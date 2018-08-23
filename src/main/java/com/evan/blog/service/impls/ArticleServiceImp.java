package com.evan.blog.service.impls;

import com.evan.blog.model.*;
import com.evan.blog.model.enums.DraftStatus;
import com.evan.blog.pojo.ArticleItem;
import com.evan.blog.pojo.ArticleDetails;
import com.evan.blog.repository.DraftDao;
import com.evan.blog.repository.CategoryDao;
import com.evan.blog.repository.CommentaryDao;
import com.evan.blog.repository.ArticleDao;
import com.evan.blog.service.ArticleCacheService;
import com.evan.blog.service.ArticleService;
import com.evan.blog.util.PubIdGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "articleService")
public class ArticleServiceImp implements ArticleService {

    private int pageSize = 6;

    @Autowired
    ArticleDao ArticleDao;
    @Autowired
    DraftDao draftDao;
    @Autowired
    ArticleCacheService articleCacheService;
    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CommentaryDao commentaryDao;
    @Autowired
    PubIdGenerator pubIdGenerator;

    @Override
    public PageInfo<ArticleItem> getAllArticleItems(Integer pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);

        List<Article> articles = ArticleDao.selectArticles(null);
        PageInfo<Article> publishedArticlePageInfo = new PageInfo<>(articles);

        long total = publishedArticlePageInfo.getTotal();

        List<ArticleItem> articleItems = new ArrayList<>();

        Long[] pubIds = new Long[articles.size()];
        for (int i = 0; i < pubIds.length; i++) {
            pubIds[i] = articles.get(i).getPubId();
        }

        ArticleItem articleItem;
        Long[] voteCounts = articleCacheService.bulkGetVoteCount(pubIds);
        for (int j = 0; j < articles.size(); j++) {
            articleItem = new ArticleItem(articles.get(j));
            articleItem.setVoteCount(voteCounts[j].intValue());
            articleItems.add(articleItem);
        }

        //dumpy code

        PageInfo<ArticleItem> publishedArticleItemPageInfo = new PageInfo<>(articleItems);
        publishedArticleItemPageInfo.setTotal(total);
        return publishedArticleItemPageInfo;
    }

    @Override
    public PageInfo<ArticleItem> getArticleItemsByCategoryId(long categoryId, Integer pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articles = ArticleDao.selectArticlesByCategoryId(categoryId);
        PageInfo<Article> publishedArticlePageInfo = new PageInfo<>(articles);

        long total = publishedArticlePageInfo.getTotal();
        List<ArticleItem> articleItems = new ArrayList<>();

        Long[] pubIds = new Long[articles.size()];
        for (int i = 0; i < pubIds.length; i++) {
            pubIds[i] = articles.get(i).getPubId();
        }

        ArticleItem articleItem;
        Long[] voteCounts = articleCacheService.bulkGetVoteCount(pubIds);
        for (int j = 0; j < articles.size(); j++) {
            articleItem = new ArticleItem(articles.get(j));
            articleItem.setVoteCount(voteCounts[j].intValue());
            articleItems.add(articleItem);
        }

        //dumpy code

        PageInfo<ArticleItem> publishedArticleItemPageInfo = new PageInfo<>(articleItems);
        publishedArticleItemPageInfo.setTotal(total);
        return publishedArticleItemPageInfo;
    }

    @Override
    public ArticleDetails getArticle(long pubId) {

        Article article = ArticleDao.selectArticleByPubId(pubId);

        Long articleVisitorCount = articleCacheService.getArticleVisitorCount(pubId);
        Long voteCount = articleCacheService.getVoteCount(pubId);

        ArticleDetails articleDetails = new ArticleDetails(article);

        articleDetails.setVoteCount(voteCount.intValue());
        articleDetails.setVisitorCount(articleVisitorCount.intValue());

        return articleDetails;
    }

    @Override
    public String getTitleByPubId(long pubId) {
        return ArticleDao.selectArticleTitleByPubId(pubId);
    }


    @Override
    @Transactional
    public void addArticle(TempArticle tempArticle) {
        long pubId = pubIdGenerator.generatePubId();
        tempArticle.setPubId(pubId);

        String title = tempArticle.getTitle();

        Draft draft = new Draft();

        draft.setId(tempArticle.getDraftId());

        draft.setTitle(title);
        draft.setStatus(DraftStatus.Published);

        draftDao.updateDraft(draft);
        Category category = tempArticle.getCategory();

        if (category.getId() == null) {
            categoryDao.insertCategory(category);
            tempArticle.setCategory(category);
        }

        ArticleDao.insertTempArticle(tempArticle);

        articleCacheService.updateLatestPublishedArticle(pubId, title);
    }

    @Override
    public PageInfo<Article> getArticlesByFilter(Integer pageIndex, QueryFilter filter) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articles = ArticleDao.selectArticles(filter);
        return new PageInfo<>(articles);
    }

    @Override
    @Transactional
    public void deleteArticles(List<Long> pubIds) throws Exception {
        for (Long pubId: pubIds) {
            Draft draft = ArticleDao.selectArticleByPubId(pubId).getDraft();

            draftDao.updateDraftStatus(DraftStatus.Deleted, draft.getId());
            commentaryDao.deleteCommentariesByPubId(pubId);
            ArticleDao.deleteArticle(pubId);

        }
    }

    @Override
    public List<Article> getLatestArticles(Integer limit) {
        return ArticleDao.selectLatestArticles(8);
    }
}

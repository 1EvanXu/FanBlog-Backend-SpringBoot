package com.evan.blog.service.impls;

import com.evan.blog.model.*;
import com.evan.blog.model.enums.ArticleStatus;
import com.evan.blog.pojo.PublishedArticleDetails;
import com.evan.blog.pojo.PublishedArticleItem;
import com.evan.blog.repository.ArticleDao;
import com.evan.blog.repository.CategoryDao;
import com.evan.blog.repository.CommentaryDao;
import com.evan.blog.repository.PublishedArticleDao;
import com.evan.blog.service.CategoryService;
import com.evan.blog.service.PublishedArticleCacheService;
import com.evan.blog.service.PublishedArticleService;
import com.evan.blog.util.PubIdGenerator;
import com.evan.blog.util.RedisOperator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "publishedArticleService")
public class PublishedArticleServiceImp implements PublishedArticleService {

    private int pageSize = 6;

    @Autowired
    PublishedArticleDao publishedArticleDao;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    PublishedArticleCacheService publishedArticleCacheService;
    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CommentaryDao commentaryDao;
    @Autowired
    PubIdGenerator pubIdGenerator;

    @Override
    public PageInfo<PublishedArticleItem> getAllPublishedArticleItems(Integer pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);

        List<PublishedArticle> publishedArticles = publishedArticleDao.selectPublishedArticles(null);
        PageInfo<PublishedArticle> publishedArticlePageInfo = new PageInfo<>(publishedArticles);

        long total = publishedArticlePageInfo.getTotal();

        List<PublishedArticleItem> publishedArticleItems = new ArrayList<>();

        Integer[] pubIds = new Integer[publishedArticles.size()];
        for (int i = 0; i < pubIds.length; i++) {
            pubIds[i] = publishedArticles.get(i).getPubId();
        }

        PublishedArticleItem publishedArticleItem;
        Long[] voteCounts = publishedArticleCacheService.bulkGetVoteCount(pubIds);
        for (int j = 0; j < publishedArticles.size(); j++) {
            publishedArticleItem = new PublishedArticleItem(publishedArticles.get(j));
            publishedArticleItem.setVoteCount(voteCounts[j].intValue());
            publishedArticleItems.add(publishedArticleItem);
        }

        //dumpy code

        PageInfo<PublishedArticleItem> publishedArticleItemPageInfo = new PageInfo<>(publishedArticleItems);
        publishedArticleItemPageInfo.setTotal(total);
        return publishedArticleItemPageInfo;
    }

    @Override
    public PageInfo<PublishedArticleItem> getPublishedArticleItemsByCategoryId(Integer categoryId, Integer pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        List<PublishedArticle> publishedArticles = publishedArticleDao.selectPublishedArticlesByCategoryId(categoryId);
        PageInfo<PublishedArticle> publishedArticlePageInfo = new PageInfo<>(publishedArticles);

        long total = publishedArticlePageInfo.getTotal();
        List<PublishedArticleItem> publishedArticleItems = new ArrayList<>();

        Integer[] pubIds = new Integer[publishedArticles.size()];
        for (int i = 0; i < pubIds.length; i++) {
            pubIds[i] = publishedArticles.get(i).getPubId();
        }

        PublishedArticleItem publishedArticleItem;
        Long[] voteCounts = publishedArticleCacheService.bulkGetVoteCount(pubIds);
        for (int j = 0; j < publishedArticles.size(); j++) {
            publishedArticleItem = new PublishedArticleItem(publishedArticles.get(j));
            publishedArticleItem.setVoteCount(voteCounts[j].intValue());
            publishedArticleItems.add(publishedArticleItem);
        }

        //dumpy code

        PageInfo<PublishedArticleItem> publishedArticleItemPageInfo = new PageInfo<>(publishedArticleItems);
        publishedArticleItemPageInfo.setTotal(total);
        return publishedArticleItemPageInfo;
    }

    @Override
    public PublishedArticleDetails getPublishedArticle(Integer pubId) {

        PublishedArticle publishedArticle = publishedArticleDao.selectPublishedArticleByPubId(pubId);

        Long articleVisitorCount = publishedArticleCacheService.getArticleVisitorCount(pubId);
        Long voteCount = publishedArticleCacheService.getVoteCount(pubId);

        PublishedArticleDetails publishedArticleDetails = new PublishedArticleDetails(publishedArticle);

        publishedArticleDetails.setVoteCount(voteCount.intValue());
        publishedArticleDetails.setVisitorCount(articleVisitorCount.intValue());

        return publishedArticleDetails;
    }

    @Override
    public String getTitleByPubId(Integer pubId) {
        return publishedArticleDao.selectPublishedArticleTitleByPubId(pubId);
    }


    @Override
    @Transactional
    public void addPublishedArticle(PublishingArticle publishingArticle) {
        Integer pubId = pubIdGenerator.generatePubId();
        publishingArticle.setPubId(pubId);

        String title = publishingArticle.getTitle();

        Article article = new Article();

        article.setId(publishingArticle.getArticleId());

        article.setTitle(title);

        articleDao.updateArticle(article);
        Category category = publishingArticle.getCategory();

        if (category.getId() == null) {
            categoryDao.insertCategory(category);
            publishingArticle.setCategory(category);
        }

        publishedArticleDao.insertPublishingArticle(publishingArticle);

        publishedArticleCacheService.updateLatestPublishedArticle(pubId, title);
    }

    @Override
    public PageInfo<PublishedArticle> getPublishedArticlesByFilter(Integer pageIndex, QueryFilter filter) {
        PageHelper.startPage(pageIndex, pageSize);
        List<PublishedArticle> publishedArticles = publishedArticleDao.selectPublishedArticles(filter);
        return new PageInfo<>(publishedArticles);
    }

    @Override
    @Transactional
    public void deletePublishedArticles(List<Integer> pubIds) throws Exception {
        for (Integer pubId: pubIds) {
            Article article = publishedArticleDao.selectPublishedArticleByPubId(pubId).getArticle();
            String key = article.getId() + ":" + article.getTitle();
            if (publishedArticleCacheService.removePublishedArticleFromCache(key)) {
                articleDao.updateArticleStatus(ArticleStatus.Deleted, article.getId());
                commentaryDao.deleteCommentariesByPubId(pubId);
                publishedArticleDao.deletePublishedArticle(pubId);
            } else {
                throw new Exception("Delete PublishedArticles Failed");
            }
        }
    }
}

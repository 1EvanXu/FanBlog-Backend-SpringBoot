package com.evan.blog.service.impls;

import com.evan.blog.model.PublishedArticle;
import com.evan.blog.pojo.PublishedArticleDetails;
import com.evan.blog.pojo.PublishedArticleItem;
import com.evan.blog.repository.PublishedArticleDao;
import com.evan.blog.service.PublishedArticleCacheService;
import com.evan.blog.service.PublishedArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "publishedArticleService")
public class PublishedArticleServiceImp implements PublishedArticleService {

    private int pageSize = 6;

    @Autowired
    PublishedArticleDao publishedArticleDao;
    @Autowired
    PublishedArticleCacheService publishedArticleCacheService;


    @Override
    public PageInfo<PublishedArticleItem> getAllPublishedArticleItems(Integer pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);

        List<PublishedArticle> publishedArticles = publishedArticleDao.selectPublishedArticles();

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
        return new PageInfo<>(publishedArticleItems);
    }

    @Override
    public PageInfo<PublishedArticleItem> getPublishedArticleItemsByCategoryId(Integer categoryId, Integer pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        List<PublishedArticle> publishedArticles = publishedArticleDao.selectPublishedArticlesByCategoryId(categoryId);

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
        return new PageInfo<>(publishedArticleItems);
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
}

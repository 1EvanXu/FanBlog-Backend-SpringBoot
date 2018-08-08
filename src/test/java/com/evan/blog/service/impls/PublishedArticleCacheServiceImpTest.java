package com.evan.blog.service.impls;

import com.evan.blog.service.PublishedArticleCacheService;
import com.evan.blog.util.IPUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublishedArticleCacheServiceImpTest {

    @Autowired
    PublishedArticleCacheService publishedArticleCacheService;
    @Test
    public void vote() {
        boolean vote;
        String ip = IPUtil.getRandomIp();
        vote = publishedArticleCacheService.vote(180711661, ip);
        assertTrue(vote);
        vote = publishedArticleCacheService.vote(180711661, ip);
        assertFalse(vote);

    }

    @Test
    public void hasVoted() {
        boolean hasVoted = publishedArticleCacheService.hasVoted(180721499, "192.168.1.101");
        System.out.println(hasVoted);
        assertFalse(hasVoted);
    }

    @Test
    public void getVoteCount() {

        Long voteCount = publishedArticleCacheService.getVoteCount(180711661);
        assertEquals(1L, voteCount.longValue());
    }

    @Test
    public void updateArticlesRank() {
    }

    @Test
    public void getArticlesRankBoard() {
    }

    @Test
    public void updateLatestPublishedArticle() {
    }

    @Test
    public void getLatestPubArticleList() {
    }
}
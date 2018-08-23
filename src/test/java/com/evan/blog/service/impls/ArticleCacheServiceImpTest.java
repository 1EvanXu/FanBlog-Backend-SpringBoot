package com.evan.blog.service.impls;

import com.evan.blog.service.ArticleCacheService;
import com.evan.blog.util.IPUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleCacheServiceImpTest {

    @Autowired
    ArticleCacheService articleCacheService;
    @Test
    public void vote() {
        boolean vote;
        String ip = IPUtil.getRandomIp();
        vote = articleCacheService.vote(180711661L, ip);
        assertTrue(vote);
        vote = articleCacheService.vote(180711661L, ip);
        assertFalse(vote);

    }

    @Test
    public void hasVoted() {
        boolean hasVoted = articleCacheService.hasVoted(180721499L, "192.168.1.101");
        System.out.println(hasVoted);
        assertFalse(hasVoted);
    }

    @Test
    public void getVoteCount() {

        Long voteCount = articleCacheService.getVoteCount(180711661L);
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
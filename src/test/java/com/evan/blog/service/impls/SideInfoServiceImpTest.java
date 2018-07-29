package com.evan.blog.service.impls;

import com.evan.blog.pojo.SideInfoItem;
import com.evan.blog.service.SideInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SideInfoServiceImpTest {

    @Autowired
    @Qualifier(value = "sideInfoService")
    SideInfoService sideInfoService;

    @Test
    public void getPopularPublishedArticle() {
        List<SideInfoItem> sideInfoItems = sideInfoService.getPopularPublishedArticle();
        System.out.println(sideInfoItems);
    }

    @Test
    public void getLatestPublishedArticle() {
        List<SideInfoItem> sideInfoItems = sideInfoService.getLatestPublishedArticle();
        System.out.println(sideInfoItems);
    }

    @Test
    public void getCategoriesInfo() {
        List<SideInfoItem> sideInfoItems = sideInfoService.getCategoriesInfo();
        System.out.println(sideInfoItems);
    }
}
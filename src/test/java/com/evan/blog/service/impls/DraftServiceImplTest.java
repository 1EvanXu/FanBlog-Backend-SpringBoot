package com.evan.blog.service.impls;

import com.evan.blog.model.Draft;
import com.evan.blog.service.DraftService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DraftServiceImplTest {

    @Autowired
    DraftService service;

    @Test
    public void queryArticleById() {
        Draft draft = service.queryDraftById(1);
        assertEquals("test draft title 1", draft.getTitle());
    }
}
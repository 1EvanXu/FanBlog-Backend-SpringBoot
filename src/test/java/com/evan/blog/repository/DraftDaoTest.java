package com.evan.blog.repository;

import com.evan.blog.model.Draft;
import com.evan.blog.model.DraftQueryFilter;
import com.evan.blog.model.enums.DraftStatus;
import com.evan.blog.model.enums.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DraftDaoTest {

    private Draft draft = null;

    private Long id = null;

    private String testTitle = "test draft title";

    private String testHtml = "<h1>content title</h1>";

    private String testMd = "#content title";

    @Autowired
    private DraftDao draftDao;

    @Before
    public void insertDraft() {

        draft = new Draft(testTitle, DraftStatus.Editing, testHtml, testMd);
        draftDao.insertDraft(draft);
        id = draft.getId();
        assertNotNull(id);

    }

    @Test
    public void getAllDraft() {
        DraftQueryFilter queryFilter = new DraftQueryFilter("created_time", Order.Asc, DraftStatus.Editing);
        List<Draft> drafts = draftDao.selectAllDrafts(queryFilter);
        for (Draft draft : drafts) {
            assertEquals(DraftStatus.Editing, draft.getStatus());
        }
        Draft draft1 = drafts.get(0);
        Draft draft2 = drafts.get(1);
        int r = draft1.getCreatedTime().compareTo(draft2.getCreatedTime());
        assertTrue(r < 0);

        queryFilter = new DraftQueryFilter("created_time", Order.Desc, DraftStatus.Editing);
        drafts = draftDao.selectAllDrafts(queryFilter);
        draft1 = drafts.get(0);
        draft2 = drafts.get(1);
        r = draft1.getCreatedTime().compareTo(draft2.getCreatedTime());
        assertTrue(r > 0);

    }

    @Test
    public void selectDraftById() {
        Draft d = draftDao.selectDraftById(id);
        assertEquals(testTitle, d.getTitle());
        assertEquals(testHtml, d.getHtmlContent());
        assertEquals(testMd, d.getMarkdownContent());
    }

    @Test
    public void updateDraftStatus() {
        draftDao.updateDraftStatus(DraftStatus.Deleted, id);
        Draft d = draftDao.selectDraftById(id);
        assertEquals(DraftStatus.Deleted, d.getStatus());
    }

    @Test
    public void updateDraft() {
        String updatedTitle = "中文文章标题测试";

        draft.setTitle(updatedTitle);
        draftDao.updateDraft(draft);
        Draft afterUpdatedDraft = draftDao.selectDraftById(draft.getId());
        assertEquals(updatedTitle, afterUpdatedDraft.getTitle());
    }

    @After
    public void deleteDraft() {

        draftDao.deleteDraft(id);
        Draft d = draftDao.selectDraftById(id);
        assertNull(d);
        draft = null;
        id = null;
    }
}
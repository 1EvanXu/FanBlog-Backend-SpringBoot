package com.evan.blog.service.impls;

import com.evan.blog.model.Draft;
import com.evan.blog.model.QueryFilter;
import com.evan.blog.model.enums.DraftStatus;
import com.evan.blog.repository.DraftDao;
import com.evan.blog.service.DraftService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "draftService")
public class DraftServiceImpl implements DraftService {

    @Autowired
    private DraftDao draftDao;

    private int pageSize = 6;

    @Override
    public PageInfo<Draft> getDrafts(Integer pageIndex, QueryFilter filter) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Draft> drafts = draftDao.selectAllDrafts(filter);
        return new PageInfo<>(drafts);
    }

    @Override
    public Draft queryDraftById(long id) {
        return draftDao.selectDraftById(id);
    }

    @Override
    public void addDraft(Draft draft) {
        draftDao.insertDraft(draft);
    }

    @Override
    public void updateDraft(Draft draft) {
        draftDao.updateDraft(draft);
    }

    @Override
    @Transactional
    public void updateDraftStatus(DraftStatus status, List<Long> ids) {
        for (Long id: ids) {
            draftDao.updateDraftStatus(status, id);
        }

    }

    @Override
    @Transactional
    public void removeDrafts(List<Long> articleIds) {
        for (Long id: articleIds) {
            draftDao.deleteDraft(id);
        }
    }
}

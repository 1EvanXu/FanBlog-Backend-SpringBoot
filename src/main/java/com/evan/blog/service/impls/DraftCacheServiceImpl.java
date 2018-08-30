package com.evan.blog.service.impls;

import com.evan.blog.model.Draft;
import com.evan.blog.model.Category;
import com.evan.blog.model.enums.DraftStatus;
import com.evan.blog.pojo.TempDraft;
import com.evan.blog.repository.CategoryDao;
import com.evan.blog.repository.DraftDao;
import com.evan.blog.service.DraftCacheService;
import com.evan.blog.util.JsonUtil;
import com.evan.blog.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

@Service(value = "draftCacheService")
public class DraftCacheServiceImpl implements DraftCacheService {

    private static final long DURATION = 7200L;  //7200 seconds, 2 hours.

    private final String keyPrefix = "md_content:";

    private static final Semaphore semaphore = new Semaphore(1);

    @Autowired
    RedisOperator redisOperator;

    @Autowired
    DraftDao draftDao;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public long generateTempArticleId() {
        String key = "temp_article_id:";
        return redisOperator.incr(key, 1);
    }

    @Override
    public long saveDraftInCache(TempDraft tempDraft) throws IllegalAccessException {
        Long tempDraftId = tempDraft.getTempDraftId();
        Long draftId = tempDraft.getId();

        if (tempDraftId == null && draftId == null)
            throw new IllegalAccessException("the property tempDraftId and id can't be null same time");

        String key = null;
        String tempKey = tempDraftId != null ? keyPrefix + "d:" + tempDraftId : null;

        if (tempKey == null) {
            key =  keyPrefix + "a:" + draftId;
        } else {
            if (draftId == null) {
                key = tempKey;
            } else {
                key =  keyPrefix + "a:" + draftId;
                redisOperator.del(tempKey);
            }
        }

        String content = JsonUtil.objectToJson(tempDraft);

        redisOperator.set(key, content, randomExpireTime());

        return System.currentTimeMillis();
    }

    @Override
    public TempDraft getDraftContent(Long draftId) {
        String key = keyPrefix + "a:"+ draftId;
        String value = redisOperator.get(key);

        TempDraft tempDraft = null;

        if (value != null && !value.equals("")) {
            tempDraft = JsonUtil.jsonToPojo(value, TempDraft.class);
        } else {
            // 加锁防止缓存穿透
            try {
                semaphore.acquire();
                Draft draft = draftDao.selectDraftById(draftId);
                if (draft == null) {
                    throw new IllegalAccessException("The draft not exist!");
                } else if (draft.getStatus() != DraftStatus.Editing) {
                    throw new IllegalAccessException("Can edit only when the status of draft is Editing");
                }
                tempDraft = new TempDraft(draft);

                redisOperator.set(key, JsonUtil.objectToJson(tempDraft), randomExpireTime());

            } catch (InterruptedException | IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            } finally {
                semaphore.release();
            }
        }

        return tempDraft;

    }

    @Override
    public Long saveDraft(TempDraft tempDraft) throws NullPointerException {
        Long draftId = tempDraft.getId();
        String key, value;
        Draft draft;
        if (draftId != null) {
            key = keyPrefix + "a:"+ draftId;
            value = redisOperator.get(key);

            draft = JsonUtil.jsonToPojo(value, TempDraft.class);
            draft.setHtmlContent(tempDraft.getHtmlContent());
            draftDao.updateDraft(draft);
            return draftId;
        }
        Long tempDraftId = tempDraft.getTempDraftId();
        if (tempDraftId != null) {
            key = keyPrefix + "d:" + tempDraftId;
            value = redisOperator.get(key);
            draft = JsonUtil.jsonToPojo(value, TempDraft.class);

            // set default title if title is empty
            if (draft.getTitle() == null || draft.getTitle().equals("")) {
                draft.setTitle("Temp article title:"+tempDraftId.toString());
            }

            draft.setHtmlContent(tempDraft.getHtmlContent());
            draftDao.insertDraft(draft);
            return draft == null ? null : draft.getId();
        }
        throw new IllegalArgumentException("the property tempDraftId and id can't be null same time");
    }

    @Override
    public List<Category> searchCategoryByName(String keywords) {
        return categoryDao.selectCategoriesByName(keywords);
    }

    //防止缓存雪崩
    private long randomExpireTime() {
        Random random = new Random();
        long delta = (long) random.nextInt(120);
        return DURATION + delta;
    }
}

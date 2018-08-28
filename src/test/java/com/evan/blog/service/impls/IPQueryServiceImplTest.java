package com.evan.blog.service.impls;

import com.evan.blog.pojo.IPLocation;
import com.evan.blog.service.IPQueryService;
import com.evan.blog.util.IPUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IPQueryServiceImplTest {

    @Autowired
    @Qualifier("IPQueryService")
    IPQueryService ipQueryService;

    @Test
    public void query() {

    }
}
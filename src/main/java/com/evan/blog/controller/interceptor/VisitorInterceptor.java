package com.evan.blog.controller.interceptor;

import com.evan.blog.model.GithubUser;
import com.evan.blog.pojo.IPLocation;
import com.evan.blog.pojo.VisitorRecord;
import com.evan.blog.service.ArticleService;
import com.evan.blog.service.IPQueryService;
import com.evan.blog.service.VisitorRecordCacheService;
import com.evan.blog.util.IPUtil;
import com.evan.blog.util.JsonUtil;
import com.sun.jndi.toolkit.url.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisitorInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier("IPQueryService")
    IPQueryService ipQueryService;

    @Autowired
    VisitorRecordCacheService visitorRecordService;

    @Autowired
    ArticleService articleService;

    private Pattern pattern1 = Pattern.compile(".*/blog/articles/(\\d{9})/?$");
    private Pattern pattern2 = Pattern.compile(".*/blog/articles/items/p/\\d+/?$");


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();

        Matcher matcher1 = pattern1.matcher(url);
        Matcher matcher2 = pattern2.matcher(url);
        if (!matcher1.matches() && !matcher2.matches()) {
            return true;
        }

        String ip = IPUtil.getRealIP(request);

        String username = null;
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie c: cookies) {
                if (c.getName().equals("user")) {
                    String s = UrlUtil.decode(c.getValue());
                    GithubUser user = JsonUtil.jsonToPojo(s, GithubUser.class);
                    assert user != null;
                    username = user.getName();
                }
            }
        } catch (NullPointerException e) {
            username = null;
        }

        VisitorRecord record = new VisitorRecord();
        if (username == null) {
            username = "visitor";
            record.setVisitorType(VisitorRecord.PASSAGER);
        } else {
            record.setVisitorType(VisitorRecord.USER);
        }

        IPLocation location = ipQueryService.query(ip);
        record.setIpLocation(location);
        record.setIpAddress(ip);
        record.setName(username);
        record.setVisitTime(new Date().getTime());
        // Catch spec Exception
        visitorRecordService.recordVisitor(record);
        visitorRecordService.updateRegionDistributions(record.getIpLocation().getCity());

        //if visiting an article, record this visitor
        if (matcher1.find()) {
            long pubId = Long.parseLong(matcher1.group(1));
            if (articleService.getTitleByPubId(pubId) != null) {
                visitorRecordService.addVisitorsRecord((int)pubId, record);
            }
        }

        // record the pv info
        if (matcher2.find()) {
            visitorRecordService.pageViewCount();
        }

        return true;
    }
}

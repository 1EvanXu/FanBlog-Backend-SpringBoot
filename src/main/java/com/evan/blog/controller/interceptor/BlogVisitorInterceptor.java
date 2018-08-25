package com.evan.blog.controller.interceptor;

import com.evan.blog.pojo.IPLocation;
import com.evan.blog.pojo.VisitorRecord;
import com.evan.blog.service.IPQueryService;
import com.evan.blog.service.SiteInfoCacheService;
import com.evan.blog.service.VisitorRecordCacheService;
import com.evan.blog.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlogVisitorInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier("IPQueryService")
    IPQueryService ipQueryService;

    @Autowired
    VisitorRecordCacheService visitorRecordService;

    @Autowired
    SiteInfoCacheService siteInfoCacheService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String ip = IPUtil.getRealIP(request);

        String username;
        try {

            username = request.getSession().getAttribute("username").toString();
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
        siteInfoCacheService.updateRegionDistribution(record);

        String url = request.getRequestURL().toString();

        //if visiting an article, record this visitor

        Matcher matcher = Pattern.compile(".*apis/blog/articles/(\\d{9})/?$").matcher(url);
        if (matcher.find()) {
            int pubId = Integer.parseInt(matcher.group(1));
            visitorRecordService.addVisitorsRecord(pubId, record);
        }

        return true;
    }
}

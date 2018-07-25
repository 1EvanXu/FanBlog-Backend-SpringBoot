package com.evan.blog.controller.interceptor;

import com.evan.blog.pojo.VisitorRecord;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlogVisitorInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getRemoteAddr();
        String username = request.getSession().getAttribute("username").toString();
        VisitorRecord record = new VisitorRecord();
        if (username == null) {
            record.setVisitorType(VisitorRecord.VisitorType.PASSAGER);
        } else {
            record.setVisitorType(VisitorRecord.VisitorType.USER);
        }
        return false;
    }
}

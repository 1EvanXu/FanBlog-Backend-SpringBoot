package com.evan.blog.controller.interceptor;

import com.evan.blog.model.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

public class AuthorityInterceptor implements HandlerInterceptor {

    private Pattern pattern1 = Pattern.compile(".*/blog/cache/drafts$");
    private Pattern pattern2 = Pattern.compile(".*/blog/drafts/status$");
    private Pattern pattern3 = Pattern.compile(".*/blog/drafts\\?ids=.*");
    private Pattern pattern4 = Pattern.compile(".*/blog/articles\\?ids=.*");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        if (pattern1.matcher(url).matches() && pattern2.matcher(url).matches()
                && pattern3.matcher(url).matches() && pattern4.matcher(url).matches()) {
            return true;
        }
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        return user != null && user.getLevel().getLevelcode() > 0;
    }
}

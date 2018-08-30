package com.evan.blog.interceptor;

import com.evan.blog.model.User;
import com.evan.blog.model.enums.UserLevel;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        AccessLevel accessLevel = method.getAnnotation(AccessLevel.class);
        if (accessLevel == null) {
            return true;
        }

        UserLevel[] values = accessLevel.value();
        UserLevel[] roles = accessLevel.roles();
        if (values.length == 0 && roles.length == 0) {
            return false;
        }

        Set<UserLevel> authorities = new HashSet<>();
        Collections.addAll(authorities, values);
        Collections.addAll(authorities, roles);

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        return user != null && authorities.contains(user.getLevel());
    }
}

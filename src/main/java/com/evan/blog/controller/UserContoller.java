package com.evan.blog.controller;

import com.evan.blog.model.GithubUser;
import com.evan.blog.model.User;
import com.evan.blog.pojo.BlogJSONResult;
import com.evan.blog.pojo.authorization.GithubUserResponse;
import com.evan.blog.pojo.authorization.AccessTockenResponse;
import com.evan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(path = "/user")
public class UserContoller {

    @Value("${blog.oauth.github.client-id}")
    private String clientId;
    @Value("${blog.oauth.github.client-secret}")
    private String clientSecret;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserService userService;

    @Value("${blog.domain}")
    String domain;

    private final static int SessionDurationTime = 86400 * 2; // two day

    @GetMapping(path = "/authorization")
    public void githubAuthCallback(@RequestParam(value = "code", required = false) String code,
                                         HttpServletRequest request, HttpServletResponse response) throws IOException {
        String getAccessTokenUrl = "https://github.com/login/oauth/access_token?client_id=" + clientId + "&client_secret=" + clientSecret + "&code=" + code;

        AccessTockenResponse tokenResponse = restTemplate.getForObject(getAccessTokenUrl, AccessTockenResponse.class);
        String accessToken = tokenResponse.getAccess_token();

        String param; // the params in redirect url；

        if (accessToken != null && !accessToken.equals("")) {
            String getGithubUserUrl = "https://api.github.com/user?access_token=" + accessToken;
            GithubUserResponse userResponse = restTemplate.getForObject(getGithubUserUrl, GithubUserResponse.class);
            if (userResponse.validResponse()) {
                GithubUser user = (GithubUser) userService.getOrAddUser(userResponse.convertToGithubUser());

                HttpSession session = request.getSession();

                session.setAttribute("user", user);

                session.setMaxInactiveInterval(SessionDurationTime);
                param = ";userId=" + user.getId();
            } else {
                param = ";error=Bad response";
            }
        } else {
            param = ";error=Bad access token";
        }
        String redirectUrl = response.encodeRedirectURL(domain + "/blog/login" + param);
        response.sendRedirect(redirectUrl);
    }

    @GetMapping(path = "/{userId}")
    @ResponseBody
    public BlogJSONResult getUser(@PathVariable("userId") Long userId, HttpServletRequest request) {
        if (userId == null) {
            return BlogJSONResult.errorMsg("Param userId can't be null");
        }
        HttpSession session = request.getSession();

        GithubUser user = (GithubUser) session.getAttribute("user:" + userId);
        return BlogJSONResult.ok(user);
    }

    @GetMapping(path = "/{userId}/state")
    @ResponseBody
    public BlogJSONResult getUserLogState(@PathVariable("userId") Long userId, HttpServletRequest request) {
        String attributeName = "user:" + userId.toString();
        HttpSession session = request.getSession();

        BlogJSONResult jsonResult = new BlogJSONResult(200, attributeName + " logged in.", 1);
        if (session.getAttribute(attributeName) == null) {
            jsonResult.setMsg(attributeName + " not logged in.");
            jsonResult.setData(10);
        }
        return jsonResult;
    }

    @PutMapping(path = "/{userId}/state")
    @ResponseBody
    public BlogJSONResult logout(@PathVariable("userId") Long userId, HttpServletRequest request) {
        String attributeName = "user:" + userId.toString();
        HttpSession session = request.getSession();
        session.removeAttribute(attributeName);
        return new BlogJSONResult(200, "Log out suceed", 0);
    }

}


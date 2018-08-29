package com.evan.blog.Handler;

import com.evan.blog.exception.ResourceNotExistException;
import com.evan.blog.pojo.BlogJSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = {"com.evan.blog.controller"})
public class CommonExceptionHandler {

    @ExceptionHandler(value = ResourceNotExistException.class)
    @ResponseBody
    public BlogJSONResult handleResourceNotExist(ResourceNotExistException e) {
        return new BlogJSONResult(404, e.getMessage(), null);
    }
}

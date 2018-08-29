package com.evan.blog.controller;

import com.evan.blog.exception.ResourceUploadException;
import com.evan.blog.util.FileUtil;
import com.evan.blog.util.ImageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/resources")
public class ResourceController {

    @Value("${blog.resource.temp-dir}")
    private String tempResourcePath;

    @Value("${blog.resource.image-dir}")
    private String imagesPath;

    @Value("${blog.domain}")
    String domain;

    private final static int maxImageWidth = 500;

    @PostMapping("/images")
    public ImageUploadResult uploadBlogArticleImage(MultipartFile file) {

        String fileName = UUID.randomUUID().toString();  //图片名字
        String fileFormat = file.getContentType();
        fileFormat = fileFormat.split("/")[1];
        fileName = fileName + "." + fileFormat;
        System.out.println(fileName);
        boolean storeResult = false; // 存储文件的结果
        //调用文件处理类FileUtil，处理文件，将文件写入指定位置
        try {
            storeResult = FileUtil.storeFile(file.getBytes(), tempResourcePath, fileName);
        } catch (IOException e) {
            throw new ResourceUploadException("Failed to save the resource in server.", e);
        }

        if (storeResult) {
            String srcImagePath = tempResourcePath + "/" + fileName;
            String destImagePath = imagesPath + "/" + fileName;
            try {

                ImageUtil.processBlogImage(srcImagePath, destImagePath, maxImageWidth, 0, fileFormat);
            } catch (Exception e) {
                throw new ResourceUploadException("Failed to process image.", e);
            }
        }
        String imageUrl = domain + "/images/" + fileName;
        return ImageUploadResult.success(imageUrl);
    }

    static class ImageUploadResult {
        private int success;
        private String message;
        private String url;

        public static ImageUploadResult success(String url) {
            ImageUploadResult result = new ImageUploadResult();
            result.setMessage("Upload succeed!");
            result.setSuccess(1);
            result.setUrl(url);
            return result;
        }

        public int getSuccess() {
            return success;
        }

        public void setSuccess(int success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

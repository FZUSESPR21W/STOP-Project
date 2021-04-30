package com.fzu.stop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * @author 宋家锐
 */
public interface FileService {
    List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif","image/jpg","image/png","image/svg");
    Logger LOGGER = LoggerFactory.getLogger(FileService.class);
    /**
     * 上传图片
     * @param file
     * @return 图片url
     */
    String uploadPicture(MultipartFile file);
}

package com.fzu.stop.controller;

import com.fzu.stop.pojo.ResponseDTO;
import com.fzu.stop.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 宋家锐
 */
@Controller
@RequestMapping("/api")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("/upload_pic")
    @ResponseBody
    public ResponseDTO uploadPicture(MultipartFile file){
        String url = fileService.uploadPicture(file);

        return null;
    }
}

package com.fzu.stop.controller;

import com.fzu.stop.util.ResponseUtil;
import com.fzu.stop.pojo.ResponseDTO;
import com.fzu.stop.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

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
        if(url!=null){
            Map<String,Object> data = new HashMap<>(1);
            data.put("pictureUrl",url);
            return ResponseUtil.getSuccessResponse("上传成功",data);
        }

        return ResponseUtil.getFailResponse("上传失败",new HashMap<>(1));
    }
}

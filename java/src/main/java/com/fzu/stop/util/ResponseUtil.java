package com.fzu.stop.util;

import com.fzu.stop.pojo.ResponseDTO;

import java.util.Map;

/**
 * @author 梁达毅
 */
public class ResponseUtil {
    public static ResponseDTO getSuccessResponse(String message, Map<String,Object> data){
        ResponseDTO responseDTO = new ResponseDTO(0,message,data);
        return responseDTO;
    }
    public static ResponseDTO getFailResponse(String message, Map<String,Object> data){
        ResponseDTO responseDTO = new ResponseDTO(-1,message,data);
        return  responseDTO;
    }
}

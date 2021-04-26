package com.fzu.stop.Util;

import com.fzu.stop.pojo.ResponseDTO;

import java.util.Map;

public class ResponseUtil {
    public static ResponseDTO getSuccessResponse(String message, Map<String,Object> data){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setErrorCode(0);
        responseDTO.setMessage(message);
        responseDTO.setData(data);
        return  responseDTO;
    }
    public static ResponseDTO getFailResponse(String message, Map<String,Object> data){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setErrorCode(-1);
        responseDTO.setMessage(message);
        responseDTO.setData(data);
        return  responseDTO;
    }
}

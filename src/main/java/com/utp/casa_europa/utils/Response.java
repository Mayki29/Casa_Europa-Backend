package com.utp.casa_europa.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {
    public static <T> ResponseEntity<Map<String, Object>> setResponse(T data, HttpStatus status){
        Map<String, Object> responseMap = new HashMap<>();
        if(status.isError()){
            responseMap.put("success", false);
            responseMap.put("error", data);
        }else{
            responseMap.put("success", true);
            responseMap.put("result", data);
        }

        responseMap.put("statusCode", status.value());            
        responseMap.put("timestamp", System.currentTimeMillis());            
        
        return ResponseEntity.status(status).body(responseMap);
    }
    

}

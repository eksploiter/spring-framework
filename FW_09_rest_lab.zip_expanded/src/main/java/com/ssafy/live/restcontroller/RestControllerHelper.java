package com.ssafy.live.restcontroller;

import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// TODO: 02-3. 성공과 실패 시 ResponseEntity로 상태와 함께 데이터를 반환하도록 메서드를 구성해보자.
public interface RestControllerHelper {
    default ResponseEntity<?> handleSuccess(Object data){
        return handleSuccess(data, HttpStatus.OK);
    }
    
    default ResponseEntity<?> handleFail(Exception e){
        return handleFail(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    default ResponseEntity<?> handleSuccess(Object data, HttpStatus status){
        Map<String, Object> map = Map.of("status", "SUCCESS","data", data);
        return ResponseEntity.status(status).body(map);
    }
    
    default ResponseEntity<?> handleFail(Exception e, HttpStatus status){
        e.printStackTrace();
        Map<String, Object> map = Map.of("status", "FAIL","error", e.getMessage());
        return ResponseEntity.status(status).body(map);
    }
}

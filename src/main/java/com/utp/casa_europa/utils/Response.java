package com.utp.casa_europa.utils;

import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
     private boolean success;
    private T result;
    private Object error;
    private int statusCode;
    private long timestamp;

    public static <T> ResponseEntity<Response<T>> setResponse(T data, HttpStatus status) {
        boolean isError = status.isError();

        Response<T> response = Response.<T>builder()
            .success(!isError)
            .result(isError ? null : data)
            .error(isError ? data : null)
            .statusCode(status.value())
            .timestamp(Instant.now().toEpochMilli())
            .build();

        return ResponseEntity.status(status).body(response);
    }
    

}

package com.example.RomashkaKo.respons;

import lombok.Data;

import java.util.List;

@Data
public class BaseResponse {
    private String status = "OK";
    private final Integer code;
    private List<String> exceptions;

    public BaseResponse(String status, Integer code) {
        this.status = status;
        this.code = code;
    }
    public BaseResponse(List<String> exceptions, Integer code) {
        this.exceptions = exceptions;
        this.code = code;
    }
}

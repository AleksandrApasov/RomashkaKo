package com.example.RomashkaKo.respons;

import lombok.Data;

import java.util.List;

@Data
public class ErrorsListResponse extends ParentResponse {

    private List<String> exceptions;
    public ErrorsListResponse(String status, List<String> exceptions) {
        this.exceptions = exceptions;
        super.status = status;
    }
}

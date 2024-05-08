package com.example.RomashkaKo.respons;

import lombok.Data;

import java.util.List;

@Data
public class StatusResponse extends ParentResponse {

    public StatusResponse(String status) {
        super.status = status;
    }
}

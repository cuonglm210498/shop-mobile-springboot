package com.lecuong.exception.error;

import com.lecuong.exception.StatusResponse;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private StatusResponse statusResponse;

    public BusinessException(StatusResponse statusResponse) {
        this.statusResponse = statusResponse;
    }
}

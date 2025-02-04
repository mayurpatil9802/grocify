package com.grocify.commonlibs.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreErrorResponse {

    private String message;
    private int errorCode;

}

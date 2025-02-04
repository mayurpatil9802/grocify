package com.grocify.commonlibs.model.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreDetailRequest {

    private Long userId;

    private String storeName;

    private String storeAddress;

    private String gstNo;

    private String description;
}

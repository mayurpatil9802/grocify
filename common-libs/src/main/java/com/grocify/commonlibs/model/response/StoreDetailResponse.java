package com.grocify.commonlibs.model.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreDetailResponse {

    private Long userId;

    private Long storeId;

    private String store_Name;

    private String store_Address;

    private String gstNo;

    private String description;
}

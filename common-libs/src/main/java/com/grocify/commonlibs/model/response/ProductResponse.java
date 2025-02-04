package com.grocify.commonlibs.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Data
@Builder
public class ProductResponse {
    private Long id;

    private String productName;

    private String description;

    private int price;

    private String unit;

    private int availableUnit;

    private Long storeId;

    private Map<String, String> metadata;

    private String imageURL;
}

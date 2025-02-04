package com.grocify.commonlibs.model.request;

import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Builder
@Data
public class UpdateProductRequest {

    private String productName;

    private String description;

    private int price;

    private String unit;

    private int availableUnit;

    private Map<String, String> metadata;

}

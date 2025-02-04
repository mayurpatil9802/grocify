package com.grocify.commonlibs.model.request;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateProductRequest {

    private String productName;

    private String description;

    private int price;

    private String unit;

    private int availableUnit;

    private Map<String, String> metadata;
}

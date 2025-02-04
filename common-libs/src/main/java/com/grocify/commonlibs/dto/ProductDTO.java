package com.grocify.commonlibs.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;


@Data
@Builder
public class ProductDTO {
    private Long id;

    private String productName;

    private String description;

    private int price;

    private String unit;

    private int availableUnit;

    private Long storeId;

    private Map<String, String> metadata;

    private String imageURL;

    private boolean status;

    private LocalDate createdAt;

    private LocalDate updatedAt;



}

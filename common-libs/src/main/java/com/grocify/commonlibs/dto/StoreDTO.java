package com.grocify.commonlibs.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class StoreDTO {

    private Long id;

    private String name;


    private String address;

    private String gstNo;

    private String description;

    private boolean status;

    private LocalDate create_at;


    private LocalDate update_at;

    private Long userId;

}

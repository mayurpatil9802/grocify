package com.grocify.commonlibs.mapper;

import com.grocify.commonlibs.dto.StoreDTO;
import com.grocify.commonlibs.entity.StoreEntity;
import com.grocify.commonlibs.model.request.StoreDetailRequest;
import com.grocify.commonlibs.model.response.StoreDetailResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StoreMapper {

    public StoreDTO storeEntityToStoreDTO(StoreEntity user) {
        return StoreDTO.builder()
                .gstNo(user.getGstNo())
                .id(user.getId())
                .userId(user.getUserId())
                .name(user.getName())
                .address(user.getAddress())
                .description(user.getDescription())
                .id(user.getId())
                .build();
    }

    public StoreEntity updateStoreDTOToStoreEntity(StoreDTO storeDTO) {
        StoreEntity userEntity = new StoreEntity();
        userEntity.setId(storeDTO.getId());
        userEntity.setName(storeDTO.getName());
        userEntity.setAddress(storeDTO.getAddress());
        userEntity.setUserId(storeDTO.getUserId());
        userEntity.setDescription(storeDTO.getDescription());
        userEntity.setGstNo(storeDTO.getGstNo());
        userEntity.setStatus(storeDTO.isStatus());
        userEntity.setUpdatedAt(LocalDate.now());
        return userEntity;

    }

    public StoreEntity storeDTOToStoreEntity(StoreDTO storeDTO) {
        StoreEntity userEntity = new StoreEntity();
//        userEntity.setId(storeDTO.getId());
        userEntity.setName(storeDTO.getName());
        userEntity.setUserId(storeDTO.getUserId());
        userEntity.setAddress(storeDTO.getAddress());
        userEntity.setDescription(storeDTO.getDescription());
        userEntity.setGstNo(storeDTO.getGstNo());
        userEntity.setStatus(true);
        return userEntity;

    }

    public StoreDetailResponse StoreDTOToStoreResponse(StoreDTO userDTO) {
        return StoreDetailResponse.builder()
                .gstNo(userDTO.getGstNo())
                .userId(userDTO.getUserId())
                .description(userDTO.getDescription())
                .store_Address(userDTO.getAddress())
                .store_Name(userDTO.getName())
                .storeId(userDTO.getId())
                .build();
    }

    public StoreDTO StoreDetailRequestToStoreDTO(StoreDetailRequest storeDetailRequest) {
        return StoreDTO.builder()
                .gstNo(storeDetailRequest.getGstNo())
                .description(storeDetailRequest.getDescription())
                .address(storeDetailRequest.getStoreAddress())
                .name(storeDetailRequest.getStoreName())
                .userId(storeDetailRequest.getUserId())
                .create_at(LocalDate.now())
                .update_at(LocalDate.now())
                .build();
    }

    public StoreEntity storeDettailRequestToStoreEntity(StoreDetailRequest storeDetailRequest) {
        StoreEntity userEntity = new StoreEntity();

        userEntity.setName(storeDetailRequest.getStoreName());
        userEntity.setAddress(storeDetailRequest.getStoreAddress());
        userEntity.setDescription(storeDetailRequest.getDescription());
        userEntity.setGstNo(storeDetailRequest.getGstNo());
        return userEntity;

    }


}

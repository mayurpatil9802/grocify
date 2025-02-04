package com.grocify.storemgmt.service;


import com.grocify.commonlibs.dao.StoreDao;
import com.grocify.commonlibs.dto.StoreDTO;
import com.grocify.commonlibs.mapper.StoreMapper;
import com.grocify.commonlibs.model.request.StoreDetailRequest;
import com.grocify.commonlibs.model.response.StoreDetailResponse;
import com.grocify.storemgmt.exception.StoreNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grocify.storemgmt.validator.UpdateStoreRequestValidation;

import java.util.Optional;

@Service
@Transactional
public class StoreService {

    @Autowired
    private StoreDao storeDao;


    @Autowired
    private UpdateStoreRequestValidation updateStoreRequestValidation;

    @Autowired
    private StoreMapper storeMapper;


    public StoreDetailResponse updateStoreDetail(Long id, StoreDetailRequest obj) {
        updateStoreRequestValidation.validationUpdateRequest(obj);

        Optional<StoreDTO> storeDto = storeDao.getStoreById(id);
        if (storeDto.isEmpty() || storeDto.get().isStatus()) {
            throw new StoreNotFoundException("store with provided id is not present");
        }
        StoreDTO storeDTO = storeDto.get();
        storeDTO.setName(obj.getStoreName());
        storeDTO.setAddress(obj.getStoreAddress());
        storeDTO.setDescription(obj.getDescription());
        storeDTO = storeDao.updateStoreDetail(storeDTO);
        return storeMapper.StoreDTOToStoreResponse(storeDTO);
    }

    public StoreDetailResponse insertStoreDetail(StoreDetailRequest storeDetailRequest) {
        updateStoreRequestValidation.validationUpdateRequest(storeDetailRequest);
        StoreDTO storeDTO = storeMapper.StoreDetailRequestToStoreDTO(storeDetailRequest);
        storeDTO = storeDao.insertStoreData(storeDTO);
        return storeMapper.StoreDTOToStoreResponse(storeDTO);
    }

    public StoreDetailResponse deleteStoreDetail(Long id) {
        Optional<StoreDTO> storeDto = storeDao.getStoreById(id);
        if (storeDto.isEmpty() || storeDto.get().isStatus()) {
            throw new StoreNotFoundException("store with provided id is not present");
        }
        StoreDTO storeDTO =  storeDto.get();
        storeDTO.setStatus(false);
        storeDTO = storeDao.updateStoreDetail(storeDTO);

        return storeMapper.StoreDTOToStoreResponse(storeDTO);
    }
}

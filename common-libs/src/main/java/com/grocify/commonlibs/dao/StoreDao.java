package com.grocify.commonlibs.dao;


import com.grocify.commonlibs.dto.StoreDTO;
import com.grocify.commonlibs.entity.StoreEntity;
import com.grocify.commonlibs.mapper.StoreMapper;
import com.grocify.commonlibs.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class StoreDao {


    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreMapper storeMapper;

    public Optional<StoreDTO> getStoreById(Long id) {
        System.out.println("dao1");
        Optional<StoreEntity> StoreDetail = storeRepository.findById(id);
        if (StoreDetail.isPresent()) {
            StoreEntity store = StoreDetail.get();
            StoreDTO storeDTO = storeMapper.storeEntityToStoreDTO(store);
            return Optional.of(storeDTO);
        }
        return Optional.empty();

    }

    public StoreDTO updateStoreDetail(StoreDTO storeDTO) {

        StoreEntity storeEntity = storeMapper.updateStoreDTOToStoreEntity(storeDTO);
        storeRepository.save(storeEntity);
        return storeDTO;

    }

    public StoreDTO insertStoreData(StoreDTO obj) {
        StoreEntity storeEntity = storeMapper.storeDTOToStoreEntity(obj);
        storeRepository.save(storeEntity);
        return storeMapper.storeEntityToStoreDTO(storeEntity);
    }

}

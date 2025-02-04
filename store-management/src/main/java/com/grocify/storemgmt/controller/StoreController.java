package com.grocify.storemgmt.controller;


import com.grocify.commonlibs.model.request.StoreDetailRequest;
import com.grocify.commonlibs.model.response.StoreDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.grocify.storemgmt.service.StoreService;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;


    @PutMapping("/{id}")
    public StoreDetailResponse updateStore(@PathVariable Long id, @RequestBody StoreDetailRequest obj) {

        return storeService.updateStoreDetail(id,obj);
    }

    @PostMapping
    public StoreDetailResponse insertData(@RequestBody StoreDetailRequest storeDetailRequest) {
        return storeService.insertStoreDetail(storeDetailRequest);
    }

    @DeleteMapping("/{id}")
    public StoreDetailResponse deleteData(@PathVariable Long id){
        return storeService.deleteStoreDetail(id);
    }



}

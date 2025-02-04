package com.grocify.commonlibs.mapper;

import com.grocify.commonlibs.dto.ProductDTO;
import com.grocify.commonlibs.entity.ProductEntity;
import com.grocify.commonlibs.model.request.CreateProductRequest;
import com.grocify.commonlibs.model.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductMapper {

    public ProductDTO createProductRequestToProductDTO(CreateProductRequest createProductRequest, Long storeId) {
        return ProductDTO.builder()
                .price(createProductRequest.getPrice())
                .storeId(storeId)
                .productName(createProductRequest.getProductName())
                .availableUnit(createProductRequest.getAvailableUnit())
                .unit(createProductRequest.getUnit())
                .metadata(createProductRequest.getMetadata())
                .description(createProductRequest.getDescription())
                .build();
    }

    public ProductEntity productDTOToProductEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setStoreId(productDTO.getStoreId());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setName(productDTO.getProductName());
        productEntity.setMetadata(productDTO.getMetadata());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setImageUrl(productDTO.getImageURL());
        productEntity.setAvailableUnit(productDTO.getAvailableUnit());
        productEntity.setUnit(productDTO.getUnit());
        productEntity.setStatus(productDTO.isStatus());
        return productEntity;
    }

    public ProductDTO productEntityToProductDTO(ProductEntity productEntity) {
        return ProductDTO.builder().id(productEntity.getId()).availableUnit(productEntity.getAvailableUnit()).productName(productEntity.getName()).storeId(productEntity.getStoreId()).price(productEntity.getPrice()).unit(productEntity.getUnit()).metadata(productEntity.getMetadata()).createdAt(LocalDate.now()).updatedAt(LocalDate.now()).description(productEntity.getDescription()).imageURL(productEntity.getImageUrl()).build();

    }

    public ProductResponse productDTOToProductResponse(ProductDTO productDTO) {
        return ProductResponse.builder().id(productDTO.getId()).price(productDTO.getPrice()).storeId(productDTO.getStoreId()).productName(productDTO.getProductName()).availableUnit(productDTO.getAvailableUnit()).unit(productDTO.getUnit()).metadata(productDTO.getMetadata()).description(productDTO.getDescription()).imageURL(productDTO.getImageURL()).build();
    }


}

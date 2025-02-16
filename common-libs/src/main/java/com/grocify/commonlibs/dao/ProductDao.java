package com.grocify.commonlibs.dao;

import com.grocify.commonlibs.dto.ProductDTO;
import com.grocify.commonlibs.entity.ProductEntity;
import com.grocify.commonlibs.mapper.ProductMapper;
import com.grocify.commonlibs.repository.ProductRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {
	@Autowired private ProductMapper productMapper;

	@Autowired private ProductRepository productRepository;

	public ProductDTO insertProductDetail(ProductDTO productDTO) {
		ProductEntity productEntity = productMapper.productDTOToProductEntity(productDTO);
		productEntity.setCreatedAt(LocalDate.now());
		productEntity.setUpdatedAt(LocalDate.now());
		productEntity.setStatus(true);

		productEntity = productRepository.save(productEntity);
		return productMapper.productEntityToProductDTO(productEntity);
	}

	public Optional<ProductDTO> getProductDetail(Long productId) {
		Optional<ProductEntity> productEntity = productRepository.findByIdAndStatus( productId,true);
		return productEntity.map(entity -> productMapper.productEntityToProductDTO(entity));
	}

	public List<ProductDTO> getAllActiveProductsFromStore(Long storeId) {
		return productRepository.findByStatusAndStoreId(true, storeId).stream()
				.map(product -> productMapper.productEntityToProductDTO(product))
				.toList();
	}

	public List<ProductDTO> getAllActiveProducts() {
		return productRepository.findAll().stream()
				.map(product -> productMapper.productEntityToProductDTO(product))
				.toList();
	}

	public ProductDTO updateProductDetails(ProductDTO productDTO) {
		ProductEntity productEntity = productMapper.productDTOToProductEntity(productDTO);
		productEntity.setId(productDTO.getId());
		productEntity.setUpdatedAt(LocalDate.now());
		productRepository.save(productEntity);
		return productDTO;
	}
}

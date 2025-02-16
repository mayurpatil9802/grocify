package com.grocify.storemgmt.service;

import com.grocify.commonlibs.dao.ProductDao;
import com.grocify.commonlibs.dto.ProductDTO;
import com.grocify.commonlibs.mapper.ProductMapper;
import com.grocify.commonlibs.model.request.CreateProductRequest;
import com.grocify.commonlibs.model.request.UpdateProductRequest;
import com.grocify.commonlibs.model.response.ProductResponse;
import jakarta.transaction.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ProductService {
	@Autowired private ProductMapper productMapper;

	@Autowired private ProductDao productDao;

	public ProductResponse insertProductData(
			Long storeId, CreateProductRequest createProductRequest) {
		ProductDTO productDTO =
				productMapper.createProductRequestToProductDTO(createProductRequest, storeId);
		productDTO = productDao.insertProductDetail(productDTO);
		return productMapper.productDTOToProductResponse(productDTO);
	}

	public List<ProductResponse> getAllProductsFromStore(Long storeId) {
		return productDao.getAllActiveProductsFromStore(storeId).stream()
				.map(product -> productMapper.productDTOToProductResponse(product))
				.toList();
	}

	public ProductResponse updateProductData(
			Long productId, UpdateProductRequest updateProductRequest) {

		Optional<ProductDTO> productDTOOptional = productDao.getProductDetail(productId);

		if (productDTOOptional.isEmpty()) {
			throw new RuntimeException("Provided product id is not present");
		}

		ProductDTO productDTO = productDTOOptional.get();
		productDTO.setPrice(updateProductRequest.getPrice());
		productDTO.setProductName(updateProductRequest.getProductName());
		productDTO.setDescription(updateProductRequest.getDescription());
		productDTO.setUnit(updateProductRequest.getUnit());
		productDTO.setAvailableUnit(updateProductRequest.getAvailableUnit());
		productDTO.setMetadata(updateProductRequest.getMetadata());
		productDTO.setStatus(true);

		ProductDTO updatedProductDTO = productDao.updateProductDetails(productDTO);
		return productMapper.productDTOToProductResponse(updatedProductDTO);
	}

	public ProductResponse deleteProductData(Long productId) {
		Optional<ProductDTO> productDTOOptional = productDao.getProductDetail(productId);

		if (productDTOOptional.isEmpty()) {
			throw new RuntimeException("Provided product id is not present");
		}

		ProductDTO productDTO = productDTOOptional.get();
		productDTO.setStatus(false);
		productDTO = productDao.updateProductDetails(productDTO);

		return productMapper.productDTOToProductResponse(productDTO);
	}

	public ProductResponse uploadProductImage(
			Long storeId, Long productId, MultipartFile multipartFile) throws Exception {

		Optional<ProductDTO> existingProductDetailsOptional = productDao.getProductDetail(productId);

		if (existingProductDetailsOptional.isEmpty()) {
			throw new RuntimeException("Provided product id is not present");
		}


		String filePath = buildImagePath(Objects.requireNonNull(multipartFile.getOriginalFilename()), storeId, productId);
    	Files.createDirectories(Path.of("src/main/resources/" + storeId));
		
		Files.write(Path.of("src/main/resources/" + filePath), multipartFile.getBytes());

		ProductDTO existingProductDetails = existingProductDetailsOptional.get();
		existingProductDetails.setStatus(true);
		existingProductDetails.setImageURL(filePath);
		productDao.updateProductDetails(existingProductDetails);

		return productMapper.productDTOToProductResponse(existingProductDetails);
	}

	public ResponseEntity<InputStreamResource> downloadFile(Long storeId, Long productId) throws IOException {

		Optional<ProductDTO> existingProductDetailsOptional = productDao.getProductDetail(productId);

		if (existingProductDetailsOptional.isEmpty()) {
			throw new RuntimeException("Provided product id is not present");
		}

		ProductDTO existingProductDetails = existingProductDetailsOptional.get();

		InputStream inputStream = new FileInputStream("src/main/resources/" + existingProductDetails.getImageURL());
		InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		headers.setContentLength(Files.size(Paths.get("src/main/resources/" + existingProductDetails.getImageURL())));
		return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
	}

	private String buildImagePath(String originalFileName, Long storeId, Long productId) {
		String[] fileNameParts = originalFileName.split("\\.");
		return storeId + "/" + productId + "." + fileNameParts[1];
	}

	public List<ProductResponse> getAllProducts() {
		return productDao.getAllActiveProducts().stream()
				.map(product -> productMapper.productDTOToProductResponse(product))
				.toList();
	}

	public ProductResponse getSingleProductDetail(Long productId) {
		Optional<ProductDTO> productDTOOptional = productDao.getProductDetail(productId);
		if (productDTOOptional.isEmpty()) {
			throw new RuntimeException("Provided product id is not present");
		}
		return productMapper.productDTOToProductResponse(productDTOOptional.get());

	}
}

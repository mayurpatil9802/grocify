package com.grocify.storemgmt.controller;


import com.grocify.commonlibs.model.request.CreateProductRequest;
import com.grocify.commonlibs.model.request.UpdateProductRequest;
import com.grocify.commonlibs.model.response.ProductResponse;
import com.grocify.storemgmt.service.ProductService;
import java.io.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/store/{storeId}/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponse insertProduct(@PathVariable Long storeId, @RequestBody CreateProductRequest createProductRequest){
        return productService.insertProductData(storeId, createProductRequest);
    }

    @GetMapping("/abc")
    public List<ProductResponse> getAllProductFromStore(@PathVariable Long storeId){
        return productService.getAllProductsFromStore(storeId);
    }
    @GetMapping("/{productId}")
    public ProductResponse getSingleProduct(@PathVariable Long productId){
        return productService.getSingleProductDetail(productId);
    }

    @GetMapping
    public List<ProductResponse> getAllProduct(){
        return productService.getAllProducts();
    }

    @PutMapping("/{productId}")
    public ProductResponse updateProductDetails(@PathVariable Long productId, @RequestBody UpdateProductRequest updateProductRequest){
        return productService.updateProductData(productId , updateProductRequest);
    }

    @DeleteMapping("/{productId}")
    public ProductResponse deleteProduct(@PathVariable Long productId){
        return productService.deleteProductData(productId);
    }

    @PutMapping("/{productId}/image")
    public ProductResponse updateProductImage(@PathVariable Long storeId, @PathVariable Long productId, @RequestPart MultipartFile imageFile) throws Exception {
        return productService.uploadProductImage(storeId, productId, imageFile);
    }

    @GetMapping("/{productId}/image")
    public ResponseEntity<InputStreamResource> downloadImage(@PathVariable Long storeId, @PathVariable Long productId) throws IOException {
        return productService.downloadFile(storeId, productId);
    }


}

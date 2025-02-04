package com.grocify.commonlibs.repository;

import com.grocify.commonlibs.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity , Long> {

    List<ProductEntity> findByStatusAndStoreId(boolean status, Long productId);
    Optional<ProductEntity> findByIdAndStatus(Long productId, boolean status);

}

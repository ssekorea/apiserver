package com.wellness.sseproject.domain.repository;

import com.wellness.sseproject.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    @Query(value = "SELECT * FROM PRODUCT_IMAGE_TB WHERE product_id = :productId", nativeQuery = true)
    List<ProductImage> getProductImagesByProduct(@Param("productId")int productId);

    @Query(value = "SELECT * FROM PRODUCT_IMAGE_TB WHERE product_id >= :minCount && product_id <= :maxCount ORDER BY product_id", nativeQuery = true)
    List<ProductImage> getProductImagesByPage(@Param("minCount")int minCount, @Param("maxCount") int maxCount);

    @Query(value = "SELECT * FROM PRODUCT_IMAGE_TB WHERE product_id = :productId", nativeQuery = true)
    List<ProductImage> getProductImagesByProductId(@Param("productId")int productId);

    @Query(value = "SELECT * FROM PRODUCT_IMAGE_TB WHERE product_id IN :productIdList", nativeQuery = true)
    List<ProductImage> getProductImagesByProductIdList(@Param("productIdList")List<Integer> productIdList);


}

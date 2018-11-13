package com.wellness.sseproject.domain.repository;

import com.wellness.sseproject.domain.Lecture;
import com.wellness.sseproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductId(int productId);

    Product findByProductIdAndMasking(int productId, boolean masking);

    void deleteByProductId(int id);

    @Query(value = "SELECT * FROM PRODUCT_TB WHERE masking = :masking ORDER BY product_id LIMIT :startIndex, :pageCount", nativeQuery = true)
    List<Product> findProductListByPage(@Param("startIndex") int startIndex, @Param("pageCount") int pageCount, @Param("masking") boolean masking);

    @Query(value = "SELECT * FROM PRODUCT_TB WHERE masking = :masking AND product_id IN :productIdList ORDER BY product_id", nativeQuery = true)
    List<Product> findProductListByProductIdListAndMasking(@Param("productIdList")List<Integer> productIdList, @Param("masking")boolean masking);

    @Query(value = "SELECT * FROM PRODUCT_TB WHERE product_id IN :productIdList ORDER BY product_id", nativeQuery = true)
    List<Product> findProductListByProductIdList(@Param("productIdList")List<Integer> productIdList);

}

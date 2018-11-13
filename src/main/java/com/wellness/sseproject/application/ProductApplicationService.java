package com.wellness.sseproject.application;

import com.wellness.sseproject.domain.Product;
import com.wellness.sseproject.domain.ProductImage;
import com.wellness.sseproject.domain.repository.ProductImageRepository;
import com.wellness.sseproject.domain.repository.ProductRepository;
import com.wellness.sseproject.web.controller.dto.ProductDTO;
import com.wellness.sseproject.web.controller.dto.ProductRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductApplicationService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductImageRepository productImageRepository;

    public ProductDTO registerProduct(ProductRegisterDTO productRegisterDTO) {
        Product product = new Product(productRegisterDTO);
        product.setMasking(true);
        Product savedProduct = productRepository.saveAndFlush(product);

        List<ProductImage> productImageList = new ArrayList<>();
        for (String imageUrl : productRegisterDTO.getProductImageUrls()){
            ProductImage productImage = new ProductImage();
            productImage.setProduct(savedProduct);
            productImage.setImageUrl(imageUrl);
            productImageList.add(productImage);
        }
        productImageRepository.saveAll(productImageList);

        return new ProductDTO(savedProduct.getProductId(), productRegisterDTO);
    }

    //@Transactional
    public void deleteProduct(int productId) {
        Product product = productRepository.findByProductId(productId);
        product.setMasking(false);
        productRepository.save(product);

        List<ProductImage> productImages = productImageRepository.getProductImagesByProductId(productId);
        productImageRepository.deleteAll(productImages);
    }

    @Transactional
    public ProductDTO modifyProduct(int productId, ProductDTO productDTO){

        Product product = new Product(productDTO);
        product.setProductId(productId);
        productRepository.save(product);

        List<ProductImage> productImageList = new ArrayList<>();

        for (String productImageUrl : productDTO.getProductImageUrls()){
            ProductImage tempProductImage = new ProductImage();
            tempProductImage.setImageUrl(productImageUrl);
            tempProductImage.setProduct(product);
            productImageList.add(tempProductImage);
        }

        List<ProductImage> tempProductImages = productImageRepository.getProductImagesByProductId(product.getProductId());

        for (ProductImage productImage : tempProductImages){
            productImageRepository.delete(productImage);
        }
        productImageRepository.saveAll(productImageList);

        return productDTO;
    }

    public boolean deleteProductImages(int productId) {
        Product product = productRepository.findByProductIdAndMasking(productId, true);
        List<ProductImage> productImages = productImageRepository.getProductImagesByProductId(productId);
        if (productImages == null){
            return false;
        }
        productImageRepository.deleteAll(productImages);

        return true;
    }
}

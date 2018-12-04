package com.wellness.sseproject.application;

import com.wellness.sseproject.domain.Product;
import com.wellness.sseproject.domain.ProductImage;
import com.wellness.sseproject.domain.repository.ProductImageRepository;
import com.wellness.sseproject.domain.repository.ProductRepository;
import com.wellness.sseproject.web.controller.dto.ProductDTO;
import com.wellness.sseproject.web.controller.dto.ProductImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductQueryService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductImageRepository productImageRepository;


    public ProductDTO getProductDTOById(int productId) {

        Product product = productRepository.findByProductIdAndMasking(productId, true);
        if (product == null) {
            return null;
        }
        List<ProductImage> productImageList = productImageRepository.getProductImagesByProduct(productId);
        ProductDTO productDTO = new ProductDTO(product);
        productDTO.setProductImageUrlsByProductImages(productImageList);

        return productDTO;
    }

    public Product getProductById(int productId) {
        return productRepository.findByProductId(productId);
    }


    public List<ProductDTO> getProductListByPage(int startIndex, int count) {
        int startCount = count * startIndex;

        List<Product> productList = productRepository.findProductListByPage(startCount, count, true);

        if (productList.size() == 0) {
            return null;
        }
        List<Integer> productIdList = new ArrayList<>();
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : productList) {
            productIdList.add(product.getProductId());
            productDTOList.add(new ProductDTO(product));
        }

        List<ProductImage> productImageList = productImageRepository.getProductImagesByProductIdList(productIdList);

        for (ProductDTO productDTO : productDTOList) {
            for (ProductImage productImage : productImageList) {
                if (productImage.getProduct().getProductId() == productDTO.getProductId()) {
                    productDTO.getProductImageUrls().add(productImage.getImageUrl());
                }
            }
        }

        return productDTOList;
    }

    public List<ProductImageDTO> getProductImageDTOListByProductId(int productId) {
        Product product = productRepository.findByProductIdAndMasking(productId, true);
        if (product == null) {
            return null;
        }
        List<ProductImage> productImageList = productImageRepository.getProductImagesByProduct(productId);

        if (productImageList.size() == 0) {
            return null;
        }
        List<ProductImageDTO> productImageDTOList = new ArrayList<>();
        for (ProductImage productImage : productImageList) {
            productImageDTOList.add(new ProductImageDTO(productImage));
        }
        return productImageDTOList;
    }

    public boolean isStockAvailable(int productId, int orderNum){
        Product product = productRepository.findByProductId(productId);
        return product.getStock() >= orderNum;
    }

}

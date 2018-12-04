package com.wellness.sseproject.web.controller;


import com.wellness.sseproject.application.ProductApplicationService;
import com.wellness.sseproject.application.ProductQueryService;
import com.wellness.sseproject.web.controller.dto.ProductDTO;
import com.wellness.sseproject.web.controller.dto.ProductImageDTO;
import com.wellness.sseproject.web.controller.dto.ProductRegisterDTO;
import com.wellness.sseproject.web.controller.message.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductApplicationService productApplicationService;
    @Autowired
    ProductQueryService productQueryService;

    @Value("${server.tomcat.basedir}")
    String baseDir;
    @Value("${server.domain}")
    String domainAddress;
    @Value("${server.port}")
    String portNum;


    //Test Completion
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getProductById(@PathVariable int productId) {

        ProductDTO productDTO = productQueryService.getProductDTOById(productId);
        if (productDTO == null) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.
                    createErrorResponseMessageFactory("Invalid product Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new ProductResponseMessage(productDTO);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);

    }

    //Test Completion
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getProductList(@RequestParam(defaultValue = "0") int startIndex, @RequestParam(defaultValue = "30") int count) {

        List<ProductDTO> productDTOList = productQueryService.getProductListByPage(startIndex, count);
        if (productDTOList == null) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.
                    createErrorResponseMessageFactory("There is No page", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new ProductListResponseMessage(productDTOList);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //Test Completion
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ApiResponseMessage> registerProduct(@RequestBody ProductRegisterDTO productRegisterDTO) {

        ProductDTO productDTO = productApplicationService.registerProduct(productRegisterDTO);
        ApiResponseMessage apiResponseMessage = new ProductResponseMessage(productDTO);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //Test Completion
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponseMessage> deleteProduct(@PathVariable int productId) {

        if (productQueryService.getProductDTOById(productId) == null) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid lecture Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        productApplicationService.deleteProduct(productId);
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setHttpStatusCodeOK();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //Test Complete
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<ApiResponseMessage> modifyProduct(@PathVariable int productId, @RequestBody ProductDTO productDTO) {

        if (productQueryService.getProductDTOById(productId) == null) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid productId Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        productDTO.setProductId(productId);
        ApiResponseMessage apiResponseMessage = new ProductResponseMessage(productApplicationService.modifyProduct(productId, productDTO));
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //Test_DONE
    @RequestMapping(value = "/{productId}/images", method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getProductImageByProductId(@PathVariable int productId) {

        List<ProductImageDTO> productImageDTOList = productQueryService.getProductImageDTOListByProductId(productId);
        if (productImageDTOList == null) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid product Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new ProductImageResponse(productImageDTOList);
        apiResponseMessage.setHttpStatusCodeOK();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //Test_DONE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{productId}/images", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponseMessage> deleteAllProductImagesByLectureId(@PathVariable int productId) {

        if (productQueryService.getProductImageDTOListByProductId(productId).size() == 0) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid lecture Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        productApplicationService.deleteProductImages(productId);
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setHttpStatusCodeOK();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    private String imageFileUpload(MultipartFile file){
        if (file.getName().contains("/")) {
            return null;
        }

        if (!file.isEmpty()) {
            try {
                String pathName = "/images/" + UUID.randomUUID() + "/";
                File dirFile = new File(baseDir + pathName);
                if(!dirFile.exists()){
                    //디렉토리 생성 메서드
                    dirFile.mkdirs();
                    System.out.println("created directory successfully!");
                }
                File writeFile = new File(baseDir + pathName + file.getName());

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(writeFile));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                System.out.println("You successfully uploaded");
                return "http://" + domainAddress + ":" + portNum + pathName;
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("message You Failed to upload");
            }
        }
        else {
            System.out.println("You Failed to upload because it is empty");
        }
        return null;
    }

}

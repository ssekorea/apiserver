package com.wellness.sseproject.web.controller;

import com.wellness.sseproject.web.controller.message.ApiResponseMessage;
import com.wellness.sseproject.web.controller.message.ErrorResponseMessageFactory;
import com.wellness.sseproject.web.controller.message.FilePathResponseMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/files")
public class FileUploadController {

    @Value("${server.tomcat.basedir}")
    String baseDir;
    @Value("${server.domain}")
    String domainAddress;
    @Value("${server.port}")
    String portNum;

    String tomcatPathName = "work/Tomcat/localhost/ROOT/public";

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ApiResponseMessage> uploadFiles(@RequestParam("name") String name,
                                                          @RequestParam("file") MultipartFile file) {
        try{
            System.out.println(new String(file.getOriginalFilename().getBytes("8859_1"), "UTF-8"));
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (name.contains("/")){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Relative pathnames not allowed", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        if (!file.isEmpty()) {
            try {

                String etx = name.substring(name.lastIndexOf('.'));

                String saveFileName = UUID.randomUUID() + etx;

                File serverFile = new File(baseDir + File.separator + saveFileName);
                file.transferTo(serverFile);

                String imageUrl = "http://" + domainAddress + ":" + portNum + File.separator + tomcatPathName + File.separator + saveFileName;
                System.out.println(imageUrl);


                ApiResponseMessage apiResponseMessage = new FilePathResponseMessage(imageUrl);

                return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("message You Failed to upload");
            }
        } else {
            System.out.println("You Failed to upload because it is empty");
        }
        return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Relative pathnames not allowed", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);

    }
}

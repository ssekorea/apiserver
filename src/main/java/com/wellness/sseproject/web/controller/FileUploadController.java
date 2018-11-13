package com.wellness.sseproject.web.controller;

import com.wellness.sseproject.web.controller.message.ApiResponseMessage;
import com.wellness.sseproject.web.controller.message.ErrorResponseMessageFactory;
import com.wellness.sseproject.web.controller.message.FilePathResponseMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ApiResponseMessage> uploadFiles(@RequestParam("name") String name,
                                                          @RequestParam("file") MultipartFile file){

        System.out.println(name);
        if (name.contains("/")) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Relative pathnames not allowed", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
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
                File writeFile = new File(baseDir + pathName + name);

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(writeFile));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                System.out.println("You successfully uploaded");

                String imageUrl = "http://" + domainAddress + ":" + portNum + pathName;

                ApiResponseMessage apiResponseMessage = new FilePathResponseMessage(imageUrl);

                return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("message You Failed to upload");
            }
        }
        else {
            System.out.println("You Failed to upload because it is empty");
        }
        return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Relative pathnames not allowed", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);

    }
}

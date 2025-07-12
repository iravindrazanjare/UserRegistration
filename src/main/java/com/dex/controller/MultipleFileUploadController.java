package com.dex.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dex.models.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping({ "/upi/upload" })
public class MultipleFileUploadController
{
    @PostMapping({ "documents" })
    public ResponseEntity<ResponseMessage> uploadPolicyDocument(@RequestParam("document") List<MultipartFile> multipartFile) {
        String OUT_PATH = "C:\\Documents_and_Photo\\";
        String userId = "\\ABCD\\";
        String message = "";
        try {
            for (MultipartFile mf : multipartFile) {
                byte[] bytes = mf.getBytes();
                Path path = Paths.get(String.valueOf(OUT_PATH) + userId + mf.getOriginalFilename(), new String[0]);
                Files.write(path, bytes, new OpenOption[0]);
            }
            message = "Document Upload Successfully";
        }
        catch (IOException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Upload Failed with Exception"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }
}

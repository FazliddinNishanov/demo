package com.example.demo.web;

import com.example.demo.entitiy.ImageModel;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 23.06.2022
 */
@RestController
@RequestMapping("/api/image")
@CrossOrigin
public class ImageController {

    @Autowired
    private ImageUploadService imageUploadService;


    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadImageToUser(@RequestParam("file") MultipartFile file,
                                                             Principal principal)throws IOException {

        imageUploadService.uploadImageTouser(file, principal);
        return new  ResponseEntity<>(new MessageResponse("Photo is Uploaded"), HttpStatus.OK);
    }

    @PostMapping("/{postId}/upload")
    public ResponseEntity<MessageResponse> uploadImageToPost(@PathVariable("postId") String postId,
                                                             @RequestParam MultipartFile file,
                                                             Principal principal)throws IOException{
        imageUploadService.uploadImageToPost(file,principal,Long.parseLong(postId));
        return new  ResponseEntity<>(new MessageResponse("Photo is Saved"), HttpStatus.OK);
    }
    @GetMapping("/profileImage")
    public ResponseEntity<ImageModel> getImageForUser(Principal principal){
        ImageModel imageToUser = imageUploadService.getImageToUser(principal);
        return new ResponseEntity<>(imageToUser,HttpStatus.OK);
    }

    @GetMapping("/{postId}/image")
    public ResponseEntity<ImageModel> getImageForPost(@PathVariable("postId") String postId){
        ImageModel image = imageUploadService.getImageToPost(Long.parseLong(postId));
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}

package com.example.demo.web;

import com.example.demo.dto.PostDTO;
import com.example.demo.entitiy.Post;
import com.example.demo.facade.PostFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.PostService;
import com.example.demo.validation.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Developed by: Fazliddin Nishanov 
 * Date: 23.06.2022
 */

@RestController
@RequestMapping("/api/post")
@CrossOrigin
public class PostController {
    @Autowired
    private PostFacade postFacade;
    @Autowired
    private PostService postService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody PostDTO postDTO,
                                         BindingResult bindingResult,
                                         Principal principal){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Post post = postService.createrPost(postDTO, principal);
        PostDTO createPost = postFacade.postToPostDto(post);
        return new ResponseEntity<>(createPost, HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        List<PostDTO> postDTOList = postService.getAllPost()
                .stream()
                .map(postFacade::postToPostDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @PostMapping("/user/post")
    public ResponseEntity<List<PostDTO>> getAllPostForUser(Principal principal){
        List<PostDTO> userPosts = postService.getAllForUser(principal)
                .stream()
                .map(postFacade::postToPostDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userPosts, HttpStatus.OK);
    }

    @PostMapping("/{postId}/{username}/like")
    public ResponseEntity<PostDTO> likePost(@PathVariable("postId") String postId,
                                            @PathVariable("username") String username){
        Post post = postService.likedPost(Long.parseLong(postId), username);
        PostDTO postDTO = postFacade.postToPostDto(post);

        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }



    public ResponseEntity<MessageResponse> deletePost(@PathVariable("postId") String postId, Principal principal){
        postService.deletePost(Long.parseLong(postId), principal);
        return new ResponseEntity<>(new MessageResponse("Post was deleted"), HttpStatus.OK);
    }
}

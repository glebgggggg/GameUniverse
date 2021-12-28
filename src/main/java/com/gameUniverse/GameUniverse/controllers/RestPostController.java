package com.gameUniverse.GameUniverse.controllers;

import com.gameUniverse.GameUniverse.requests.DeleteAndFindRequest;
import com.gameUniverse.GameUniverse.requests.EditPostRequest;
import com.gameUniverse.GameUniverse.requests.NewPostRequest;
import com.gameUniverse.GameUniverse.response.PostInfoResponse;
import com.gameUniverse.GameUniverse.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class RestPostController {
    private final PostService postService;

    /*@GetMapping("/post/{id}")
    public ResponseEntity<PostInfoResponse> getBook(final @PathVariable Long id) {
        return new ResponseEntity<>(this.postService.showOne(new DeleteAndFindRequest(id)), HttpStatus.OK);
    }*/

    @GetMapping("/allPosts")
    public ResponseEntity<PostInfoResponse[]> getAllBooks() {
        return new ResponseEntity(
                this.postService.getAll().toArray(new PostInfoResponse[0]),
                HttpStatus.OK
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addNewBook(
            final @Valid @RequestBody NewPostRequest request
    ) {
        this.postService.addPost(request);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<PostInfoResponse> updateBook(
            final @Valid @RequestBody EditPostRequest request
    ) {
        return new ResponseEntity(this.postService.editPost(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PostInfoResponse[]> deleteBook(final @PathVariable Long id) {
        this.postService.deletePost(new DeleteAndFindRequest(id));

        return new ResponseEntity(this.postService.getAll().toArray(new PostInfoResponse[0]), HttpStatus.OK);
    }
}



package com.gameUniverse.GameUniverse.service;

import com.gameUniverse.GameUniverse.entities.Post;
import com.gameUniverse.GameUniverse.exeption.BadRequestException;
import com.gameUniverse.GameUniverse.repositories.PostRepository;
import com.gameUniverse.GameUniverse.requests.DeleteAndFindRequest;
import com.gameUniverse.GameUniverse.requests.EditPostRequest;
import com.gameUniverse.GameUniverse.requests.NewPostRequest;
import com.gameUniverse.GameUniverse.response.PostInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final Scheduler scheduler;
    private final PostRepository postRepository;

    public void addPost(NewPostRequest newPostRequest) {

        Post post = new Post(newPostRequest.getTitle(), newPostRequest.getContent());

        if (post.getTitle().length() < 4) {
            throw new BadRequestException("Title is too short!", HttpStatus.BAD_REQUEST);
        }

        if (post.getContent().length() < 10) {
            throw new BadRequestException("Content is too short!", HttpStatus.BAD_REQUEST);
        }

        postRepository.save(post);
    }

    @Cacheable("postCache")
    public List<PostInfoResponse> getAll() {
        List<Post> list = postRepository.findAll();


        return list.stream().map(post -> new PostInfoResponse(
                post.getTitle(),
                post.getContent(),
                post.getUser().getLogin(),
                post.getPostId()
        )).collect(Collectors.toList());
    }

    public PostInfoResponse editPost(EditPostRequest editPostRequest) {
        Optional<Post> postInDB = postRepository.findById(editPostRequest.getId());

        Post post;

        if(postInDB.isPresent()){
            post = postInDB.get();
            post.setTitle(editPostRequest.getTitle());
            post.setContent(editPostRequest.getContent());
        }else{
            throw new BadRequestException("No post with such id!", HttpStatus.BAD_REQUEST);
        }

        postRepository.save(post);
        return new PostInfoResponse(post.getTitle(), post.getContent(), post.getUser().getLogin(), post.getPostId());
    }

    public void deletePost(DeleteAndFindRequest deleteAndFindRequest) {

        postRepository.deleteById(deleteAndFindRequest.getId());

        scheduler.clearCache();
    }

    /*@Cacheable("postCache")
    public PostInfoResponse showOne(DeleteAndFindRequest deleteAndFindRequest) {
        Optional<Post> postInOptional = postRepository.findById(deleteAndFindRequest.getId());

        if (postInOptional.isPresent()) {
            Post post = postInOptional.get();
            return new PostInfoResponse(post.getTitle(), post.getContent(), post.getUser().getLogin(), deleteAndFindRequest.getId());
        } else {
            throw new BadRequestException("No post with such id!", HttpStatus.BAD_REQUEST);
        }
    }*/
}

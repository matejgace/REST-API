package mg.RESTAPI.service;

import mg.RESTAPI.dtos.PostDto;

import java.util.ArrayList;
import java.util.List;

public interface PostService {


    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePost(long id);

}

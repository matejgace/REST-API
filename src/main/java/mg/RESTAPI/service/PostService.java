package mg.RESTAPI.service;

import mg.RESTAPI.dtos.PostDto;
import mg.RESTAPI.dtos.PostResponse;

import java.util.ArrayList;
import java.util.List;

public interface PostService {


    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePost(long id);

}

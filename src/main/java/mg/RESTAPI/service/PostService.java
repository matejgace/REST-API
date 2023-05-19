package mg.RESTAPI.service;

import mg.RESTAPI.dtos.PostDto;
import mg.RESTAPI.dtos.PostResponse;


public interface PostService {


    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(Long id);

}

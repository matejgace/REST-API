package mg.RESTAPI.service.impl;

import mg.RESTAPI.dtos.CommentDto;
import mg.RESTAPI.entity.Comment;
import mg.RESTAPI.entity.Post;
import mg.RESTAPI.exception.BlogAPIException;
import mg.RESTAPI.exception.ResourceNotFoundException;
import mg.RESTAPI.repositories.CommentRepository;
import mg.RESTAPI.repositories.PostRepository;
import mg.RESTAPI.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private ModelMapper mapper;

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(ModelMapper mapper, CommentRepository commentRepository, PostRepository postRepository) {
        this.mapper = mapper;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto){

        //convert Dto entity
        Comment comment = mapToEntity(commentDto);

        //retrieve post entity by id

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        //set post to comment entity
        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);

        //convert to Dto
        //CommentDto commentResponse = mapToDto(newComment);

        //return commentResponse;

        return mapToDto(newComment);

    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {

        //retrieve comment by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {

        //retrieve post entity by id

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        //retrieve comment by id

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {

        //retrieve post entity by id

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        //retrieve comment by id

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        comment.setName(commentRequest.getName());
        comment.setBody(commentRequest.getBody());
        comment.setEmail(commentRequest.getEmail());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        //retrieve post entity by id

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        //retrieve comment by id

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));
        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

       commentRepository.delete(comment);
    }


//    private CommentDto mapToDto(Comment comment){
//
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setBody(comment.getBody());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setName(comment.getName());
//
//        return commentDto;
//    }

    private CommentDto mapToDto(Comment comment){

        CommentDto commentDto = mapper.map(comment, CommentDto.class);
        return commentDto;
    }

//    private Comment mapToEntity (CommentDto commentDto){
//
//        Comment comment = new Comment();
//        comment.setId(comment.getId());
//        comment.setBody(commentDto.getBody());
//        comment.setEmail(commentDto.getEmail());
//        comment.setName(commentDto.getName());
//
//        return comment;
//    }

    private Comment mapToEntity (CommentDto commentDto){

        Comment comment = mapper.map(commentDto, Comment.class);
        return comment;
    }
}

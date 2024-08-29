package com.example.demo.web;

import com.example.demo.model.dto.CommentViewDto;
import com.example.demo.model.dto.NewCommentDto;
import com.example.demo.model.validation.ApiError;
import com.example.demo.service.BlogService;
import com.example.demo.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class BlogRestController {
    private final BlogService blogService;
    private final CommentService commentService;


    public BlogRestController(BlogService blogService, CommentService commentService) {
        this.blogService = blogService;
        this.commentService = commentService;
    }

    @GetMapping("/api/comments/{id}")
    public ResponseEntity<List<CommentViewDto>> getComments(
            @PathVariable Long id,
            Principal principal
    ) {
        return ResponseEntity.ok(
                commentService.getComments(id));
    }

    @PostMapping("/api/comments/{id}")
    public ResponseEntity<CommentViewDto> newComment(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long id,
            @RequestBody @Valid NewCommentDto newCommentDto
    ) {
        newCommentDto.setUser(principal.getUsername());
        newCommentDto.setBlogTitle(blogService.findById(id).getTitle());

        CommentViewDto newComment =
                commentService.createComment(newCommentDto);


        URI locationOfNewComment =
                URI.create(String.format("/api/comments/%s/%s", id, newComment.getCommentId()));

        return ResponseEntity.
                created(locationOfNewComment).
                body(newComment);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe ->
                apiError.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(apiError);
    }
}

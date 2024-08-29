package com.example.demo.service;

import com.example.demo.model.dto.CommentViewDto;
import com.example.demo.model.dto.NewCommentDto;

import java.util.List;

public interface CommentService {
    List<CommentViewDto> getComments(Long blogId);

    CommentViewDto createComment(NewCommentDto newCommentDto);
}

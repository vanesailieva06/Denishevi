package com.example.demo.service.impl;

import com.example.demo.model.dto.CommentViewDto;
import com.example.demo.model.dto.NewCommentDto;
import com.example.demo.model.entity.Comment;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper, UserRepository userRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
    }

    @Override
    public List<CommentViewDto> getComments(Long blogId) {
        List<CommentViewDto> collect = commentRepository.findAllByBlog_Id(blogId).stream().map(
                comment -> {
                    CommentViewDto commentViewDto = modelMapper.map(comment, CommentViewDto.class);
                    commentViewDto.setUser(comment.getUser().getUsername());
                    commentViewDto.setMessage(comment.getMessage());
                    return commentViewDto;
                }
        ).collect(Collectors.toList());
        return collect;
    }

    @Override
    public CommentViewDto createComment(NewCommentDto newCommentDto) {
        Comment comment = modelMapper.map(newCommentDto, Comment.class);
        comment.setUser(userRepository.findByUsername(newCommentDto.getUser()).orElseThrow());
        comment.setBlog(blogRepository.findByTitle(newCommentDto.getBlogTitle()).orElseThrow());
        comment.setMessage(newCommentDto.getMessage());
        comment.setCreated(LocalDateTime.now());
        Comment save = commentRepository.save(comment);
        CommentViewDto map = modelMapper.map(save, CommentViewDto.class);
        map.setUser(newCommentDto.getUser());
        map.setMessage(newCommentDto.getMessage());
        return map;
    }
}

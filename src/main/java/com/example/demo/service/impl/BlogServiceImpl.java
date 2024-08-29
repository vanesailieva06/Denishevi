package com.example.demo.service.impl;

import com.example.demo.model.dto.BlogAddDto;
import com.example.demo.model.dto.BlogViewDto;
import com.example.demo.model.entity.Blog;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BlogService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    public BlogServiceImpl(BlogRepository blogRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public BlogViewDto findById(Long blogId) {
        return modelMapper.map(blogRepository.findById(blogId).orElseThrow(), BlogViewDto.class);
    }

    @Override
    public List<BlogViewDto> getAll() {
        return blogRepository.findAll().stream().map(
                blog -> modelMapper.map(blog, BlogViewDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public void addBlog(BlogAddDto blogAddDto, UserDetails userDetails) {
        Blog blog = modelMapper.map(blogAddDto, Blog.class);
        blog.setUser(userRepository.findByUsername(userDetails.getUsername()).orElseThrow());
        blogRepository.save(blog);
    }
}

package com.example.demo.service;

import com.example.demo.model.dto.BlogAddDto;
import com.example.demo.model.dto.BlogViewDto;
import com.example.demo.model.entity.Blog;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface BlogService {
    BlogViewDto findById(Long blogId);

    List<BlogViewDto> getAll();

    void addBlog(BlogAddDto blogAddDto, UserDetails userDetails);
}

package com.example.demo.service;

import com.example.demo.model.dto.GalleryViewDto;

import java.util.List;

public interface GalleryService {
    List<GalleryViewDto> getAllPictures();
}

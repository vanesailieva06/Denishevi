package com.example.demo.service.impl;

import com.example.demo.model.dto.GalleryViewDto;
import com.example.demo.repository.GalleryRepository;
import com.example.demo.service.GalleryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryServiceImpl implements GalleryService {
    private final GalleryRepository galleryRepository;
    private final ModelMapper modelMapper;

    public GalleryServiceImpl(GalleryRepository galleryRepository, ModelMapper modelMapper) {
        this.galleryRepository = galleryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GalleryViewDto> getAllPictures() {
        return galleryRepository.findAll().stream().map(
                gallery -> modelMapper.map(gallery, GalleryViewDto.class)
        ).collect(Collectors.toList());
    }
}

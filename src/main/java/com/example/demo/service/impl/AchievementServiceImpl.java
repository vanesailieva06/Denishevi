package com.example.demo.service.impl;

import com.example.demo.model.dto.AchievementViewDto;
import com.example.demo.model.entity.Achievement;
import com.example.demo.model.entity.Image;
import com.example.demo.repository.AchievementRepository;
import com.example.demo.service.AchievementService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepository achievementRepository;
    private final ModelMapper modelMapper;

    public AchievementServiceImpl(AchievementRepository achievementRepository, ModelMapper modelMapper) {
        this.achievementRepository = achievementRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AchievementViewDto> getAll() {
        return achievementRepository.findAll().stream().map(
                achievement -> {
                    AchievementViewDto achievementViewDto = modelMapper.map(achievement, AchievementViewDto.class);
                    List<String> imageUrl = new ArrayList<>();
                    for (Image image : achievement.getImageUrl()) {
                        imageUrl.add(image.getUrl());
                    }
                    achievementViewDto.setImages(imageUrl);
                    return achievementViewDto;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public AchievementViewDto findById(Long id) {
        Achievement achievement = achievementRepository.findById(id).orElseThrow();
        AchievementViewDto achievementViewDto
                = modelMapper.map(achievement, AchievementViewDto.class);
        List<String> imageUrl = new ArrayList<>();
        for (Image image : achievement.getImageUrl()) {
            imageUrl.add(image.getUrl());
        }
        achievementViewDto.setImages(imageUrl);

        return achievementViewDto;
    }
}

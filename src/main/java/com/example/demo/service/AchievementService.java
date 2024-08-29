package com.example.demo.service;

import com.example.demo.model.dto.AchievementViewDto;

import java.util.List;

public interface AchievementService {
    List<AchievementViewDto> getAll();

    AchievementViewDto findById(Long id);
}

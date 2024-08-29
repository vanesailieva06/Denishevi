package com.example.demo.web;

import com.example.demo.service.AchievementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AchievementsController {

    private final AchievementService achievementService;

    public AchievementsController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping("/achievements")
    public String getAchievements(Model model){
        model.addAttribute("achievements", achievementService.getAll());
        return "achievements";
    }

    @GetMapping("/achievement/{id}")
    private String getAchievementDetails(@PathVariable Long id, Model model){
        model.addAttribute("achievementDetails", achievementService.findById(id));

        return "achievment-page";
    }
}

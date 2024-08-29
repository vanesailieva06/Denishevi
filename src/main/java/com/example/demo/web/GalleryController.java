package com.example.demo.web;

import com.example.demo.service.GalleryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GalleryController {
    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping("/gallery")
    public String getGallery(Model model){
        model.addAttribute("pictures", galleryService.getAllPictures());
        return "gallery";
    }
}

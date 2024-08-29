package com.example.demo.model.entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "achievments")
public class Achievement extends BaseEntity{
    private String description;
    private String title;
    private List<Image> imageUrl;

    public Achievement() {

    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @OneToMany(mappedBy = "achievement", fetch = FetchType.EAGER)
    public List<Image> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<Image> imageUrl) {
        this.imageUrl = imageUrl;
    }
    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.example.demo.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "gallery")
public class Gallery extends BaseEntity{
    private User user;
    private String url;

    public Gallery() {
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

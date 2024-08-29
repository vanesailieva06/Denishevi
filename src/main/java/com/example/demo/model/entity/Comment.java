package com.example.demo.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    private String message;
    private LocalDateTime created;
    private User user;
    private Blog blog;

    public Comment() {
    }

    @Column()
    public String getMessage() {
        return message;
    }

    public void setMessage(String description) {
        this.message = description;
    }


    @Column
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog forum) {
        this.blog = forum;
    }
}

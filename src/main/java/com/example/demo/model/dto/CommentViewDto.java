package com.example.demo.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommentViewDto {
    private String message;
    private Long commentId;
    private String user;
    private LocalDateTime created;

    public CommentViewDto() {
    }

    public CommentViewDto(String message, Long commentId, String user, LocalDateTime created) {
        this.message = message;
        this.commentId = commentId;
        this.user = user;
        this.created = created;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}

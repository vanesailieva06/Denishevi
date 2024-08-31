package com.example.demo.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserRegisterDto() {
    }

    @NotEmpty(message = "Потребителското име не може да е празно")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @NotEmpty(message = "Паролата не може да е празна")
    @Size(min = 4, max = 20, message = "Паролата трябва да е между 4 и 20 знака")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = "Паролата не може да е празна")
    @Size(min = 4, max = 20, message = "Паролата трябва да е между 4 и 20 знака")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotEmpty
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

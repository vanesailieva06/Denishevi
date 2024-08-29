package com.example.demo.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{
    private RoleType roleType;

    public Role() {
    }

    @Enumerated(EnumType.STRING)
    public RoleType getRoleType() {
        return roleType;
    }


    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}


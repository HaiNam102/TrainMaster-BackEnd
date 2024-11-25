package com.example.springmvc.entity.Login;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int role_id;

    @Column(name = "rolename", length = 50)
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<Account> accounts;

    public Role() {
    }

    public Role(int role_id, String roleName) {
        this.role_id = role_id;
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRole_name(String role_name) {
        this.roleName = roleName;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}

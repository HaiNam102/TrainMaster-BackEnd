package com.example.springmvc.entity.Login;

import com.example.springmvc.entity.*;
import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", length = 36)
    private int accountId;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    //Relationship for All table

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Owner owner;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private FitnessManager fitnessManager;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PersonalTrainer personalTrainer;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Client client;

    public Account() {
    }

    public Account(int accountId, String username, String password, boolean enabled, Role role) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
//
//        public FitnessManager getFitnessManager() {
//            return fitnessManager;
//        }
//
//        public void setFitnessManager(FitnessManager fitnessManager) {
//            this.fitnessManager = fitnessManager;
//        }
//
//        public PersonalTrainer getPersonalTrainer() {
//            return personalTrainer;
//        }
//
//        public void setPersonalTrainer(PersonalTrainer personalTrainer) {
//            this.personalTrainer = personalTrainer;
//        }
//
//        public Client getClient() {
//            return client;
//        }
//
//        public void setClient(Client client) {
//            this.client = client;
//        }
}
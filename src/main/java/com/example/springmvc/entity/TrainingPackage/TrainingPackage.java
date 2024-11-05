package com.example.springmvc.entity.TrainingPackage;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "training_package")
public class TrainingPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    private int package_id;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "cost")
    private float cost;

    @Column(name = "sessionnumber")
    private int sessionNumber;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "trainingPackage",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<TrainingPackageOfClient> trainingPackageOfClient;

    public TrainingPackage() {
    }

    public TrainingPackage(int package_id, String packageName, float cost, int sessionNumber, String description) {
        this.package_id = package_id;
        this.packageName = packageName;
        this.cost = cost;
        this.sessionNumber = sessionNumber;
        this.description = description;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

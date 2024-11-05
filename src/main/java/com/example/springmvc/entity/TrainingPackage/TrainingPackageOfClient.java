package com.example.springmvc.entity.TrainingPackage;

import com.example.springmvc.entity.Client;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "training_package_of_clients")
public class TrainingPackageOfClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_package_of_clients_id")
    private int trainingPackageOfClientsId;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private TrainingPackage trainingPackage;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "startdate")
    private Date startDate;

    @Column(name = "remainsession")
    private int remainSession;

    public TrainingPackageOfClient() {
    }

    public TrainingPackageOfClient(int trainingPackageOfClientsId, int remainSession, Date startDate) {
        this.trainingPackageOfClientsId = trainingPackageOfClientsId;
        this.remainSession = remainSession;
        this.startDate = startDate;
    }

    public int getTrainingPackageOfClientsId() {
        return trainingPackageOfClientsId;
    }

    public void setTrainingPackageOfClientsId(int trainingPackageOfClientsId) {
        this.trainingPackageOfClientsId = trainingPackageOfClientsId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getRemainSession() {
        return remainSession;
    }

    public void setRemainSession(int remainSession) {
        this.remainSession = remainSession;
    }
}

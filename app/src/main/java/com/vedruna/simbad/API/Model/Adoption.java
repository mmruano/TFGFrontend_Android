package com.vedruna.simbad.API.Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Adoption implements Serializable {
    private Long adoptionId;
    private User user;
    private Animal animal;
    private Refuge refuge;
    private LocalDate startDate;
    private LocalDate changeDate;
    private String statusAdoption;

    public Adoption() {
    }

    public Adoption(Long adoptionId, User user, Animal animal, Refuge refuge,
                    LocalDate startDate, LocalDate changeDate, String statusAdoption) {
        this.adoptionId = adoptionId;
        this.user = user;
        this.animal = animal;
        this.refuge = refuge;
        this.startDate = startDate;
        this.changeDate = changeDate;
        this.statusAdoption = statusAdoption;
    }

    public Long getAdoptionId() {
        return adoptionId;
    }

    public void setAdoptionId(Long adoptionId) {
        this.adoptionId = adoptionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Refuge getRefuge() {
        return refuge;
    }

    public void setRefuge(Refuge refuge) {
        this.refuge = refuge;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public String getStatusAdoption() {
        return statusAdoption;
    }

    public void setStatusAdoption(String statusAdoption) {
        this.statusAdoption = statusAdoption;
    }
}

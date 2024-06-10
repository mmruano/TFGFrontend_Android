package com.vedruna.simbad.API.Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Animal implements Serializable {

    private Long animalId;
    private Refuge refuge;
    private String name;
    private String gender;
    private String age;
    private LocalDate birthDate;
    private String species;
    private String size;
    private String energyLevel;
    private String statAdoption;
    private String health;
    private String description;
    private LocalDate entryDate;
    private LocalDate departureDate;
    private String photo;

    public Animal() {
    }

    public Animal(Long animalId, Refuge refuge, String name, String gender, String age, LocalDate birthDate,
                  String species, String size, String energyLevel, String statAdoption, String health,
                  String description, LocalDate entryDate, LocalDate departureDate, String photo) {
        this.animalId = animalId;
        this.refuge = refuge;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.birthDate = birthDate;
        this.species = species;
        this.size = size;
        this.energyLevel = energyLevel;
        this.statAdoption = statAdoption;
        this.health = health;
        this.description = description;
        this.entryDate = entryDate;
        this.departureDate = departureDate;
        this.photo = photo;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Refuge getRefuge() {
        return refuge;
    }

    public void setRefuge(Refuge refuge) {
        this.refuge = refuge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(String energyLevel) {
        this.energyLevel = energyLevel;
    }

    public String getStatAdoption() {
        return statAdoption;
    }

    public void setStatAdoption(String statAdoption) {
        this.statAdoption = statAdoption;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

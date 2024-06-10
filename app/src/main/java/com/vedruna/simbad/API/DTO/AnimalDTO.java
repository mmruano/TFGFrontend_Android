package com.vedruna.simbad.API.DTO;

import com.vedruna.simbad.API.Model.Animal;

import java.io.Serializable;

public class AnimalDTO implements Serializable {
    private Long id;
    private String name;
    private String province;
    private String gender;
    private String age;
    private String birthDate;
    private String species;
    private String size;
    private String energyLevel;
    private String statAdoption;
    private String health;
    private String description;
    private String entryDate;
    private String departureDate;
    private String photo;
    private Long refugeId;
    private String refugeName;
    private String refugeStreet;
    private String refugeEmail;
    private String refugeProvince;
    private String refugePhone;
    private String refugeCodePost;

    public AnimalDTO() {
    }

    public AnimalDTO(Animal animal) {
        this.id = animal.getAnimalId();
        this.name = animal.getName();
        this.province = animal.getRefuge().getProvince();
        this.gender = animal.getGender();
        this.age = animal.getAge();
        this.birthDate = String.valueOf(animal.getBirthDate());
        this.species = animal.getSpecies();
        this.size = animal.getSize();
        this.energyLevel = animal.getEnergyLevel();
        this.statAdoption = animal.getStatAdoption();
        this.health = animal.getHealth();
        this.description = animal.getDescription();
        this.entryDate = String.valueOf(animal.getEntryDate());
        this.departureDate = String.valueOf(animal.getDepartureDate());
        this.photo = animal.getPhoto();
        this.refugeId = animal.getRefuge().getRefugeId();
        this.refugeName = animal.getRefuge().getName();
        this.refugeStreet = animal.getRefuge().getStreet();
        this.refugeEmail = animal.getRefuge().getEmail();
        this.refugeProvince = animal.getRefuge().getProvince();
        this.refugePhone = animal.getRefuge().getPhone();
        this.refugeCodePost = animal.getRefuge().getPostCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
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

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getRefugeId() {
        return refugeId;
    }

    public void setRefugeId(Long refugeId) {
        this.refugeId = refugeId;
    }

    public String getRefugeName() {
        return refugeName;
    }

    public void setRefugeName(String refugeName) {
        this.refugeName = refugeName;
    }

    public String getRefugeStreet() {
        return refugeStreet;
    }

    public void setRefugeStreet(String refugeStreet) {
        this.refugeStreet = refugeStreet;
    }

    public String getRefugeEmail() {
        return refugeEmail;
    }

    public void setRefugeEmail(String refugeEmail) {
        this.refugeEmail = refugeEmail;
    }

    public String getRefugeProvince() {
        return refugeProvince;
    }

    public void setRefugeProvince(String refugeProvince) {
        this.refugeProvince = refugeProvince;
    }

    public String getRefugePhone() {
        return refugePhone;
    }

    public void setRefugePhone(String refugePhone) {
        this.refugePhone = refugePhone;
    }

    public String getRefugeCodePost() {
        return refugeCodePost;
    }

    public void setRefugeCodePost(String refugeCodePost) {
        this.refugeCodePost = refugeCodePost;
    }
}

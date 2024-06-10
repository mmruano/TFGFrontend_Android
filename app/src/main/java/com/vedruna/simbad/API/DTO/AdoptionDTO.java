package com.vedruna.simbad.API.DTO;

import com.vedruna.simbad.API.Model.Adoption;

import java.io.Serializable;
import java.time.LocalDate;

public class AdoptionDTO implements Serializable {
    private Long id;
    private String userName;
    private String surname;
    private String province;
    private String email;
    private String phone;
    private String postCode;
    private String startDate;
    private String changeDate;
    private String statusAdoption;
    private String animalName;
    private String animalProvince;
    private String animalGender;
    private String animalAge;
    private String animalBirthDate;
    private String animalSpecies;
    private String animalSize;
    private String animalEnergyLevel;
    private String animalStatAdoption;
    private String animalHealth;
    private String animalDescription;
    private String animalEntryDate;
    private String animalDepartureDate;
    private String animalPhoto;
    private Long animalRefugeId;
    private String animalRefugeName;
    private String animalRefugeStreet;
    private String animalRefugeEmail;
    private String animalRefugeProvince;
    private String animalRefugePhone;
    private String animalRefugeCodePost;

    public AdoptionDTO() {
    }

    public AdoptionDTO(Adoption adoption) {
        this.id = adoption.getAdoptionId();
        this.userName = adoption.getUser().getName();
        this.surname = adoption.getUser().getSurname();
        this.province = adoption.getUser().getProvince();
        this.email = adoption.getUser().getEmail();
        this.phone = adoption.getUser().getPhone();
        this.postCode = adoption.getUser().getPostCode();
        this.startDate = String.valueOf(adoption.getStartDate());
        this.changeDate = String.valueOf(adoption.getChangeDate());
        this.statusAdoption = adoption.getStatusAdoption();
        this.animalPhoto = adoption.getAnimal().getPhoto();
        this.animalName = adoption.getAnimal().getName();
        this.animalProvince = adoption.getRefuge().getProvince();
        this.animalGender = adoption.getAnimal().getGender();
        this.animalAge = adoption.getAnimal().getAge();
        this.animalBirthDate = String.valueOf(adoption.getAnimal().getBirthDate());
        this.animalSpecies = adoption.getAnimal().getSpecies();
        this.animalSize = adoption.getAnimal().getSize();
        this.animalEnergyLevel = adoption.getAnimal().getEnergyLevel();
        this.animalStatAdoption = adoption.getAnimal().getStatAdoption();
        this.animalHealth = adoption.getAnimal().getHealth();
        this.animalDescription = adoption.getAnimal().getDescription();
        this.animalEntryDate = String.valueOf(adoption.getAnimal().getEntryDate());
        this.animalDepartureDate = String.valueOf(adoption.getAnimal().getDepartureDate());
        this.animalRefugeId = adoption.getRefuge().getRefugeId();
        this.animalRefugeName = adoption.getRefuge().getName();
        this.animalRefugeStreet = adoption.getRefuge().getStreet();
        this.animalRefugeEmail = adoption.getRefuge().getEmail();
        this.animalRefugeProvince = adoption.getRefuge().getProvince();
        this.animalRefugePhone = adoption.getRefuge().getPhone();
        this.animalRefugeCodePost = adoption.getRefuge().getPostCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getStatusAdoption() {
        return statusAdoption;
    }

    public void setStatusAdoption(String statusAdoption) {
        this.statusAdoption = statusAdoption;
    }

    public String getAnimalPhoto() {
        return animalPhoto;
    }

    public void setAnimalPhoto(String animalPhoto) {
        this.animalPhoto = animalPhoto;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalProvince() {
        return animalProvince;
    }

    public void setAnimalProvince(String animalProvince) {
        this.animalProvince = animalProvince;
    }

    public String getAnimalGender() {
        return animalGender;
    }

    public void setAnimalGender(String animalGender) {
        this.animalGender = animalGender;
    }

    public String getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(String animalAge) {
        this.animalAge = animalAge;
    }

    public String getAnimalBirthDate() {
        return animalBirthDate;
    }

    public void setAnimalBirthDate(String animalBirthDate) {
        this.animalBirthDate = animalBirthDate;
    }

    public String getAnimalSpecies() {
        return animalSpecies;
    }

    public void setAnimalSpecies(String animalSpecies) {
        this.animalSpecies = animalSpecies;
    }

    public String getAnimalSize() {
        return animalSize;
    }

    public void setAnimalSize(String animalSize) {
        this.animalSize = animalSize;
    }

    public String getAnimalEnergyLevel() {
        return animalEnergyLevel;
    }

    public void setAnimalEnergyLevel(String animalEnergyLevel) {
        this.animalEnergyLevel = animalEnergyLevel;
    }

    public String getAnimalStatAdoption() {
        return animalStatAdoption;
    }

    public void setAnimalStatAdoption(String animalStatAdoption) {
        this.animalStatAdoption = animalStatAdoption;
    }

    public String getAnimalHealth() {
        return animalHealth;
    }

    public void setAnimalHealth(String animalHealth) {
        this.animalHealth = animalHealth;
    }

    public String getAnimalDescription() {
        return animalDescription;
    }

    public void setAnimalDescription(String animalDescription) {
        this.animalDescription = animalDescription;
    }

    public String getAnimalEntryDate() {
        return animalEntryDate;
    }

    public void setAnimalEntryDate(String animalEntryDate) {
        this.animalEntryDate = animalEntryDate;
    }

    public String getAnimalDepartureDate() {
        return animalDepartureDate;
    }

    public void setAnimalDepartureDate(String animalDepartureDate) {
        this.animalDepartureDate = animalDepartureDate;
    }

    public Long getAnimalRefugeId() {
        return animalRefugeId;
    }

    public void setAnimalRefugeId(Long animalRefugeId) {
        this.animalRefugeId = animalRefugeId;
    }

    public String getAnimalRefugeName() {
        return animalRefugeName;
    }

    public void setAnimalRefugeName(String animalRefugeName) {
        this.animalRefugeName = animalRefugeName;
    }

    public String getAnimalRefugeStreet() {
        return animalRefugeStreet;
    }

    public void setAnimalRefugeStreet(String animalRefugeStreet) {
        this.animalRefugeStreet = animalRefugeStreet;
    }

    public String getAnimalRefugeEmail() {
        return animalRefugeEmail;
    }

    public void setAnimalRefugeEmail(String animalRefugeEmail) {
        this.animalRefugeEmail = animalRefugeEmail;
    }

    public String getAnimalRefugeProvince() {
        return animalRefugeProvince;
    }

    public void setAnimalRefugeProvince(String animalRefugeProvince) {
        this.animalRefugeProvince = animalRefugeProvince;
    }

    public String getAnimalRefugePhone() {
        return animalRefugePhone;
    }

    public void setAnimalRefugePhone(String animalRefugePhone) {
        this.animalRefugePhone = animalRefugePhone;
    }

    public String getAnimalRefugeCodePost() {
        return animalRefugeCodePost;
    }

    public void setAnimalRefugeCodePost(String animalRefugeCodePost) {
        this.animalRefugeCodePost = animalRefugeCodePost;
    }
}

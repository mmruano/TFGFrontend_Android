package com.vedruna.simbad.API.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Refuge implements Serializable {
    private Long refugeId;
    private String name;
    private String typeRefuge;
    private String cif;
    private String street;
    private String province;
    private String postCode;
    private String phone;
    private String email;
    private String password;
    private LocalDate creationDate;
    private List<Animal> animals;
    private List<Adoption> adoptions;

    public Refuge() {
    }

    public Refuge(Long refugeId, String name, String typeRefuge, String cif, String street, String province,
                  String postCode, String phone, String email, String password, LocalDate creationDate,
                  List<Animal> animals, List<Adoption> adoptions) {
        this.refugeId = refugeId;
        this.name = name;
        this.typeRefuge = typeRefuge;
        this.cif = cif;
        this.street = street;
        this.province = province;
        this.postCode = postCode;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
        this.animals = animals;
        this.adoptions = adoptions;
    }

    public Long getRefugeId() {
        return refugeId;
    }

    public void setRefugeId(Long refugeId) {
        this.refugeId = refugeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeRefuge() {
        return typeRefuge;
    }

    public void setTypeRefuge(String typeRefuge) {
        this.typeRefuge = typeRefuge;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Adoption> getAdoptions() {
        return adoptions;
    }

    public void setAdoptions(List<Adoption> adoptions) {
        this.adoptions = adoptions;
    }
}

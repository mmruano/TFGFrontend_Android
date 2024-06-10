package com.vedruna.simbad.API.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class User implements Serializable {
    private Long userId;
    private String name;
    private String surname;
    private String gender;
    private String province;
    private String postCode;
    private String email;
    private String phone;
    private String password;
    private LocalDate creationDate;
    private List<Adoption> adoptions;

    public User() {
    }

    public User(Long userId, String name, String surname, String gender, String province,
                String postCode, String email, String phone, String password, LocalDate creationDate,
                List<Adoption> adoptions) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.province = province;
        this.postCode = postCode;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.creationDate = creationDate;
        this.adoptions = adoptions;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public List<Adoption> getAdoptions() {
        return adoptions;
    }

    public void setAdoptions(List<Adoption> adoptions) {
        this.adoptions = adoptions;
    }
}

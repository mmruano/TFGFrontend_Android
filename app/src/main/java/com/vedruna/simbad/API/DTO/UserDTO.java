package com.vedruna.simbad.API.DTO;

import com.vedruna.simbad.API.Model.User;

import java.io.Serializable;
import java.time.LocalDate;

public class UserDTO implements Serializable {
    private String name;
    private String surname;
    private String gender;
    private String province;
    private String postCode;
    private String email;
    private String phone;
    private String creationDate;

    public UserDTO() {

    }

    public UserDTO(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.gender = user.getGender();
        this.province = user.getProvince();
        this.postCode = user.getPostCode();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.creationDate = String.valueOf(user.getCreationDate());
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}

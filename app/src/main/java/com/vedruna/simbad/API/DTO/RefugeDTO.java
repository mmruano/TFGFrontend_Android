package com.vedruna.simbad.API.DTO;

import com.vedruna.simbad.API.Model.Refuge;

import java.io.Serializable;
import java.time.LocalDate;

public class RefugeDTO implements Serializable {
    private String name;
    private String typeRefuge;
    private String cif;
    private String street;
    private String province;
    private String postCode;
    private String phone;
    private String email;
    private LocalDate createDate;

    public RefugeDTO() {

    }

    public RefugeDTO(Refuge refuge) {
        this.name = refuge.getName();
        this.typeRefuge = refuge.getTypeRefuge();
        this.cif = refuge.getCif();
        this.street = refuge.getStreet();
        this.province = refuge.getProvince();
        this.postCode = refuge.getPostCode();
        this.phone = refuge.getPhone();
        this.email = refuge.getEmail();
        this.createDate = refuge.getCreationDate();
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}

package com.vedruna.simbad.API.Security;

public class AuthResponse {
    private String token;
    private String message;
    private String userType;

    public AuthResponse() {
    }

    public AuthResponse(String token, String message, String userType) {
        this.token = token;
        this.message = message;
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

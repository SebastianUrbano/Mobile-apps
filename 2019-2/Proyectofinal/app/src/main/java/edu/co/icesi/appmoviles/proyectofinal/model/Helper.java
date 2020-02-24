package edu.co.icesi.appmoviles.proyectofinal.model;

public class Helper {

    public final static String HELPER = "HELPER";
    public final static String USER = "USER";

    private String uid;
    private String name;
    private String email;
    private String userType;
    private String password;
    private String specialty;

    public Helper() {
    }

    public Helper(String uid, String name, String email, String userType, String password, String specialty) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.password = password;
        this.specialty = specialty;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}

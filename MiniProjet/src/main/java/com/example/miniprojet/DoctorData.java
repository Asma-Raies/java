package com.example.miniprojet;

public class DoctorData {
    private Integer code ;
    private String fullName ;
    private  String adresse ;
    private String password ;
    private  String specialite ;
    private String service ;
    private String mobile ;

    public DoctorData(Integer code, String fullName, String adresse, String password, String specialite, String service, String mobile) {
        this.code = code;
        this.fullName = fullName;
        this.adresse = adresse;
        this.password = password;
        this.specialite = specialite;
        this.service = service;
        this.mobile = mobile;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

package com.example.miniprojet;

import java.util.Date;

public class PatientData {
    private Integer patientId ;
    private String password ;
    private String fullName ;
    private String description ;
    private String Diagnostic ;
    private String  mobile ;
    private  String adresse ;
    private Date date ;

    public PatientData(Integer patientId, String password, String fullName, String description, String diagnostic, String mobile, String adresse, Date date) {
        this.patientId = patientId;
        this.password = password;
        this.fullName = fullName;
        this.description = description;
        Diagnostic = diagnostic;
        this.mobile = mobile;
        this.adresse = adresse;
        this.date = date;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiagnostic() {
        return Diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        Diagnostic = diagnostic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

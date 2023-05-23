package com.example.miniprojet;

public class NurseData {

    private Integer idNurse ;
    private String fullName ;
    private  String adresse ;
    private String mobile ;
    private Double salary ;
    private Integer IdSalle ;

    public NurseData(Integer idNurse, String fullName, String adresse, String mobile, Double salary, Integer idSalle) {

        this.idNurse = idNurse;
        this.fullName = fullName;
        this.adresse = adresse;
        this.mobile = mobile;
        this.salary = salary;
        IdSalle = idSalle;
    }

    public Integer getIdNurse() {
        return idNurse;
    }

    public void setIdNurse(Integer idNurse) {
        this.idNurse = idNurse;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getIdSalle() {
        return IdSalle;
    }

    public void setIdSalle(Integer idSalle) {
        IdSalle = idSalle;
    }

}

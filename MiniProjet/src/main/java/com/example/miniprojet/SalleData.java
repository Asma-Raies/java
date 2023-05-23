package com.example.miniprojet;

public class SalleData {
    private Integer idSalle ;
    private Integer nbBed ;
    private String idService ;


    public SalleData(Integer idSalle, Integer nbBed, String idService) {
        this.idSalle = idSalle;
        this.nbBed = nbBed;
        this.idService = idService;
    }

    public Integer getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(Integer idSalle) {
        this.idSalle = idSalle;
    }

    public Integer getNbBed() {
        return nbBed;
    }

    public void setNbBed(Integer nbBed) {
        this.nbBed = nbBed;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }
}

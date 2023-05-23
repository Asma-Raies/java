package com.example.miniprojet;

public class ServiceData {
    private String  code ;
    private String name ;
    private  String bloc ;

    public ServiceData(String code,String name , String bloc ) {
        this.code = code;
        this.bloc=bloc;
        this.name=name;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloc() {
        return bloc;
    }

    public void setBloc(String bloc) {
        this.bloc = bloc;
    }

    @Override
    public String toString() {
        return name;

    }
}

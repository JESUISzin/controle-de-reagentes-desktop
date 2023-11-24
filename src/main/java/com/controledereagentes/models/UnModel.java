package com.controledereagentes.models;

public class UnModel {
    private Integer id;
    private String sigla;

    public UnModel(Integer id, String sigla) {
        this.id = id;
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return sigla;
    }

    public Integer getId() {
        return id;
    }

    public String getSigla() {
        return sigla;
    }

}

package com.controledereagentes.models;

public class FornecedorModel {
    private Integer id;
    private String razao_social;
    private String cnpj;

    public FornecedorModel(Integer id, String razao_social, String cnpj) {
        this.id = id;
        this.razao_social = razao_social;
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return id + " - " + razao_social + " - " + cnpj;
    }

    public Integer getId() {
        return id;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public String getCnpj() {
        return cnpj;
    }
}

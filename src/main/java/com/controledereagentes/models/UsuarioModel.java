package com.controledereagentes.models;

public class UsuarioModel {
    private Integer id;
    private String nome;

    public UsuarioModel(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}

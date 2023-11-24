package com.controledereagentes.models;

public class ReagenteModel {
    private int id;
    private String cod;
    private String descricao;
    private String loc_estoque;
    private double estoque_atual;
    private double vlr_estoque;
    private int entradas;
    private int saidas;
    private UnModel un;

    public ReagenteModel(int id, String cod, String descricao, String loc_estoque, double estoque_atual, double vlr_estoque, int entradas, int saidas, UnModel un) {
        this.id = id;
        this.cod = cod;
        this.descricao = descricao;
        this.loc_estoque = loc_estoque;
        this.estoque_atual = estoque_atual;
        this.vlr_estoque = vlr_estoque;
        this.entradas = entradas;
        this.saidas = saidas;
        this.un = un;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLoc_estoque() {
        return loc_estoque;
    }

    public void setLoc_estoque(String loc_estoque) {
        this.loc_estoque = loc_estoque;
    }

    public double getEstoque_atual() {
        return estoque_atual;
    }

    public void setEstoque_atual(double estoque_atual) {
        this.estoque_atual = estoque_atual;
    }

    public double getVlr_estoque() {
        return vlr_estoque;
    }

    public void setVlr_estoque(double vlr_estoque) {
        this.vlr_estoque = vlr_estoque;
    }

    public int getEntradas() {
        return entradas;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    public int getSaidas() {
        return saidas;
    }

    public void setSaidas(int saidas) {
        this.saidas = saidas;
    }

    public UnModel getUn() {
        return un;
    }

    public String getSigla() {
        return un.getSigla();
    }


}

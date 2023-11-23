package com.controledereagentes.models;

public class ReagentesModel {
    // Declaração de variáveis para cada coluna
    private int id;
    private String cod;
    private String descricao;
    private String locEstoque;
    private double estoqueAtual;
    private double vlrEstoque;
    private int entradas;
    private int saidas;
    private boolean ativo;
    private int idUnDeMedidaFk;

    public ReagentesModel(Integer id, String cod, String descricao, String locEstoque, double estoqueAtual, double vlrEstoque, int entradas, int saidas, boolean ativo, int idUnDeMedidaFk) {
    }


    // Getters e setters para cada propriedade
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

    public String getLocEstoque() {
        return locEstoque;
    }

    public void setLocEstoque(String locEstoque) {
        this.locEstoque = locEstoque;
    }

    public double getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(double estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public double getVlrEstoque() {
        return vlrEstoque;
    }

    public void setVlrEstoque(double vlrEstoque) {
        this.vlrEstoque = vlrEstoque;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getIdUnDeMedidaFk() {
        return idUnDeMedidaFk;
    }

    public void setIdUnDeMedidaFk(int idUnDeMedidaFk) {
        this.idUnDeMedidaFk = idUnDeMedidaFk;
    }

}

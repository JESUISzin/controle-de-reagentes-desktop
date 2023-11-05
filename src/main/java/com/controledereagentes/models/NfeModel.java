package com.controledereagentes.models;

import java.util.Date;

public class NfeModel {
    private Integer id;
    private Integer numero;
    private Date data_emissao;
    private FornecedorModel fornecedor;

    public NfeModel(Integer id, Integer numero, Date data_emissao, FornecedorModel fornecedor) {
        this.id = id;
        this.numero = numero;
        this.data_emissao = data_emissao;
        this.fornecedor = fornecedor;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public Date getData_emissao() {
        return data_emissao;
    }

    public String getRazao_social() {
        if (fornecedor != null) {
            return fornecedor.getRazao_social();
        }
        return null; // ou retorne o valor padrão desejado, como uma string vazia
    }

    public String getCnpj() {
        if (fornecedor != null) {
            return fornecedor.getCnpj();
        }
        return null; // ou retorne o valor padrão desejado, como uma string vazia
    }
}

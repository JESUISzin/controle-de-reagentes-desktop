package com.controledereagentes.models;


import java.sql.Date;
import java.time.LocalDate;

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

    public LocalDate getData_emissao() {
        return data_emissao.toLocalDate();
    }

    public FornecedorModel getFornecedor() {
        return fornecedor;
    }

    public String getRazao_social() {
        return fornecedor.getRazao_social();
    }

    public String getCnpj() {
        return fornecedor.getCnpj();
    }
}

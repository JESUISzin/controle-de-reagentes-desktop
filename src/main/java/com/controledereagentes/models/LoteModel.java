package com.controledereagentes.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoteModel {
    private Integer id;
    private Integer numero;
    private Integer itens_vinculados;
    private LocalDateTime createdAt;

    public LoteModel(Integer id, Integer numero, Integer itens_vinculados, Timestamp createdAt) {
        this.id = id;
        this.numero = numero;
        this.itens_vinculados = itens_vinculados;
        this.createdAt = createdAt.toLocalDateTime();
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public Integer getItens_vinculados() {
        return itens_vinculados;
    }

    public String  getCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return createdAt.format(formatter);
    }
}

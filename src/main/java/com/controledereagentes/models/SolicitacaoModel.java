package com.controledereagentes.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SolicitacaoModel {

    private int id;
    private int status;
    private String comentario;
    private LocalDateTime createdAt;
    private UsuarioModel usuario;


    public SolicitacaoModel(int id, int status, String comentario, Timestamp createdAt, UsuarioModel usuario) {
        this.id = id;
        this.status = status;
        this.comentario = comentario;
        this.createdAt = createdAt.toLocalDateTime();
        this.usuario = usuario;
    }


    public int getId() {
        return id;
    }

    public String getStatus() {
        switch (this.status) {
            case 1:
                return "Em análise";
            case 2:
                return "Incompleto";
            case 3:
                return "Concluído";
        }
        return "-";
    }

    public String getComentario() {
        return comentario;
    }

    public String getCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return createdAt.format(formatter);
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public String getNome() {
        return usuario.getNome();
    }

    public int getStatusId() {
        return status;
    }
}
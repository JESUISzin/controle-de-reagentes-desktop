package com.controledereagentes.dao;

import com.controledereagentes.ConnectionFactory;
import com.controledereagentes.models.SolicitacaoModel;
import com.controledereagentes.models.UsuarioModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class SolicitacoesDAO {
    private final Connection conDAO;

    public SolicitacoesDAO() {
        this.conDAO = ConnectionFactory.getConnection();
    }

    public ObservableList<SolicitacaoModel> list() {
        PreparedStatement ps;
        ResultSet rs;
        ObservableList<SolicitacaoModel> solicitacoes = FXCollections.observableArrayList();

        String sqlCmd = """
                SELECT s.id, s.status, s.comentario, s.createdAt, u.id AS id_usuario, u.nome AS nome
                FROM solicitacoes as s
                INNER JOIN usuarios as u ON s.id_usuario_fk = u.id
                ORDER BY s.id""";

        try {
            ps = conDAO.prepareStatement(sqlCmd);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int status = rs.getInt("status");
                String comentario = rs.getString("comentario");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                UsuarioModel usuario = new UsuarioModel(
                        rs.getInt(5),
                        rs.getString(6));

                solicitacoes.add(new SolicitacaoModel(id, status, comentario, createdAt, usuario));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return solicitacoes;
    }

    public void add(Integer status, String comentario) {
        PreparedStatement ps;

        String sqlCmd = "INSERT into solicitacoes (status, comentario, id_usuario_fk, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?)";

        try {
            conDAO.setAutoCommit(false);

            ps = conDAO.prepareStatement(sqlCmd);

            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

            ps.setInt(1, status);
            ps.setString(2, comentario);
            ps.setInt(3, 1);
            ps.setDate(4, currentDate);
            ps.setDate(5, currentDate);

            ps.execute();
            ps.close();
            conDAO.commit();
            conDAO.close();
        } catch (SQLException e) {
            try {
                conDAO.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Integer status, String comentario) {
        PreparedStatement ps;
        String sqlCmd = "UPDATE solicitacoes SET status = ?, comentario = ?, id_usuario_fk = ? WHERE id = ?";

        try {
            ps = conDAO.prepareStatement(sqlCmd);

            ps.setInt(1, status);
            ps.setString(2, comentario);
            ps.setInt(3, 1);
            ps.setInt(4, id);

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            try {
                conDAO.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String sqlCmd = "DELETE FROM solicitacoes WHERE id = ?";

        try {
            PreparedStatement ps = conDAO.prepareStatement(sqlCmd);

            ps.setInt(1, id);

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

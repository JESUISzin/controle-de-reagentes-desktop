package com.controledereagentes.dao;


import com.controledereagentes.ConnectionFactory;
import com.controledereagentes.models.LoteModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class LotesDAO {
    private final Connection conDAO;

    public LotesDAO() {
        this.conDAO = ConnectionFactory.getConnection();
    }

    public ObservableList<LoteModel> list() {
        PreparedStatement ps;
        ResultSet rs;
        ObservableList<LoteModel> lotesDeCompraList = FXCollections.observableArrayList();

        String sqlCmd = "SELECT * FROM lotesdecompra ORDER BY id";

        try {
            ps = conDAO.prepareStatement(sqlCmd);
            rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer numero = rs.getInt("numero");
                Integer itens_vinculados = rs.getInt("itens_vinculados");
                Timestamp createdAt = rs.getTimestamp("createdAt");

                lotesDeCompraList.add(new LoteModel(id, numero, itens_vinculados, createdAt));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lotesDeCompraList;
    }

    public void add(Integer numero) {
        PreparedStatement ps;

        String sqlCmd = "INSERT INTO lotesdecompra (numero, itens_vinculados, createdAt, updatedAt) VALUES (?, ?, ?, ?)";

        try {
            conDAO.setAutoCommit(false);

            ps = conDAO.prepareStatement(sqlCmd);

            java.sql.Timestamp currentDate = new java.sql.Timestamp(System.currentTimeMillis());

            ps.setInt(1, numero);
            ps.setInt(2, 0);
            ps.setTimestamp(3, currentDate);
            ps.setTimestamp(4, currentDate);

            ps.execute();
            ps.close();

            conDAO.commit();
        } catch (SQLException e) {
            try {
                conDAO.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void update(Integer id, Integer numero, Integer itens_vinculados) {
        PreparedStatement ps;

        String sqlCmd = "UPDATE lotesdecompra SET numero=?, itens_vinculados=?, updatedAt=? WHERE id=?";

        try {
            conDAO.setAutoCommit(false);

            ps = conDAO.prepareStatement(sqlCmd);

            java.sql.Timestamp currentDate = new java.sql.Timestamp(System.currentTimeMillis());

            ps.setInt(1, numero);
            ps.setInt(2, itens_vinculados);
            ps.setTimestamp(3, currentDate);
            ps.setInt(4, id);

            ps.execute();
            ps.close();

            conDAO.commit();
        } catch (SQLException e) {
            try {
                conDAO.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer id) {
        PreparedStatement ps;

        String sqlCmd = "DELETE FROM lotesdecompra WHERE id=?";

        try {
            conDAO.setAutoCommit(false);

            ps = conDAO.prepareStatement(sqlCmd);

            ps.setInt(1, id);

            ps.execute();
            ps.close();

            conDAO.commit();
        } catch (SQLException e) {
            try {
                conDAO.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}

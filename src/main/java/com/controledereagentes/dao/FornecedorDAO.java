package com.controledereagentes.dao;


import com.controledereagentes.ConnectionFactory;
import com.controledereagentes.models.FornecedorModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FornecedorDAO {
    private final Connection conDAO;

    public FornecedorDAO() {
        this.conDAO = ConnectionFactory.getConnection();
    }

    public ObservableList<FornecedorModel> list() {
        PreparedStatement ps;
        ResultSet rs;
        ObservableList<FornecedorModel> fornecedores = FXCollections.observableArrayList();

        String sqlCmd = "SELECT * FROM fornecedores ORDER BY id";

        try {
            ps = conDAO.prepareStatement(sqlCmd);
            rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt(1);
                String razao_social = rs.getString(2);
                String cnpj = rs.getString(3);

                fornecedores.add(new FornecedorModel(id, razao_social, cnpj));
            }
            rs.close();
            ps.close();
            conDAO.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fornecedores;
    }

    public void add(BigInteger cnpj, String razaoSocial) {
        PreparedStatement ps;

        String sqlCmd = "INSERT into fornecedores (cnpj, razao_social, createdAt, updatedAt) VALUES (?, ?, ?, ?)";

        try {
            conDAO.setAutoCommit(false);

            ps = conDAO.prepareStatement(sqlCmd);

            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

            ps.setString(1, cnpj.toString());
            ps.setString(2, razaoSocial);
            ps.setDate(3, currentDate);
            ps.setDate(4, currentDate);

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


    public void update(Integer id, BigInteger cnpj, String razao_social) {
        PreparedStatement ps;
        String sqlCmd = "UPDATE fornecedores SET cnpj = ?, razao_social = ? WHERE id = ?";

        try {
            conDAO.setAutoCommit(false);

            ps = conDAO.prepareStatement(sqlCmd);

            ps.setString(1, cnpj.toString());
            ps.setString(2, razao_social);
            ps.setInt(3, id);

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

    public void delete(Integer id) {
        System.out.println(id);
        String sqlCmd = "DELETE FROM fornecedores WHERE id = ?";

        try {
            PreparedStatement ps = conDAO.prepareStatement(sqlCmd);

            ps.setInt(1, id);

            ps.execute();
            ps.close();
            conDAO.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

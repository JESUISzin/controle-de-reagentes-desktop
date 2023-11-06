package com.controledereagentes.dao;


import com.controledereagentes.ConnectionFactory;
import com.controledereagentes.models.FornecedorModel;
import com.controledereagentes.models.NfeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class NfeDAO {
    private final Connection conDAO;

    public NfeDAO() {
        this.conDAO = ConnectionFactory.getConnection();
    }

    public ObservableList<NfeModel> list() {
        PreparedStatement ps;
        ResultSet rs;
        ObservableList<NfeModel> nfes = FXCollections.observableArrayList();

        String sqlCmd = "SELECT nfes.id, nfes.numero, nfes.data_emissao, fornecedores.id AS id_fornecedor," +
                "fornecedores.razao_social AS razao_social, fornecedores.cnpj AS cnpj\n" +
                "FROM nfes\n" +
                "INNER JOIN fornecedores ON nfes.id_fornecedor_fk = fornecedores.id\n" +
                "ORDER BY nfes.id";

        try {
            ps = conDAO.prepareStatement(sqlCmd);
            rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt(1);
                Integer numero = rs.getInt(2);
                Date data_emissao = rs.getDate(3);
                FornecedorModel fornecedor = new FornecedorModel(
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6));

                nfes.add(new NfeModel(id, numero, data_emissao, fornecedor));
            }
            rs.close();
            ps.close();
            conDAO.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nfes;
    }

    public void add(Integer numero, Date data_emissao, Integer fornecedor_id) {
        PreparedStatement ps;

        String sqlCmd = "INSERT into nfes (numero, data_emissao, id_fornecedor_fk, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?)";

        try {
            conDAO.setAutoCommit(false);

            ps = conDAO.prepareStatement(sqlCmd);

            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

            ps.setInt(1, numero);
            ps.setDate(2, data_emissao);
            ps.setInt(3, fornecedor_id);
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


    public void update(Integer id, Integer numero, Date data_emissao, Integer fornecedor_id) {
        PreparedStatement ps;
        String sqlCmd = "UPDATE nfes SET numero = ?, data_emissao = ?, id_fornecedor_fk = ? WHERE id = ?";

        try {
            conDAO.setAutoCommit(false);

            ps = conDAO.prepareStatement(sqlCmd);

            ps.setInt(1, numero);
            ps.setDate(2, data_emissao);
            ps.setInt(3, fornecedor_id);
            ps.setInt(4, id);

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
        String sqlCmd = "DELETE FROM nfes WHERE id = ?";

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

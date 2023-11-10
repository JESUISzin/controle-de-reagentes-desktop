package com.controledereagentes.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.controledereagentes.ConnectionFactory;
import com.controledereagentes.models.FornecedorModel;
import com.controledereagentes.models.ReagentesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ReagentesDAO {

    private final Connection conDAO;

    public ReagentesDAO() {
        this.conDAO = ConnectionFactory.getConnection();
    }

    public ObservableList<ReagentesModel> list() {
        PreparedStatement ps;
        ResultSet rs;
        ObservableList<ReagentesModel> Reagentes = FXCollections.observableArrayList();

        String sqlCmd = "SELECT * FROM tiposdereagente ORDER BY id";

        try {
            ps = conDAO.prepareStatement(sqlCmd);
            rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt(1);
                String cod = rs.getString(2);
                String descricao = rs.getString(3);
                String locEstoque = rs.getString(4);
                double estoqueAtual = rs.getDouble(5);
                double vlrEstoque = rs.getDouble(6);
                int entradas = rs.getInt(7);
                int saidas = rs.getInt(8);
                boolean ativo = rs.getBoolean(9);
                int idUnDeMedidaFk = rs.getInt(10);


                Reagentes.add(new ReagentesModel(
                        id,
                        cod,
                        descricao,
                        locEstoque,
                        estoqueAtual,
                        vlrEstoque,
                        entradas,
                        saidas,
                        ativo,
                        idUnDeMedidaFk
                ));
            }
            rs.close();
            ps.close();
            conDAO.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Reagentes;
    }

    public void add(String cod, String descricao, String locEstoque, double estoqueAtual, double vlrEstoque, int entradas, int saidas, boolean ativo, int idUnDeMedidaFk) {
        PreparedStatement ps;

        String sqlCmd = "INSERT into tiposdereagente (cod, descricao, locEstoque, estoqueAtual, vlrEstoque, entradas, saidas, ativo, idUnDeMedidaFk, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conDAO.setAutoCommit(false);

            ps = conDAO.prepareStatement(sqlCmd);

            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

            ps.setString(1, cod);
            ps.setString(2, descricao);
            ps.setString(3, locEstoque);
            ps.setDouble(4, estoqueAtual);
            ps.setDouble(5, vlrEstoque);
            ps.setInt(6, entradas);
            ps.setInt(7, saidas);
            ps.setBoolean(8, ativo);
            ps.setInt(9, idUnDeMedidaFk);
            ps.setDate(10, currentDate);
            ps.setDate(11, currentDate);

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


    public void update(Integer id, String cod, String descricao, String locEstoque, double estoqueAtual, double vlrEstoque, int entradas, int saidas, boolean ativo, int idUnDeMedidaFk) {
        PreparedStatement ps;
        String sqlCmd = "UPDATE tiposdereagente SET cod = ?, descricao = ?, locEstoque = ?, estoqueAtual = ?, vlrEstoque = ?, entradas = ?, saidas = ?, ativo = ?, idUnDeMedidaFk = ? WHERE id = ?";

        try {
            conDAO.setAutoCommit(false);

            ps = conDAO.prepareStatement(sqlCmd);

            ps.setString(1, cod);
            ps.setString(2, descricao);
            ps.setString(3, locEstoque);
            ps.setDouble(4, estoqueAtual);
            ps.setDouble(5, vlrEstoque);
            ps.setInt(6, entradas);
            ps.setInt(7, saidas);
            ps.setBoolean(8, ativo);
            ps.setInt(9, idUnDeMedidaFk);
            ps.setInt(10, id);


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
        String sqlCmd = "DELETE FROM tiposdereagente WHERE id = ?";

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

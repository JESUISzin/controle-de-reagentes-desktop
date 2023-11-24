package com.controledereagentes.dao;

import com.controledereagentes.ConnectionFactory;
import com.controledereagentes.models.ReagenteModel;
import com.controledereagentes.models.UnModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReagentesDAO {

    private final Connection conDAO;

    public ReagentesDAO() {
        this.conDAO = ConnectionFactory.getConnection();
    }

    public ObservableList<ReagenteModel> list() {
        PreparedStatement ps;
        ResultSet rs;
        ObservableList<ReagenteModel> Reagentes = FXCollections.observableArrayList();

        String sqlCmd = """
                SELECT tipo.id, tipo.cod, tipo.descricao, tipo.loc_estoque, tipo.estoque_atual,
                tipo.vlr_estoque, tipo.entradas, tipo.saidas, unsdemedida.id AS id_un, unsdemedida.sigla AS sigla
                FROM tiposdereagente as tipo
                INNER JOIN unsdemedida ON tipo.id_un_de_medida_fk = unsdemedida.id
                ORDER BY unsdemedida.id""";

        try {
            ps = conDAO.prepareStatement(sqlCmd);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String cod = rs.getString(2);
                String descricao = rs.getString(3);
                String loc_estoque = rs.getString(4);
                double estoque_atual = rs.getDouble(5);
                double vlr_estoque = rs.getDouble(6);
                int entradas = rs.getInt(7);
                int saidas = rs.getInt(8);
                UnModel un = new UnModel(
                        rs.getInt(9),
                        rs.getString(10));


                Reagentes.add(new ReagenteModel(
                        id,
                        cod,
                        descricao,
                        loc_estoque,
                        estoque_atual,
                        vlr_estoque,
                        entradas,
                        saidas,
                        un
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

    public void add(Integer cod, String descricao, String loc_estoque, int id_un_de_medida_fk) {
        PreparedStatement ps;

        String sqlCmd = "INSERT into tiposdereagente (cod, descricao, loc_estoque, estoque_atual, vlr_estoque, entradas, saidas, ativo, id_un_de_medida_fk, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conDAO.setAutoCommit(false);

            ps = conDAO.prepareStatement(sqlCmd);

            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

            ps.setInt(1, cod);
            ps.setString(2, descricao);
            ps.setString(3, loc_estoque);
            ps.setDouble(4, 0.00);
            ps.setDouble(5, 0.00);
            ps.setInt(6, 0);
            ps.setInt(7, 0);
            ps.setBoolean(8, true);
            ps.setInt(9, id_un_de_medida_fk);
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


    public void update(Integer id, Integer cod, String descricao, String loc_estoque, int id_un_de_medida_fk) {
        PreparedStatement ps;
        String sqlCmd = "UPDATE tiposdereagente SET cod = ?, descricao = ?, loc_estoque = ?, id_un_de_medida_fk = ? WHERE id = ?";

        try {
            conDAO.setAutoCommit(false);

            ps = conDAO.prepareStatement(sqlCmd);

            ps.setInt(1, cod);
            ps.setString(2, descricao);
            ps.setString(3, loc_estoque);
            ps.setInt(4, id_un_de_medida_fk);
            ps.setInt(5, id);


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

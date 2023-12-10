/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Requisito;
import model.Tipo_Tramite;
import util.DataSource;

/**
 *
 * @author DAVID
 */
public class RequisitoDAOImpl implements IDAO<Requisito> {

    Connection conn = DataSource.obtenerConexion();

    @Override
    public int add(Requisito objeto) {
        String query = "INSERT INTO requisito (Nom_TT, Nom_Req, Descripcion) VALUES (?, ?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            conn.setAutoCommit(false);
            ps.setString(1, objeto.getTipo_tramite().getNom_TT());
            ps.setString(2, objeto.getNom_Req());
            ps.setString(3, objeto.getDescripcion());

            int exito = ps.executeUpdate();

            if (exito > 0) {
                conn.commit();
                return 1;
            } else {
                conn.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }

    @Override
    public int update(Requisito objeto) {

        String query = "UPDATE requisito set Descripcion = ? WHERE Nom_Req = ?";
        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            conn.setAutoCommit(false);
            ps.setString(1, objeto.getDescripcion());
            ps.setString(2, objeto.getNom_Req());

            int exito = ps.executeUpdate();

            if (exito > 0) {
                conn.commit();
                return 1;
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 2;

    }

    @Override
    public int delete(Requisito objeto) {

        String query = "Update requisito set activo = 2 WHERE Nom_Req = ?";
        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            conn.setAutoCommit(false);
            ps.setString(1, objeto.getNom_Req());

            int exito = ps.executeUpdate();

            if (exito > 0) {
                conn.commit();
                return 1;
            } else {
                conn.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }

    @Override
    public List<Requisito> getListado() {

        List<Requisito> requisitos = new ArrayList<>();
        String query = "SELECT * FROM requisito";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Requisito requisito = new Requisito();
                requisito.setNom_Req(rs.getString("Nom_Req"));

                Tipo_Tramite tipo_tramite = new Tipo_Tramite();
                tipo_tramite.setNom_TT(rs.getString("Nom_TT"));
                requisito.setTipo_tramite(tipo_tramite);
                requisito.getTipo_tramite().getNom_TT();
                requisito.setActivo(rs.getInt("activo"));
                requisito.setDescripcion(rs.getString("Descripcion"));
                requisitos.add(requisito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requisitos;

    }

    // Método para obtener todos los requisitos de un tipo de trámite específico
    public List<Requisito> getRequisitosByTiTramite(Tipo_Tramite tipoTramite) {
        
        List<Requisito> requisitos = new ArrayList<>();

        String query = "SELECT * FROM requisito WHERE Nom_TT = ?";
        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, tipoTramite.getNom_TT());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Requisito requisito = new Requisito();
                requisito.setNom_Req(rs.getString("Nom_Req"));
                requisito.setDescripcion(rs.getString("Descripcion"));

                requisitos.add(requisito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción apropiadamente
        }

        return requisitos;
    }

    public int Reactivar(Requisito objeto) {

        String query = "Update requisito set activo = 1 WHERE Nom_Req = ?";

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            conn.setAutoCommit(false);
            ps.setString(1, objeto.getNom_Req());

            int exito = ps.executeUpdate();

            if (exito > 0) {
                conn.commit();
                return 1;
            } else {
                conn.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;

    }

}

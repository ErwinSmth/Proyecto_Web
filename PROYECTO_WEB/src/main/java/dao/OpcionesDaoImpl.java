/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import model.Opciones;
import util.DataSource;
import static util.DataSource.obtenerConexion;

public class OpcionesDaoImpl implements IOpcionesDAO {

    Connection conn = DataSource.obtenerConexion();

    @Override
    public String agregarOpciones(Opciones op) {

        PreparedStatement pst = null;
        String mensaje = "";
        String sql = "INSERT INTO opciones (documento, estado, descripcion, grupo) VALUES(?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
          
            pst.setString(1, op.getDocumento());
            pst.setString(2, op.getEstado());
            pst.setString(3, op.getDescripcion());
            pst.setString(4, op.getGrupo());

            mensaje = "GUARDADO EXITOSAMENTE";
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            mensaje = "NO SE HA GUARDADO CORRECTAMENTE " + e.getMessage();
        }
        return mensaje;

    }

    @Override
    public String actualizarOpciones(Opciones op) {

        PreparedStatement pst = null;
        String mensaje = "";
        String sql = "UPDATE opciones documento=?, estado =?, descripcion=?,grupo=?"
                + " WHERE id=?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, op.getDocumento());
            pst.setString(2, op.getEstado());
            pst.setString(3, op.getDescripcion());
            pst.setString(4, op.getGrupo());

            mensaje = "EDITADO EXITOSAMENTE";
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            mensaje = "NO SE HA EDITADO CORRECTAMENTE " + e.getMessage();
        }
        return mensaje;

    }

    @Override
    public String eliminarOpciones(Opciones op) {
        PreparedStatement pst = null;
        String mensaje = "";
        String sql = "DELETE FROM opciones WHERE id =?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, op.getId());
            mensaje = "OPCION ELIMINADA";
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            mensaje = "NO SE HA ELIMINADO CORRECTAMENTE " + e.getMessage();
        }

        return mensaje;

    }

    @Override
    public List<Opciones> getOpciones() {
        PreparedStatement pst = null;
        String mensaje = "";
        String query = "SELECT*FROM opciones";
        List<Opciones> lista = new ArrayList<>();
        try {
            PreparedStatement stm = obtenerConexion().prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                lista.add(new Opciones(
                        rs.getInt("id"),
                        rs.getString("documento"),
                        rs.getString("estado"),
                        rs.getString("descripcion"),
                        rs.getString("grupo")));
            }
            mensaje = "OPCION SELECCIONADA";
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "NO SE PUDO SELECCIONAR" + e.getMessage();
        }
        return lista;

    }

    @Override
    public Opciones getOpcionById(int id) {

        PreparedStatement pst = null;
        String mensaje = "";
        String query = "SELECT*FROM opciones WHERE id=?";
        Opciones op = new Opciones();
        try {
            PreparedStatement stm = obtenerConexion().prepareStatement(query);
            stm.setInt(0, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                op.setId(rs.getInt("id"));
                op.setDocumento(rs.getString("documento"));
                op.setEstado(rs.getString("estado"));
                op.setDescripcion(rs.getString("descripcion"));
                op.setGrupo(rs.getString("grupo"));
            }
            mensaje = "OPCION SELECCIONADA POR ID EXITOSAMENTE";
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "NO SE PUDO SELECCIONAR POR ID" + e.getMessage();
        }
        return op;

    }

}

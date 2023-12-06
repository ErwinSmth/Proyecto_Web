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

public class OpcionesDaoImpl implements IDAO<Opciones> {

    Connection conn = DataSource.obtenerConexion();

    @Override
    public int add(Opciones op) {
        PreparedStatement pst = null;
        String sql = "INSERT INTO opcion (pagina, estado, descripcion, grupo) VALUES (?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql);

            pst.setString(1, op.getDocumento());
            pst.setString(2, op.getEstado());
            pst.setString(3, op.getDescripcion());
            pst.setString(4, op.getGrupo());

            int exito = pst.executeUpdate();
            pst.close();

            if (exito > 0) {
                return 1; // Éxito al insertar
            } else {
                return 2; // No se pudo insertar
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 2; // Error al intentar insertar
        }
    }

    @Override
    public int update(Opciones op) {
        PreparedStatement pst = null;
        String sql = "UPDATE opcion SET pagina = ?, estado = ?, descripcion = ?, grupo = ? WHERE id_opcion = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, op.getDocumento());
            pst.setString(2, op.getEstado());
            pst.setString(3, op.getDescripcion());
            pst.setString(4, op.getGrupo());
            pst.setInt(5, op.getId());

            int exito = pst.executeUpdate();
            pst.close();

            if (exito > 0) {
                return 1; // Éxito al actualizar
            } else {
                return 2; // No se pudo actualizar
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 2; // Error al intentar actualizar
        }
    }

    @Override
    public int delete(Opciones op) {
        PreparedStatement pst = null;
        String sql = "DELETE FROM opcion WHERE id_opcion = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, op.getId());
            int exito = pst.executeUpdate();
            pst.close();

            if (exito > 0) {
                return 1; // Éxito al eliminar
            } else {
                return 2; // No se pudo eliminar
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 2; // Error al intentar eliminar
        }
    }

    @Override
    public List<Opciones> getListado() {
        String query = "SELECT * FROM opcion";
        List<Opciones> lista = new ArrayList<>();
        try {
            PreparedStatement stm = obtenerConexion().prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                lista.add(new Opciones(
                        rs.getInt("id_opcion"),
                        rs.getString("pagina"),
                        rs.getString("estado"),
                        rs.getString("descripcion"),
                        rs.getString("grupo")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;

    }

    public Opciones getByID(int id) {

        String query = "SELECT*FROM opcion WHERE id_opcion=?";
        Opciones op = new Opciones();
        try {
            PreparedStatement stm = obtenerConexion().prepareStatement(query);
            stm.setInt(0, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                op.setId(rs.getInt("id_opcion"));
                op.setDocumento(rs.getString("pagina"));
                op.setEstado(rs.getString("estado"));
                op.setDescripcion(rs.getString("descripcion"));
                op.setGrupo(rs.getString("grupo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return op;

    }

}

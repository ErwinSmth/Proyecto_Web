/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Anexo;
import model.Tramite;
import util.DataSource;

/**
 *
 * @author DAVID
 */
public class AnexoDAOImpl implements IDAO<Anexo> {

    Connection conn = DataSource.obtenerConexion();

    @Override
    public int add(Anexo objeto) {

        String query = "Insert into Anexo (id_tramite, Descripcion, fecha_registro, ubicacion_archivo) values (?, ?, ?, ?)";

        try {

            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, objeto.getTramite().getId_tramite());
            ps.setString(2, objeto.getDescripcion());

            java.sql.Date fechaRegistro = java.sql.Date.valueOf(objeto.getFecha_registro());
            ps.setDate(3, fechaRegistro);

            ps.setString(4, objeto.getUbicacion_archivo());
            
            int exito = ps.executeUpdate();

            if (exito > 0) {

                conn.commit();
                int idTramite = objeto.getTramite().getId_tramite();
                TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
                tramiteDao.updateCantDoc(idTramite);
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
    public int update(Anexo objeto) {

        String query = "update anexo set Descripcion = ? where id_documento = ?";

        try {

            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, objeto.getDescripcion());
            ps.setInt(2, objeto.getId_documento());

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
    public int delete(Anexo objeto) {

        String query = "delete from anexo where id_documento = ?";

        try {

            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, objeto.getId_documento());

            int exito = ps.executeUpdate();

            if (exito > 0) {
                conn.commit();
                int idTramite = objeto.getTramite().getId_tramite();
                TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
                tramiteDao.updateCantDoc(idTramite);
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
    public List<Anexo> getListado() {
        String query = "SELECT * FROM anexo";
        List<Anexo> listado = new ArrayList<>();

        try ( PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Anexo anexo = new Anexo();
                anexo.setId_documento(rs.getInt("id_documento"));

                Tramite tramite = new Tramite();
                tramite.setId_tramite(rs.getInt("id_tramite"));
                anexo.setTramite(tramite);
                anexo.setDescripcion(rs.getString("Descripcion"));

                java.sql.Date fechaSQL = rs.getDate("fecha_registro");
                if (fechaSQL != null) {
                    LocalDate fechaLocalDate = fechaSQL.toLocalDate();
                    anexo.setFecha_registro(fechaLocalDate);
                }

                listado.add(anexo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listado;
    }

    //Metodo a usarse en los endPoints
    public List<Anexo> getListadoByID(int id) {

        List<Anexo> anexos = new ArrayList<>();
        String query = "SELECT * FROM anexo INNER JOIN tramite ON anexo.id_tramite = tramite.id_tramite "
                + "INNER JOIN persona ON tramite.id_persona = persona.id_persona "
                + "WHERE persona.id_persona = ?";

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Anexo anexo = new Anexo();
                anexo.setId_documento(rs.getInt("id_documento"));

                Tramite tramite = new Tramite();
                tramite.setId_tramite(rs.getInt("id_tramite"));

                anexo.setTramite(tramite);
                
                anexo.setDescripcion(rs.getString("Descripcion"));

                java.sql.Date fechaSQL = rs.getDate("fecha_registro");
                if (fechaSQL != null) {
                    LocalDate fechaLocalDate = fechaSQL.toLocalDate();
                    anexo.setFecha_registro(fechaLocalDate);
                }

                anexos.add(anexo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return anexos;

    }

}

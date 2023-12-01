/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Persona;
import model.Tipo_Tramite;
import model.Tramite;
import util.DataSource;

/**
 *
 * @author DAVID
 */
public class TramiteDaoImpl implements IDAO<Tramite> {

    Connection conn = DataSource.obtenerConexion();

    //1 para exito
    //2 para error
    @Override
    public int add(Tramite objeto) {

        String query = "Insert into tramite (id_persona, Nom_TT, fecha_inicio) values (?, ?, ?)";
        try {

            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, objeto.getPersona().getIdpersona());
            ps.setString(2, objeto.getTipoTramite().getNom_TT());

            java.sql.Date fechainicio = java.sql.Date.valueOf(objeto.getFecha_inicio());
            ps.setDate(3, fechainicio); //aqui se establecera la fecha automaticamente en la que se registro dicho tramite

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
    public int update(Tramite objeto) {

        String query = "Update tramite set Nom_TT = ? where id_tramite = ?";

        try {

            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, objeto.getTipoTramite().getNom_TT());
            ps.setInt(2, objeto.getId_tramite());

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
    public int delete(Tramite objeto) {

        String query = "Delete from tramite where id_tramite = ?";

        try ( PreparedStatement ps = conn.prepareCall(query)) {

            ps.setInt(1, objeto.getId_tramite());

            int exito = ps.executeUpdate();

            if (exito > 0) {
                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }

    @Override
    public List<Tramite> getListado() {

        String query = "SELECT t.*, p.prim_nomb, p.ape_pater\n"
                + "FROM tramite t\n"
                + "INNER JOIN persona p ON t.id_persona = p.id_persona;";

        List<Tramite> lista = new ArrayList<>();
        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Tramite tramite = new Tramite();
                tramite.setId_tramite(rs.getInt("id_tramite"));

                //Datos de la Persona
                Persona persona = new Persona();
                persona.setIdpersona(rs.getInt("id_persona"));
                persona.setPri_nombre(rs.getString("pri_nomb"));
                persona.setApe_paterno(rs.getString("ape_pater"));

                tramite.setPersona(persona);
                tramite.setTipoTramite(new Tipo_Tramite(rs.getString("Nom_TT")));

                //Fecha de inicio
                java.sql.Date fechainicioSQL = rs.getDate("fecha_inicio");
                LocalDate fechainicio = fechainicioSQL.toLocalDate();
                tramite.setFecha_inicio(fechainicio);

                //Fecha de fin
                java.sql.Date fechafinSQL = rs.getDate("fecha_finalizo");
                LocalDate fechafin = fechafinSQL.toLocalDate();
                tramite.setFecha_finalizo(fechafin);

                //Estado
                tramite.setEstado(rs.getInt("estado_final"));

                //cantidad de documentos
                tramite.setCant_Documentos(rs.getInt("cant_documentos"));

                lista.add(tramite);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    //Metodo a usarse como endpoint en la API
    public List<Tramite> getListadoByID(int id) {

        String query = "Select * from tramite where id_persona = ?";
        List<Tramite> lista = new ArrayList<>();

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Tramite tramite = new Tramite();
                tramite.setTipoTramite(new Tipo_Tramite(rs.getString("Nom_TT")));

                //Fecha de inicio
                java.sql.Date fechainicioSQL = rs.getDate("fecha_inicio");
                LocalDate fechainicio = fechainicioSQL.toLocalDate();
                tramite.setFecha_inicio(fechainicio);

                //Fecha de fin
                java.sql.Date fechafinSQL = rs.getDate("fecha_finalizo");
                LocalDate fechafin = fechafinSQL.toLocalDate();
                tramite.setFecha_finalizo(fechafin);

                //Estado
                tramite.setEstado(rs.getInt("estado_final"));

                //cantidad de documentos
                tramite.setCant_Documentos(rs.getInt("cant_documentos"));
                
                lista.add(tramite);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}

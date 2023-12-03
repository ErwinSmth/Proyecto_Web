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
import model.Requisito;
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

    public void updateCantDoc(int idtramite) {

        //Obtener la cantidad actual de documentos para el tramite
        String query = "SELECT COUNT(*) AS cantidad FROM anexo WHERE id_tramite = ?";

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idtramite);
            ResultSet rs = ps.executeQuery();

            int cantidadDoc = 0;
            if (rs.next()) {
                cantidadDoc = rs.getInt("cantidad");
            }

            //Actualizamos la cantidad de documentos para el tramite
            String updateQuery = "Update tramite set cant_documentos = ? WHERE id_tramite = ?";
            PreparedStatement ps2 = conn.prepareStatement(updateQuery);
            ps2.setInt(1, cantidadDoc);
            ps2.setInt(2, idtramite);
            ps2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Metodo que podria implementarse en los endpoints
    public Persona getPersonaByUS(String login) {

        Persona persona = null;
        String query = "SELECT * FROM persona where login = ?";

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                persona = new Persona();
                persona.setIdpersona(rs.getInt("id_persona"));
                persona.setPri_nombre(rs.getString("prim_nomb"));
                persona.setSeg_nombre(rs.getString("seg_nomb"));
                persona.setApe_paterno(rs.getString("ape_pater"));
                persona.setApe_materno(rs.getString("ape_mater"));
                persona.setNum_Doc(rs.getString("num_doc"));
                persona.setCorreo(rs.getString("correo"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return persona;
    }

    public List<Tipo_Tramite> getlistaTiTra() {

        List<Tipo_Tramite> listado = new ArrayList<>();
        String query = "Select * from tipo_tramite";
        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Tipo_Tramite tipoTra = new Tipo_Tramite();
                tipoTra.setNom_TT(rs.getString("Nom_TT"));
                tipoTra.setDescripcion(rs.getString("Descripcion"));
                listado.add(tipoTra);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listado;

    }

    //Metodo para insertar en la tabla requisito_tramite_estado
    //posible metodo a usarse en api
    public int addRequisito_Tramite(int id_tramite, String nombre_TT) {

        String query = "INSERT INTO requisito_tramite_estado (id_tramite, Nom_TT, Nom_Req, estado_requisito)\n"
                + "SELECT\n"
                + "    ? AS id_tramite,\n"
                + "    rt.Nom_TT,\n"
                + "    rt.Nom_Req,\n"
                + "    1 AS estado_requisito\n"
                + "FROM\n"
                + "    requisito rt\n"
                + "WHERE\n"
                + "    rt.Nom_TT = ?;";
        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            conn.setAutoCommit(false);
            ps.setInt(1, id_tramite);
            ps.setString(2, nombre_TT);

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

    //Posible metodo a usarse en la api
    //Metodo para mostrar todos los requisitos asociados a un tipo de tramite usando la tabla
    //requisito_tramite_estado
    public List<Requisito> getRequisitosByTipoTramite(int idTramite, String nombreTT) {

        String query = "SELECT r.Nom_Req, r.Nom_TT, r.Descripcion, rte.estado_requisito "
                + "FROM requisito_tramite_estado rte "
                + "JOIN requisito r ON rte.Nom_Req = r.Nom_Req "
                + "WHERE rte.id_tramite = ? AND rte.Nom_TT = ?";
        List<Requisito> listado = new ArrayList<>();

        try ( PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idTramite);
            ps.setString(2, nombreTT);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Requisito requisito = new Requisito();
                requisito.setNom_Req(rs.getString("Nom_Req"));

                Tipo_Tramite tipoTramite = new Tipo_Tramite();
                tipoTramite.setNom_TT(rs.getString("Nom_TT"));

                requisito.setTipo_tramite(tipoTramite);
                requisito.setDescripcion(rs.getString("Descripcion"));
                requisito.setEstado(rs.getInt("estado_requisito"));
                listado.add(requisito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listado;

    }

    public int getLastID() {

        int id = 0;

        String query = "Select MAX(id_tramite) AS ultimoID FROM tramite";

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("ultimoID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

}

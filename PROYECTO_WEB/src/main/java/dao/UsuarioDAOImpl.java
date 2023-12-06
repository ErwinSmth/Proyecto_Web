package dao;

import model.Pagina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Persona;
import model.Rol;
import model.Tipo_Documento;
import model.Usuario;
import util.DataSource;

/**
 *
 * @author DAVID
 */
public class UsuarioDAOImpl implements IDAO<Usuario> {

    Connection conn = DataSource.obtenerConexion();

    // Retorna 1 si funciono
    // Retorna 2 si no funciono
    public Usuario logeo(String login, String clave) {

        Usuario us = new Usuario();
        Rol rol = new Rol();
        String query = "SELECT u.login, u.clave, u.estado, u.idrol, r.nombre FROM usuario u "
                + "INNER JOIN rol r ON u.idrol = r.idrol "
                + "WHERE u.estado = 'Activo' AND u.login = ? AND u.clave = ?";

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, login);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                us.setLogin(rs.getString("login"));
                us.setClave(rs.getString("clave"));
                rol.setIdrol(rs.getInt("idrol"));
                rol.setNombre(rs.getString("nombre"));
                us.setRol(rol);
            } else {
                us.setLogin("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return us;
    }

    public int registro(Persona persona) {

        PreparedStatement psUs = null;
        PreparedStatement ps = null;

        String queryUsuario = "Insert into Usuario(login, clave, estado, idrol) values (?, ?, 'Activo', 1)";
        String queryPersona = "Insert into Persona(prim_nomb, seg_nomb, ape_pater, ape_mater, nom_TD, num_doc, correo, login)"
                + "values(?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            conn.setAutoCommit(false);

            psUs = conn.prepareStatement(queryUsuario);
            //Datos del Usuario
            Usuario usuario = persona.getUs();
            psUs.setString(1, usuario.getLogin());
            psUs.setString(2, usuario.getClave());

            int exitoUS = psUs.executeUpdate();

            ps = conn.prepareStatement(queryPersona);
            //Datos de la Persona
            ps.setString(1, persona.getPri_nombre());
            ps.setString(2, persona.getSeg_nombre());
            ps.setString(3, persona.getApe_paterno());
            ps.setString(4, persona.getApe_materno());
            ps.setString(5, persona.getTipoDoc().getNombre_TD());
            ps.setString(6, persona.getNum_Doc());
            ps.setString(7, persona.getCorreo());
            ps.setString(8, persona.getUs().getLogin());

            int exitoPer = ps.executeUpdate();

            //Si ambas inserciones se hacen correctamente
            if (exitoPer > 0 && exitoUS > 0) {
                conn.commit();
                return 1; //Insertado Correctamente
            } else {
                conn.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 2;
    }

    public List<Tipo_Documento> getTipoDocu() {

        String query = "Select * from tipo_documento";
        List<Tipo_Documento> tiposDoc = new ArrayList<>();

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Tipo_Documento tipoDoc = new Tipo_Documento();
                tipoDoc.setNombre_TD(rs.getString("nom_TD"));

                tiposDoc.add(tipoDoc);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tiposDoc;
    }

    @Override
    public int add(Usuario objeto) {

        String query = "Insert Into usuario (login, clave, estado, idrol) values (?, ?, ?, ?)";

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, objeto.getLogin());
            ps.setString(2, objeto.getClave());

            String estado = objeto.getEstado();

            if (estado.equals("Activo") || estado.equals("Inactivo")) {
                ps.setString(3, estado);
            }

            int idrol = objeto.getRol().getIdrol();

            if (idrol == 1 || idrol == 2 || idrol == 3) {
                ps.setInt(4, idrol);
            }

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
    public int update(Usuario objeto) {

        String query = "Update usuario Set clave = ?, estado = ?, idrol = ? Where login = ? ";

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, objeto.getClave());

            String estado = objeto.getEstado();

            if (estado.equals("Activo") || estado.equals("Inactivo")) {
                ps.setString(2, estado);
            }

            int idrol = objeto.getRol().getIdrol();

            if (idrol == 1 || idrol == 2 || idrol == 3) {
                ps.setInt(3, idrol);
            }

            ps.setString(4, objeto.getLogin());

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
    public int delete(Usuario objeto) {

        String query = "Update usuario set estado = 'Inactivo'  Where login = ?";

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, objeto.getLogin());

            int exito = ps.executeUpdate();

            if (exito > 0) {
                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }

    public int reactivar(Usuario objeto) {

        String query = "Update usuario set estado = 'Activo' where login = ?";

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, objeto.getLogin());

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
    public List<Usuario> getListado() {

        String query = "Select * from usuario";
        List<Usuario> listado = new ArrayList<>();

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Usuario us = new Usuario();
                us.setLogin(rs.getString("login"));
                us.setClave(rs.getString("clave"));
                us.setEstado(rs.getString("estado"));
                us.setRol(new Rol(rs.getInt("idrol")));

                listado.add(us);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listado;
    }

    public Persona getByID(String login) {

        String query = "SELECT \n"
                + "    p.id_persona,\n"
                + "    p.prim_nomb,\n"
                + "    p.seg_nomb,\n"
                + "    p.ape_pater,\n"
                + "    p.ape_mater,\n"
                + "    td.nom_TD as tipo_documento,\n"
                + "    p.num_doc,\n"
                + "    p.correo,\n"
                + "    u.login,\n"
                + "    u.estado,\n"
                + "    r.nombre as rol\n"
                + "FROM \n"
                + "    Persona p\n"
                + "JOIN \n"
                + "    Usuario u ON p.login = u.login\n"
                + "JOIN \n"
                + "    Rol r ON u.idrol = r.idrol\n"
                + "JOIN \n"
                + "    Tipo_Documento td ON p.nom_TD = td.nom_TD\n"
                + "WHERE\n"
                + "    u.login = ?";
        Persona persona = new Persona();

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                persona.setPri_nombre(rs.getString("prim_nomb"));
                persona.setSeg_nombre(rs.getString("seg_nomb"));
                persona.setApe_paterno(rs.getString("ape_pater"));
                persona.setApe_materno(rs.getString("ape_mater"));

                Tipo_Documento tipoDoc = new Tipo_Documento(rs.getString("tipo_documento"));
                persona.setTipoDoc(tipoDoc);
                persona.setNum_Doc(rs.getString("num_doc"));
                persona.setCorreo(rs.getString("correo"));

                Usuario us = new Usuario();
                us.setLogin(rs.getString("login"));
                us.setEstado(rs.getString("estado"));

                Rol rol = new Rol(rs.getString("rol"));
                us.setRol(rol);

                persona.setUs(us);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return persona;
    }

    public List<Pagina> redireccionas(int idrol) {

        List<Pagina> paginas = new ArrayList<>();
        String query = "SELECT DISTINCT o.pagina "
                + "FROM opcion o "
                + "INNER JOIN permiso p ON o.id_opcion = p.id_opcion "
                + "WHERE p.idrol = ?";

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idrol);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pagina pagina = new Pagina();
                pagina.setNombrePagina(rs.getString("pagina"));
                paginas.add(pagina);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return paginas;
    }

    public List<Pagina> getPaginas() {

        List<Pagina> paginas = new ArrayList<>();
        String query = "SELECT * FROM opcion";

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Pagina pagina = new Pagina();
                pagina.setNombrePagina(rs.getString("pagina"));
                paginas.add(pagina);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return paginas;
    }

    //Metodo a ser usado en una api
    public List<Persona> getInformacion() {

        String query = "SELECT \n"
                + "    p.id_persona,\n"
                + "    p.prim_nomb,\n"
                + "    p.seg_nomb,\n"
                + "    p.ape_pater,\n"
                + "    p.ape_mater,\n"
                + "    td.nom_TD as tipo_documento,\n"
                + "    p.num_doc,\n"
                + "    p.correo,\n"
                + "    u.login,\n"
                + "    u.estado,\n"
                + "    r.nombre as rol\n"
                + "FROM \n"
                + "    Persona p\n"
                + "JOIN \n"
                + "    Usuario u ON p.login = u.login\n"
                + "JOIN \n"
                + "    Rol r ON u.idrol = r.idrol\n"
                + "JOIN \n"
                + "    Tipo_Documento td ON p.nom_TD = td.nom_TD;";
        List<Persona> lista = new ArrayList<>();
        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Persona persona = new Persona();
                persona.setPri_nombre(rs.getString("prim_nomb"));
                persona.setSeg_nombre(rs.getString("seg_nomb"));
                persona.setApe_paterno(rs.getString("ape_pater"));
                persona.setApe_materno(rs.getString("ape_mater"));

                Tipo_Documento tipoDoc = new Tipo_Documento(rs.getString("tipo_documento"));
                persona.setTipoDoc(tipoDoc);
                persona.setNum_Doc(rs.getString("num_doc"));
                persona.setCorreo(rs.getString("correo"));

                Usuario us = new Usuario();
                us.setLogin(rs.getString("login"));
                us.setEstado(rs.getString("estado"));

                Rol rol = new Rol(rs.getString("rol"));
                us.setRol(rol);

                persona.setUs(us);

                lista.add(persona);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

//    public static void main(String[] args) {
//
//        Persona persona = new Persona("Robert", "Pierre", "Carrasco", "Mariñas", new Tipo_Documento("DNI"), "12345678", "robert@gmail.com",
//                new Usuario("setBert", "123", "'Activo'", new Rol(3)));
//
//        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
//        int resultado = usuarioDAO.registro(persona);
//
//        if (resultado == 1) {
//            System.out.println("Se ha insertado correctamente.");
//        } else {
//            System.out.println("Ha ocurrido un problema al insertar.");
//        }
//    }
}

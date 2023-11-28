package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Rol;
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

    @Override
    public int add(Usuario objeto) {

        String query = "Insert Into usuario (login, clave, estado, idrol)";

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

        String query = "Delete from usuario Where login = ?";

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

    public Usuario getByID(String login) {

        String query = "Select * from usuario where login = ?";
        Usuario us = new Usuario();

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                us.setClave(rs.getString("clave"));
                us.setEstado(rs.getString("estado"));
                us.setRol(new Rol(rs.getInt("idrol")));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return us;
    }

}

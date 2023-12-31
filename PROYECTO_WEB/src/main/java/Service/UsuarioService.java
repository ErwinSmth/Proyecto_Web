package Service;

import java.util.List;

import dao.UsuarioDAOImpl;
import model.Pagina;
import model.Persona;
import model.Tipo_Documento;
import model.Tipo_Tramite;
import model.Usuario;

public class UsuarioService {

    public Usuario login(String login, String clave) {
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.logeo(login, clave);
    }

    public int addUsuario(Usuario us) {
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.add(us);
    }

    public int updateUsuario(Usuario us) {
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.update(us);
    }

    public int delete(Usuario us) {
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.delete(us);
    }

    public List<Usuario> getUsuarios() {
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.getListado();
    }

    public Persona getUsByID(String login) {
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.getByID(login);
    }

    public List<Pagina> redirecciones(int rol) {

        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.redireccionas(rol);

    }

    public List<Pagina> getPaginas() {

        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.getPaginas();

    }

    public int Registro(Persona persona) {

        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.registro(persona);

    }

    public List<Tipo_Documento> getTiposDoc() {

        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.getTipoDocu();

    }

    public List<Persona> getInformacion() {

        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.getInformacion();

    }

    public int reactivar(Usuario objeto) {

        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.reactivar(objeto);

    }

    public void addInteresado(int id_persona) {
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        usDao.addInteresado(id_persona);
    }

    public void addAdministrador(int id_persona) {
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        usDao.addAdministrador(id_persona);
    }

    public void addEspecialista(int id_persona, Tipo_Tramite tipoTramite) {

        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        usDao.addEspecialista(id_persona, tipoTramite);

    }

    public int getLastID() {

        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.getLastID();

    }

}

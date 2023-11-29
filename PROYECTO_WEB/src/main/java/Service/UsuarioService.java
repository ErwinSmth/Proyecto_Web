package Service;

import java.util.List;

import dao.UsuarioDAOImpl;
import model.Usuario;

public class UsuarioService {

    public Usuario login(String login, String clave){
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.logeo(login, clave);
    }
    
    public int addUsuario(Usuario us){
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.add(us);
    }

    public int updateUsuario(Usuario us){
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.update(us);
    }

    public int delete(Usuario us){
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.delete(us);
    }

    public List<Usuario> getUsuarios(){
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.getListado();
    }

    public Usuario getUsByID(String login){
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.getByID(login);
    }
    
    public List<String> redirecciones(int rol){
        
        UsuarioDAOImpl usDao = new UsuarioDAOImpl();
        return usDao.redireccionas(rol);
        
    }

}

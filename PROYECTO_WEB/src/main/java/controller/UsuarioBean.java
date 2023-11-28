package controller;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

import Service.UsuarioService;
import model.Usuario;

@ManagedBean
@RequestScoped
public class UsuarioBean {
    
    private String login;
    private String clave;
    private UsuarioService usService = new UsuarioService();

    public List<Usuario> getUsuarios(){
        return usService.getUsuarios();
    }

    public int addUsuario(Usuario us){
        return usService.addUsuario(us);
    }

    public int updateUsuario(Usuario us){
        return usService.updateUsuario(us);
    }

    public int deleteUsuario(Usuario us){
        return usService.delete(us);
    }

    public Usuario getByID(String login){
        return usService.getUsByID(login);
    }

}

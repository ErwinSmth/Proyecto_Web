package controller;

import java.util.List;

import javax.faces.bean.*;

import Service.UsuarioService;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import model.Rol;
import model.Usuario;

@ManagedBean(name = "userBean")
@RequestScoped
public class UsuarioBean {

    private String login;
    private String clave;
    private UsuarioService usService;

    @PostConstruct
    public void init() {
        this.usService = new UsuarioService();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public List<Usuario> getUsuarios() {
        return usService.getUsuarios();
    }

    public void addUsuario() throws IOException {
        Usuario us = new Usuario();
        us.setLogin(login);
        us.setClave(clave);
        us.setEstado("Activo");
        us.setRol(new Rol(1)); //Siempre los que se registren en la interfaz de registro.xhtml van a ser interesados

        if (usService.addUsuario(us) == 1) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("registro.xhtml");
        }

    }

    public int updateUsuario(Usuario us) {
        return usService.updateUsuario(us);
    }

    public int deleteUsuario(Usuario us) {
        return usService.delete(us);
    }

    public Usuario getByID(String login) {
        return usService.getUsByID(login);
    }

}

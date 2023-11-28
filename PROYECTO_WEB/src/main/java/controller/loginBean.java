package controller;

import java.io.IOException;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import Service.UsuarioService;
import javax.annotation.PostConstruct;
import model.Usuario;

@ManagedBean(name = "user") 
@RequestScoped
public class loginBean {
    
    private  UsuarioService usSer;
    private String login;
    private String clave;
    
    @PostConstruct
    public void init() {
        this.usSer = new UsuarioService();
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

    public void validacion() throws IOException{

        Usuario user = usSer.login(login, clave);

        if (!user.getLogin().isEmpty()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }

    }

}

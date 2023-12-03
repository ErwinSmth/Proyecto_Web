package controller;

import model.Pagina;
import java.io.IOException;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import Service.UsuarioService;
import java.util.List;
import javax.annotation.PostConstruct;
import model.Rol;
import model.Usuario;

@ManagedBean(name = "login")
@RequestScoped
public class loginBean {

    private Usuario us;
    private UsuarioService usSer;
    private int idrol;
    private String login;
    private String clave;
    private Rol rol;
    private List<Pagina> paginas;

    @PostConstruct
    public void init() {
        this.usSer = new UsuarioService();
        this.us = new Usuario();

    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public List<Pagina> getPaginas() {
        return paginas;
    }

    public void setPaginas(List<Pagina> paginas) {
        this.paginas = paginas;
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

    public void validacion() throws IOException {

        Usuario uselog = usSer.login(login, clave);
        setRol(uselog.getRol());
        us = uselog;

        if (!uselog.getLogin().isEmpty()) {
            
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", uselog);
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("paginasPorRol", usSer.redirecciones(uselog.getRol().getIdrol()));


            FacesContext.getCurrentInstance().getExternalContext().redirect("Menu.xhtml");

            //1 --> interesado
            //2 --> especialista
            //3 --> administrador
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        }

    }

    public void redireccion() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().redirect("Registro de Usuario.xhtml");

    }

}

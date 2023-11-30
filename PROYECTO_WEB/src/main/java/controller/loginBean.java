package controller;

import model.Pagina;
import java.io.IOException;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import Service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import model.Rol;
import model.Usuario;

@ManagedBean(name = "user")
@RequestScoped
public class loginBean {

    private Usuario us;
    private UsuarioService usSer;
    private int idrol;
    private List<List<Pagina>> paginasPorRol;
    private String login;
    private String clave;
    private Rol rol;

    @PostConstruct
    public void init() {
        this.usSer = new UsuarioService();
        this.us = new Usuario();
        this.paginasPorRol = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            List<Pagina> paginas = usSer.redirecciones(i + 1);
            paginasPorRol.add(paginas);

        }
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

//    public void setPaginas(List<Pagina> paginas) {
//        this.paginas = usSer.redirecciones(us.getRol().getIdrol());
//    }
    public List<Pagina> getTodasPaginas() {
        return usSer.getPaginas();

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

        Usuario user = usSer.login(login, clave);
        setRol(user.getRol());

        if (!user.getLogin().isEmpty()) {

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);

//            // Obtener las páginas por rol después de la autenticación
//            for (int i = 0; i < 3; i++) {
//                List<Pagina> paginas = usSer.redirecciones(i + 1);
//                paginasPorRol.add(paginas);
//            }
//
//            // Almacenar las páginas por rol en la sesión del usuario
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("paginasPorRol", paginasPorRol);
            List<Pagina> paginasporRol = usSer.redirecciones(user.getRol().getIdrol());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("paginasPorRol", paginasporRol);

            // Redirigir al menú después de la autenticación
            FacesContext.getCurrentInstance().getExternalContext().redirect("Menu.xhtml");

            //1 --> interesado
            //2 --> especialista
            //3 --> administrador
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        }

    }

    public List<List<Pagina>> getPaginasPorRol() {
        return (List<List<Pagina>>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("paginasPorRol");
    }

    public void redireccion() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().redirect("Registro de Usuario.xhtml");

    }

    public String redirectPage(String nombrePagina) {
        return nombrePagina + "?faces-redirect=true";
    }

}

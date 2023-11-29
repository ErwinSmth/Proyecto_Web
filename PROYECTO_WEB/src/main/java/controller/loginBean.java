package controller;

import java.io.IOException;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import Service.UsuarioService;
import java.util.List;
import javax.annotation.PostConstruct;
import model.Usuario;

@ManagedBean(name = "user")
@RequestScoped
public class loginBean {

    private UsuarioService usSer;
    private String login;
    private String clave;
    private List<String> paginas;

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

    public List<String> getPaginas() {
        return paginas;
    }

    public void setPaginas(List<String> paginas) {
        this.paginas = paginas;
    }
    
    

    public void validacion() throws IOException {

        Usuario user = usSer.login(login, clave);

        if (!user.getLogin().isEmpty()) {
            System.out.println("Si se logeo");
            int idrol = user.getRol().getIdrol();
            System.out.println(idrol);
            this.paginas = usSer.redirecciones(idrol);
            
            System.out.println(paginas);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("paginas", this.paginas);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("Menu.xhtml");

        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        }

    }

    public void redireccion() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().redirect("registro.xhtml");

    }

}

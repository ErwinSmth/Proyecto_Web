package controller;

import model.Pagina;
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
    private int idrol;
    private List<Pagina> paginas;

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

    public List<Pagina> getTodasPaginas() {
        return usSer.getPaginas();

    }

    public void validacion() throws IOException {

        Usuario user = usSer.login(login, clave);

        if (!user.getLogin().isEmpty()) {
            System.out.println("Si se logeo");
            int id = user.getRol().getIdrol();
            setIdrol(id);
            System.out.println(id);
            this.paginas = usSer.redirecciones(3);

            //1 --> interesado
            //2 --> especialista
            //3 --> administrador

            System.out.println(getPaginas());
            FacesContext.getCurrentInstance().getExternalContext().redirect("Menu.xhtml");

        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        }

    }

    public void redireccion() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().redirect("registro.xhtml");

    }

}

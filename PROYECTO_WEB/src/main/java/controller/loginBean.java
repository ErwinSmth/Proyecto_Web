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

    private Usuario us;
    private UsuarioService usSer;
    private int idrol;
    private List<Pagina> paginas;

    @PostConstruct
    public void init() {
        this.usSer = new UsuarioService();
        this.us = new Usuario();
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
        this.paginas = usSer.redirecciones(us.getRol().getIdrol());
    }

    public List<Pagina> getTodasPaginas() {
        return usSer.getPaginas();

    }

    public void validacion() throws IOException {

        Usuario user = usSer.login(us.getLogin(), us.getClave());

        if (!user.getLogin().isEmpty()) {
            System.out.println("Si se logeo");
            int id = user.getRol().getIdrol();
            setIdrol(id);
            System.out.println(id);
            this.paginas = usSer.redirecciones(user.getRol().getIdrol());
            System.out.println(this.paginas);

            //1 --> interesado
            //2 --> especialista
            //3 --> administrador

            FacesContext.getCurrentInstance().getExternalContext().redirect("Menu.xhtml");

        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        }

    }

    public void redireccion() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().redirect("Registro de Usuario.xhtml");

    }

}

package controller;

import java.util.List;
import javax.faces.bean.*;

import Service.OpcionesService;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import model.Opciones;
import model.Usuario;

@ManagedBean(name = "opcionBean")
@RequestScoped

public class OpcionBean {

    private static final int ROL_ADMINISTRADOR = 3;
    private OpcionesService objCS;

    @PostConstruct
    public void init() {
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user == null || user.getRol().getIdrol() != ROL_ADMINISTRADOR) {
        }
        this.objCS = new OpcionesService();
    }

    public List<Opciones> getOpciones() {
        return objCS.getOpcionesLista();

    }

    public void agregar(Opciones op) {

    }

    public void actualizar(Opciones op) {

    }

    public void eliminar(Opciones op) {

    }

}

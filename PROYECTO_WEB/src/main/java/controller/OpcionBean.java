package controller;

import java.util.List;
import javax.faces.bean.*;

import Service.OpcionesService;
import model.Opciones;

@ManagedBean(name = "opcionBean")
@RequestScoped

public class OpcionBean {

    private OpcionesService objCS = new OpcionesService();

    public List<Opciones> getOpciones() {
        return objCS.getOpcionesLista();

    }

    public OpcionBean() {
    }

    public void agregar(Opciones op) {

    }

    public void actualizar(Opciones op) {

    }

    public void eliminar(Opciones op) {

    }

}

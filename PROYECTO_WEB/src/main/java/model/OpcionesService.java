
package model;

import dao.OpcionesDaoImpl;
import java.util.List;

public class OpcionesService {
    

    public List<Opciones> getOpcionesLista()
    {
        OpcionesDaoImpl op = new OpcionesDaoImpl();
        return op.getOpciones();
    }

    public Opciones getOpcionById(int id) {
        OpcionesDaoImpl op = new OpcionesDaoImpl();
        return op.getOpcionById(id);
    }

    public void agregarOpciones(Opciones op) {

        OpcionesDaoImpl opDao = new OpcionesDaoImpl();
        opDao.agregarOpciones(op);

    }

    public void actualizarOpciones(Opciones op) {

        OpcionesDaoImpl opDao = new OpcionesDaoImpl();
        opDao.actualizarOpciones(op);

    }

    public void eliminarOpciones(Opciones op) {
        OpcionesDaoImpl opDao = new OpcionesDaoImpl();
        opDao.eliminarOpciones(op);
    }

}

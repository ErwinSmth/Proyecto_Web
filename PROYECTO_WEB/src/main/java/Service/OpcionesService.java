
package Service;

import java.util.List;

import dao.OpcionesDaoImpl;
import model.Opciones;

public class OpcionesService {
    

    public List<Opciones> getOpcionesLista()
    {
        OpcionesDaoImpl op = new OpcionesDaoImpl();
        return op.getListado();
    }

    public Opciones getOpcionById(int id) {
        OpcionesDaoImpl op = new OpcionesDaoImpl();
        return op.getByID(id);
    }

    public void agregarOpciones(Opciones op) {

        OpcionesDaoImpl opDao = new OpcionesDaoImpl();
        opDao.add(op);

    }

    public void actualizarOpciones(Opciones op) {

        OpcionesDaoImpl opDao = new OpcionesDaoImpl();
        opDao.update(op);

    }

    public void eliminarOpciones(Opciones op) {
        OpcionesDaoImpl opDao = new OpcionesDaoImpl();
        opDao.delete(op);
    }

}

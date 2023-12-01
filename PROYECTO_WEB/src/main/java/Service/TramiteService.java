/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import dao.TramiteDaoImpl;
import java.util.List;
import model.Tramite;

/**
 *
 * @author DAVID
 */
public class TramiteService {

    public int add(Tramite objeto) {

        TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
        return tramiteDao.add(objeto);

    }

    public int update(Tramite objeto) {

        TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
        return tramiteDao.update(objeto);

    }

    public int delete(Tramite objeto) {

        TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
        return tramiteDao.delete(objeto);

    }

    public List<Tramite> getListado() {

        TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
        return tramiteDao.getListado();

    }

    public List<Tramite> getListadoByID(int id) {

        TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
        return tramiteDao.getListadoByID(id);

    }

}

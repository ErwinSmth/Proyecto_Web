/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import dao.Tipo_TramiteDAOImpl;
import java.util.List;
import model.Tipo_Tramite;

/**
 *
 * @author DAVID
 */
public class Tipo_TramiteService {

    public int add(Tipo_Tramite tipoTra) {

        Tipo_TramiteDAOImpl tiTraDao = new Tipo_TramiteDAOImpl();
        return tiTraDao.add(tipoTra);

    }

    public int update(Tipo_Tramite tipoTra) {

        Tipo_TramiteDAOImpl tiTraDao = new Tipo_TramiteDAOImpl();
        return tiTraDao.update(tipoTra);

    }

    public int delete(Tipo_Tramite tipoTra) {

        Tipo_TramiteDAOImpl tiTraDao = new Tipo_TramiteDAOImpl();
        return tiTraDao.delete(tipoTra);

    }

    public List<Tipo_Tramite> getListado() {

        Tipo_TramiteDAOImpl tiTraDao = new Tipo_TramiteDAOImpl();
        return tiTraDao.getListado();

    }
}

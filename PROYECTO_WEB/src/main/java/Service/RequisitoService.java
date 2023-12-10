/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import dao.RequisitoDAOImpl;
import java.util.List;
import model.Requisito;
import model.Tipo_Tramite;

/**
 *
 * @author DAVID
 */
public class RequisitoService {

    public int add(Requisito objeto) {

        RequisitoDAOImpl requiDao = new RequisitoDAOImpl();
        return requiDao.add(objeto);

    }

    public int update(Requisito objeto) {

        RequisitoDAOImpl requiDao = new RequisitoDAOImpl();
        return requiDao.update(objeto);

    }

    public int delete(Requisito objeto) {

        RequisitoDAOImpl requiDao = new RequisitoDAOImpl();
        return requiDao.delete(objeto);

    }

    public List<Requisito> getListado() {

        RequisitoDAOImpl requiDao = new RequisitoDAOImpl();
        return requiDao.getListado();

    }

    public int Reactivar(Requisito objeto) {

        RequisitoDAOImpl requiDao = new RequisitoDAOImpl();
        return requiDao.Reactivar(objeto);

    }

    public List<Requisito> getRequisitosByTiTramite(Tipo_Tramite tipoTramite) {

        RequisitoDAOImpl requiDao = new RequisitoDAOImpl();
        return requiDao.getRequisitosByTiTramite(tipoTramite);
        
    }
}

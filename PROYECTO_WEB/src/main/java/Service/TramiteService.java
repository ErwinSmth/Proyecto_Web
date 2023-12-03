/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import dao.TramiteDaoImpl;
import java.util.List;
import model.Persona;
import model.Requisito;
import model.Tipo_Tramite;
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

    public Persona getPersonaByUs(String login) {

        TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
        return tramiteDao.getPersonaByUS(login);

    }

    public List<Tipo_Tramite> getListaTra() {

        TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
        return tramiteDao.getlistaTiTra();

    }

    public int LastID() {

        TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
        return tramiteDao.getLastID();

    }

    public int addRequisito(int idtramite, String nombre_TT) {

        TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
        return tramiteDao.addRequisito_Tramite(idtramite, nombre_TT);

    }

    public List<Requisito> getRequisitos(int idtramite, String nombreTT) {

        TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
        return tramiteDao.getRequisitosByTipoTramite(idtramite, nombreTT);

    }

}

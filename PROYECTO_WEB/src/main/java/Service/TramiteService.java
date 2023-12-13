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

    public List<Tramite> getListadoByLogin(String Login) {

        TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
        return tramiteDao.getListadoByLogin(Login);

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

    public int addRequisito(int idtramite, Tipo_Tramite tipoTra) {

        TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
        return tramiteDao.addRequisito_Tramite(idtramite, tipoTra);

    }

    public List<Requisito> getRequisitos(int idtramite, String nombreTT) {

        TramiteDaoImpl tramiteDao = new TramiteDaoImpl();
        return tramiteDao.getRequisitosByTipoTramite(idtramite, nombreTT);

    }

}

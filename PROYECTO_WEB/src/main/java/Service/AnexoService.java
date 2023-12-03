/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import dao.AnexoDAOImpl;
import java.util.List;
import model.Anexo;

/**
 *
 * @author DAVID
 */
public class AnexoService {

    public int add(Anexo objeto) {

        AnexoDAOImpl anexoDao = new AnexoDAOImpl();
        return anexoDao.add(objeto);

    }

    public int update(Anexo objeto) {

        AnexoDAOImpl anexoDao = new AnexoDAOImpl();
        return anexoDao.update(objeto);

    }

    public int delete(Anexo objeto) {

        AnexoDAOImpl anexoDao = new AnexoDAOImpl();
        return anexoDao.delete(objeto);
    }

    public List<Anexo> getListado() {

        AnexoDAOImpl anexoDao = new AnexoDAOImpl();
        return anexoDao.getListado();

    }

    public List<Anexo> getListadoByID(int id) {

        AnexoDAOImpl anexoDao = new AnexoDAOImpl();
        return anexoDao.getListadoByID(id);

    }
}

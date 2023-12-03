/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Service.RequisitoService;
import Service.Tipo_TramiteService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import model.Requisito;
import model.Tipo_Tramite;
import model.Usuario;

/**
 *
 * @author DAVID
 */
@ManagedBean(name = "requisitoBean")
@RequestScoped
public class RequisitoBean {

    private static final int ROL_ADMINISTRADOR = 3;
    private RequisitoService requiServ;
    private Requisito requisito;
    private List<Tipo_Tramite> listaTipos;
    private Tipo_TramiteService tipoTraServ;

    @PostConstruct
    public void init() {
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user == null || user.getRol().getIdrol() != ROL_ADMINISTRADOR) {
        }
        this.requiServ = new RequisitoService();
        this.requisito = new Requisito();
        this.tipoTraServ = new Tipo_TramiteService();
        this.listaTipos = tipoTraServ.getListado();

    }
    public List<Tipo_Tramite> getListaTipos() {
        return listaTipos;
    }

    public void setListaTipos(List<Tipo_Tramite> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public Requisito getRequisito() {
        return requisito;
    }

    public void setRequisito(Requisito requisito) {
        this.requisito = requisito;
    }

    public void add() {

        Requisito newRequisito = new Requisito(requisito.getNom_Req(), new Tipo_Tramite(requisito.getTipo_tramite().getNom_TT()),
                requisito.getDescripcion());
        
        if (requiServ.add(newRequisito) == 1) {
            requisito.setNom_Req("");
        } else {
            System.out.println("ERROR");
        }

    }

    public void update(Requisito requisito) {

        if (requiServ.update(requisito) == 1) {
        } else {
        }

    }

    public void delete(Requisito requisito) {

        if (requiServ.delete(requisito) == 1) {
        } else {
        }

    }

    public List<Requisito> getListado() {
        return requiServ.getListado();
    }

    public void Reactivar(Requisito objeto) {

        if (requiServ.Reactivar(objeto) == 1) {
        } else {
        }

    }

}

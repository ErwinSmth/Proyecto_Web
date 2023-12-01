/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Service.Tipo_TramiteService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import model.Tipo_Tramite;
import model.Usuario;

/**
 *
 * @author DAVID
 */
@ManagedBean(name = "tipo_TramiteBean")
@RequestScoped
public class Tipo_TramiteBean {
    
    private static final int ROL_ADMINISTRADOR = 3;
    private Tipo_TramiteService tiTraSer;
    private Tipo_Tramite tipoTramite;

    @PostConstruct
    public void init() {
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user == null || user.getRol().getIdrol() != ROL_ADMINISTRADOR) {
        }
        this.tiTraSer = new Tipo_TramiteService();
        this.tipoTramite = new Tipo_Tramite();
    }

    public Tipo_Tramite getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(Tipo_Tramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public void add() {

        if (tiTraSer.add(tipoTramite) == 1) {
            tipoTramite.setNom_TT("");
            tipoTramite.setDescripcion("");
        } else {
            //Ocurrio un Error
        }

    }

    public void update(Tipo_Tramite tipoTramite) {
        System.out.println("Se ejecuta");
        if (tiTraSer.update(tipoTramite) == 1) {
            System.out.println(tipoTramite.toString());
            System.out.println("Se actualizo");
        } else {
            System.out.println(tipoTramite.toString());
            System.out.println("No se actualizo");
        }

    }

    public void delete(Tipo_Tramite tipoTramite) {

        if (tiTraSer.delete(tipoTramite) == 1) {

        } else {

        }

    }

    public List<Tipo_Tramite> getListado() {
        return tiTraSer.getListado();
    }

}

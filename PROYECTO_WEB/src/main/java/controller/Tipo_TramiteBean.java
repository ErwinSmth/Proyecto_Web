/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Service.Tipo_TramiteService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import model.Tipo_Tramite;

/**
 *
 * @author DAVID
 */
@ManagedBean(name = "tipo_TramiteBean")
@RequestScoped
public class Tipo_TramiteBean {

    private String Nom_TT;
    private String Descripcion;
    private Tipo_TramiteService tiTraSer;

    @PostConstruct
    public void init() {
        this.tiTraSer = new Tipo_TramiteService();
    }

    public String getNom_TT() {
        return Nom_TT;
    }

    public void setNom_TT(String Nom_TT) {
        this.Nom_TT = Nom_TT;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    public void add() {

        Tipo_Tramite tipoTra = new Tipo_Tramite();
        tipoTra.setNom_TT(Nom_TT);
        tipoTra.setDescripcion(Descripcion);

        if (tiTraSer.add(tipoTra) == 1) {
            //Agregado Exitosamene
        } else {
            //Ocurrio un Error
        }

    }

    public void update() {

        Tipo_Tramite tipoTra = new Tipo_Tramite();
        tipoTra.setNom_TT(Nom_TT);
        tipoTra.setDescripcion(Descripcion);

        if (tiTraSer.update(tipoTra) == 1) {

        } else {

        }

    }

    public void delete() {

        Tipo_Tramite tipoTra = new Tipo_Tramite();
        tipoTra.setNom_TT(Nom_TT);
        tipoTra.setDescripcion(Descripcion);

        if (tiTraSer.delete(tipoTra) == 1) {

        } else {

        }

    }
    
    public List<Tipo_Tramite> getListado(){
        return tiTraSer.getListado(); 
    }

}

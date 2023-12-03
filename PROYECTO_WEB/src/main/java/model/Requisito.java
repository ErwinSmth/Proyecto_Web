/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DAVID
 */
public class Requisito {

    private String nom_Req;
    private Tipo_Tramite tipo_tramite;
    private int activo; //1 si esta activo y 2 si esta inactivo
    private String descripcion;
    private int estado; //atributo a solo ser usado por la tabla requisito_tramite_estado

    public Requisito(String nom_Req, Tipo_Tramite tipo_tramite, int activo, String descripcion) {
        this.nom_Req = nom_Req;
        this.tipo_tramite = tipo_tramite;
        this.activo = activo;
        this.descripcion = descripcion;
    }

    public Requisito(String nom_Req, Tipo_Tramite tipo_tramite, String descripcion) {
        this.nom_Req = nom_Req;
        this.tipo_tramite = tipo_tramite;
        this.descripcion = descripcion;
    }

    public Requisito() {
    }

    public Tipo_Tramite getTipo_tramite() {
        return tipo_tramite;
    }

    public void setTipo_tramite(Tipo_Tramite tipo_tramite) {
        this.tipo_tramite = tipo_tramite;
    }

    public String getNom_Req() {
        return nom_Req;
    }

    public void setNom_Req(String nom_Req) {
        this.nom_Req = nom_Req;
    }


    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Requisito{" + "nom_Req=" + nom_Req + ", tipo_tramite=" + tipo_tramite + ", activo=" + activo + ", descripcion=" + descripcion + '}';
    }


    

}

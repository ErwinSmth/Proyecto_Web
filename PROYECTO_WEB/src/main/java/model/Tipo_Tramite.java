/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DAVID
 */
public class Tipo_Tramite {
    
    private String nom_TT;
    private String descripcion;

    public Tipo_Tramite(String nom_TT, String descripcion) {
        this.nom_TT = nom_TT;
        this.descripcion = descripcion;
    }

    public Tipo_Tramite(String nom_TT) {
        this.nom_TT = nom_TT;
    }
    
    

    public Tipo_Tramite() {
    }

    public String getNom_TT() {
        return nom_TT;
    }

    public void setNom_TT(String nom_TT) {
        this.nom_TT = nom_TT;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Tipo_Tramite{" + "nom_TT=" + nom_TT + ", descripcion=" + descripcion + '}';
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DAVID
 */
public class Tipo_Documento {

    private String nombre_TD;

    public Tipo_Documento() {
    }

    public Tipo_Documento(String nombre_TD) {
        this.nombre_TD = nombre_TD;
    }

    public String getNombre_TD() {
        return nombre_TD;
    }

    public void setNombre_TD(String nombre_TD) {
        this.nombre_TD = nombre_TD;
    }

    @Override
    public String toString() {
        return "Tipo_Documento{" + "nombre_TD=" + nombre_TD + '}';
    }

}

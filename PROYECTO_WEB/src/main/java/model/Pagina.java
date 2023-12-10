/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author DAVID
 */
public class Pagina implements Serializable{
    
    private String nombrePagina;

    public Pagina() {
    }

    public Pagina(String nombrePagina) {
        this.nombrePagina = nombrePagina;
    }

    public String getNombrePagina() {
        return nombrePagina;
    }

    public void setNombrePagina(String nombrePagina) {
        this.nombrePagina = nombrePagina;
    }

    @Override
    public String toString() {
        return "Pagina{" + "nombrePagina=" + nombrePagina + '}';
    }
    
    
    
}

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
public class Rol implements Serializable{
    
    private int idrol;
    private String nombre;

    public Rol(int idrol, String nombre) {
        this.idrol = idrol;
        this.nombre = nombre;
    }

    public Rol() {
    }

    public Rol(int idrol) {
        this.idrol = idrol;
    }

    public Rol(String nombre) {
        this.nombre = nombre;
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Rol{" + "idrol=" + idrol + ", nombre=" + nombre + '}';
    }
    
    
    
}

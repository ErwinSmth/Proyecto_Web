/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DAVID
 */
public class Usuario {
    
    private String login;
    private String clave;
    private String estado;
    private Rol rol;

    public Usuario() {
    }

    public Usuario(String login, String clave, String estado, Rol rol) {
        this.login = login;
        this.clave = clave;
        this.estado = estado;
        this.rol = rol;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "login=" + login + ", clave=" + clave + ", estado=" + estado + ", rol=" + rol + '}';
    }
    
}

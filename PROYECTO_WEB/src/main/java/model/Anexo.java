/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author DAVID
 */
public class Anexo {
    
    private int id_documento;
    private Tramite tramite;
    private String titulo;
    private String descripcion;
    private LocalDate fecha_registro;

    public Anexo(int id_documento, Tramite tramite, String titulo, String descripcion, LocalDate fecha_registro) {
        this.id_documento = id_documento;
        this.tramite = tramite;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_registro = fecha_registro;
    }

    public Anexo(Tramite tramite, String titulo, String descripcion, LocalDate fecha_registro) {
        this.tramite = tramite;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_registro = fecha_registro;
    }

    public Anexo() {
    }
   

    public int getId_documento() {
        return id_documento;
    }

    public void setId_documento(int id_documento) {
        this.id_documento = id_documento;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDate fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    @Override
    public String toString() {
        return "Anexo{" + "id_documento=" + id_documento + ", tramite=" + tramite + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fecha_registro=" + fecha_registro + '}';
    }
    
    
    
}

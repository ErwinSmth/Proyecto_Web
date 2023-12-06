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
    private String  ubicacion_archivo;

    public Anexo(int id_documento, Tramite tramite, String titulo, String descripcion, LocalDate fecha_registro, String ubicacion_archivo) {
        this.id_documento = id_documento;
        this.tramite = tramite;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_registro = fecha_registro;
        this.ubicacion_archivo = ubicacion_archivo;
    }

    public Anexo(Tramite tramite, String titulo, String descripcion, LocalDate fecha_registro, String ubicacion_archivo) {
        this.tramite = tramite;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_registro = fecha_registro;
        this.ubicacion_archivo = ubicacion_archivo;
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

    public String getUbicacion_archivo() {
        return ubicacion_archivo;
    }

    public void setUbicacion_archivo(String archivo) {
        this.ubicacion_archivo = ubicacion_archivo;
    }

    @Override
    public String toString() {
        return "Anexo{" + "id_documento=" + id_documento + ", tramite=" + tramite + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fecha_registro=" + fecha_registro + ", ubicacion_archivo=" + ubicacion_archivo + '}';
    }
    
    
    

}

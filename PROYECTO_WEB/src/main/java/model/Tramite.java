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
public class Tramite {
    
    private int id_tramite;
    private Persona persona;
    private Tipo_Tramite tipoTramite;
    private LocalDate fecha_inicio;
    private LocalDate fecha_finalizo;
    private int estado; //1 representa en proceso, 2 representa que acabo
    private int cant_Documentos;
    private LocalDate fecha_limite;

    public Tramite(int id_tramite, Persona persona, Tipo_Tramite tipoTramite, LocalDate fecha_inicio, LocalDate fecha_finalizo, int estado, int cant_Documentos, LocalDate fecha_limite) {
        this.id_tramite = id_tramite;
        this.persona = persona;
        this.tipoTramite = tipoTramite;
        this.fecha_inicio = fecha_inicio;
        this.fecha_finalizo = fecha_finalizo;
        this.estado = estado;
        this.cant_Documentos = cant_Documentos;
        this.fecha_limite = fecha_limite;
    }

    public Tramite(Persona persona, Tipo_Tramite tipoTramite, LocalDate fecha_inicio, LocalDate fecha_finalizo, int estado, int cant_Documentos, LocalDate fecha_limite) {
        this.persona = persona;
        this.tipoTramite = tipoTramite;
        this.fecha_inicio = fecha_inicio;
        this.fecha_finalizo = fecha_finalizo;
        this.estado = estado;
        this.cant_Documentos = cant_Documentos;
        this.fecha_limite = fecha_limite;
    }

    public int getId_tramite() {
        return id_tramite;
    }

    public void setId_tramite(int id_tramite) {
        this.id_tramite = id_tramite;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Tipo_Tramite getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(Tipo_Tramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_finalizo() {
        return fecha_finalizo;
    }

    public void setFecha_finalizo(LocalDate fecha_finalizo) {
        this.fecha_finalizo = fecha_finalizo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCant_Documentos() {
        return cant_Documentos;
    }

    public void setCant_Documentos(int cant_Documentos) {
        this.cant_Documentos = cant_Documentos;
    }

    public LocalDate getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(LocalDate fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    @Override
    public String toString() {
        return "Tramite{" + "id_tramite=" + id_tramite + ", persona=" + persona + ", tipoTramite=" + tipoTramite + ", fecha_inicio=" + fecha_inicio + ", fecha_finalizo=" + fecha_finalizo + ", estado=" + estado + ", cant_Documentos=" + cant_Documentos + ", fecha_limite=" + fecha_limite + '}';
    }
       
}

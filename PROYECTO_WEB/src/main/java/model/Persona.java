/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DAVID
 */
public class Persona {
    
    private int idpersona;
    private String pri_nombre;
    private String seg_nombre;
    private String ape_paterno;
    private String ape_materno;
    private Tipo_Documento tipoDoc;
    private String num_Doc;
    private String correo;
    private Usuario us;

    public Persona(int idpersona, String pri_nombre, String seg_nombre, String ape_paterno, String ape_materno, Tipo_Documento tipoDoc, String num_Doc, String correo, Usuario us) {
        this.idpersona = idpersona;
        this.pri_nombre = pri_nombre;
        this.seg_nombre = seg_nombre;
        this.ape_paterno = ape_paterno;
        this.ape_materno = ape_materno;
        this.tipoDoc = tipoDoc;
        this.num_Doc = num_Doc;
        this.correo = correo;
        this.us = us;
    }

    public Persona() {
    }

    public Persona(String pri_nombre, String seg_nombre, String ape_paterno, String ape_materno, Tipo_Documento tipoDoc, String num_Doc, String correo, Usuario us) {
        this.pri_nombre = pri_nombre;
        this.seg_nombre = seg_nombre;
        this.ape_paterno = ape_paterno;
        this.ape_materno = ape_materno;
        this.tipoDoc = tipoDoc;
        this.num_Doc = num_Doc;
        this.correo = correo;
        this.us = us;
    }

    public Persona(int idpersona) {
        this.idpersona = idpersona;
    }
    
    

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public String getPri_nombre() {
        return pri_nombre;
    }

    public void setPri_nombre(String pri_nombre) {
        this.pri_nombre = pri_nombre;
    }

    public String getSeg_nombre() {
        return seg_nombre;
    }

    public void setSeg_nombre(String seg_nombre) {
        this.seg_nombre = seg_nombre;
    }

    public String getApe_paterno() {
        return ape_paterno;
    }

    public void setApe_paterno(String ape_paterno) {
        this.ape_paterno = ape_paterno;
    }

    public String getApe_materno() {
        return ape_materno;
    }

    public void setApe_materno(String ape_materno) {
        this.ape_materno = ape_materno;
    }

    public Tipo_Documento getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(Tipo_Documento tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNum_Doc() {
        return num_Doc;
    }

    public void setNum_Doc(String num_Doc) {
        this.num_Doc = num_Doc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

    @Override
    public String toString() {
        return "Persona{" + "idpersona=" + idpersona + ", pri_nombre=" + pri_nombre + ", seg_nombre=" + seg_nombre + ", ape_paterno=" + ape_paterno + ", ape_materno=" + ape_materno + ", tipoDoc=" + tipoDoc + ", num_Doc=" + num_Doc + ", correo=" + correo + ", us=" + us + '}';
    }
    
    
}

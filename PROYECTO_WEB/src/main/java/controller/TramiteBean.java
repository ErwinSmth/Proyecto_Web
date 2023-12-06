/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Service.AnexoService;
import Service.ArchivoService;
import Service.TramiteService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import model.Anexo;
import model.Persona;
import model.Requisito;
import model.Tipo_Tramite;
import model.Tramite;
import model.Usuario;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author DAVID
 */
@ManagedBean(name = "tramiteBean")
@RequestScoped
public class TramiteBean {

    private static final int ROL_INTERESADO = 1;

    private Persona persona;
    private List<Tipo_Tramite> listado;
    private TramiteService traServ;
    private Tramite tramite;
    private Tipo_Tramite tipoTramiteSeleccionado;
    private List<Requisito> requisitos;
    private ArchivoService archivoServ;
    private AnexoService anexoServ;
    private Anexo anexo;

    @PostConstruct
    public void init() {

        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user == null || user.getRol().getIdrol() != ROL_INTERESADO) {
        }
        this.traServ = new TramiteService();
        this.persona = traServ.getPersonaByUs(user.getLogin());
        this.listado = traServ.getListaTra();
        this.tramite = new Tramite();
        this.archivoServ = new ArchivoService();
        this.anexoServ = new AnexoService();
        this.anexo = new Anexo();
    }

    public Tipo_Tramite getTipoTramiteSeleccionado() {
        return tipoTramiteSeleccionado;
    }

    public void setTipoTramiteSeleccionado(Tipo_Tramite tipoTramiteSeleccionado) {
        this.tipoTramiteSeleccionado = tipoTramiteSeleccionado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Tipo_Tramite> getListado() {
        return listado;
    }

    public void setListado(List<Tipo_Tramite> listado) {
        this.listado = listado;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    public List<Requisito> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(List<Requisito> requisitos) {
        this.requisitos = requisitos;
    }

    public ArchivoService getArchivoServ() {
        return archivoServ;
    }

    public void setArchivoServ(ArchivoService archivoServ) {
        this.archivoServ = archivoServ;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public void addTramite() {

        if (tipoTramiteSeleccionado == null) {
            return; // Evitar agregar un trámite si el tipo de trámite es nulo
        }

        try {
            Tramite newTramite = new Tramite(new Persona(persona.getIdpersona()), tipoTramiteSeleccionado);
            newTramite.setFecha_inicio(LocalDate.now());
            int resultado = this.traServ.add(newTramite);

            if (resultado == 1) {

                //Obtenemos el ultimo id
                int id = traServ.LastID();
                int resultadoRequisito = traServ.addRequisito(id, tipoTramiteSeleccionado.getNom_TT());

                if (resultadoRequisito == 1) {

                    //Obtenemos los requisitos
                    this.requisitos = traServ.getRequisitos(id, tipoTramiteSeleccionado.getNom_TT());

                }

            } else {
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

//    public void SubirArchivo(FileUploadEvent event) {
//        UploadedFile archivo = event.getFile();
//        System.out.println(anexo.toString());
//        String ruta = "./Documentos/hola.txt";
//        try {
//            
//            anexo.setTitulo("hola");
//            archivoServ.guardarArchivo(archivo.getInputStream(), ruta);
//            anexo.setTramite(tramite);
//            anexo.setFecha_registro(LocalDate.now());
//            anexo.setUbicacion_archivo( );
//
//            int resultado = anexoServ.add(anexo);
//
//            if (resultado == 1) {
//                System.out.println("Subido");
//            } else {
//                System.out.println("Ocurrio un error al intentar subir");
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}

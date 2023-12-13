/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Service.AnexoService;
import Service.ArchivoHelper;
import Service.RequisitoService;
import Service.TramiteService;
import java.io.IOException;
import java.io.InputStream;
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
    private AnexoService anexoServ;
    private Anexo anexo;
    private UploadedFile archivoSubido;
    private RequisitoService requiServ;

    @PostConstruct
    public void init() {

        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user == null || user.getRol().getIdrol() != ROL_INTERESADO) {
        }
        this.traServ = new TramiteService();
        this.persona = traServ.getPersonaByUs(user.getLogin());
        this.listado = traServ.getListaTra();
        this.tramite = new Tramite();
        this.anexoServ = new AnexoService();
        this.anexo = new Anexo();
        this.requiServ = new RequisitoService();
        this.tramite = new Tramite();

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

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public UploadedFile getArchivoSubido() {
        return archivoSubido;
    }

    public void setArchivoSubido(UploadedFile archivoSubido) {
        this.archivoSubido = archivoSubido;
    }

    public void obtenerRequisitos() {

        if (tipoTramiteSeleccionado == null) {
            return; // Evitar agregar un trámite si el tipo de trámite es nulo
        }
        try {
            this.requisitos = requiServ.getRequisitosByTiTramite(tipoTramiteSeleccionado);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void subir() {
        if (archivoSubido != null) {
            try {

                //Insercion del tramite
                this.tramite.setPersona(this.persona);
                this.tramite.setTipoTramite(this.tipoTramiteSeleccionado);
                this.tramite.setFecha_inicio(LocalDate.now());

                int resultadoTRA = traServ.add(tramite);
                System.out.println("El ultimo ID es: " +resultadoTRA);

                if (resultadoTRA != -1) {
                    ArchivoHelper upload = new ArchivoHelper();
                    String nombreArchivo = archivoSubido.getFileName();
                    InputStream input = archivoSubido.getInputStream();

                    String ruta = upload.subirArchivo(input, nombreArchivo);

                    Tramite tra = new Tramite();
                    tra.setId_tramite(traServ.LastID());

                    anexo.setTramite(tra);
                    anexo.setFecha_registro(LocalDate.now());
                    anexo.setDescripcion("");
                    anexo.setUbicacion_archivo(ruta);

                    int resultado = anexoServ.add(anexo);

                    if (resultado == 1) {
                        int resultadorequi = traServ.addRequisito(resultadoTRA, this.tipoTramiteSeleccionado);

                        if (resultadorequi == 1) {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("Seguimiento de Tramite.xhtml");
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

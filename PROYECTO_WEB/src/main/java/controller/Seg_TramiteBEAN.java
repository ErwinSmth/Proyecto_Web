/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Service.TramiteService;
import Service.UsuarioService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import model.Tramite;
import model.Usuario;

@ManagedBean(name = "seg_TramiteBEAN")
@RequestScoped
public class Seg_TramiteBEAN {

    private static final int ROL_INTERESADO = 1;

    private UsuarioService usServ;
    private TramiteService traServ;
    private List<Tramite> listaTramite;

    @PostConstruct
    public void init() {

        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user == null || user.getRol().getIdrol() != ROL_INTERESADO) {
        }
        
        this.usServ = new UsuarioService();
        this.traServ = new TramiteService();
        this.listaTramite = traServ.getListadoByLogin(user.getLogin());

    }
    
    public List<Tramite> getListaTramite() {
        return listaTramite;
    }  

}

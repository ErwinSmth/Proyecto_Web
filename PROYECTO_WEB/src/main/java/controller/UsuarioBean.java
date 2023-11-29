package controller;

import java.util.List;

import javax.faces.bean.*;

import Service.UsuarioService;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Persona;
import model.Rol;
import model.Tipo_Documento;
import model.Usuario;

@ManagedBean(name = "userBean")
@RequestScoped
public class UsuarioBean {

    private Usuario us;
    private Persona persona;
    private UsuarioService usService;
    private List<Tipo_Documento> listaDoc;

    @PostConstruct
    public void init() {
        this.usService = new UsuarioService();
        this.us = new Usuario();
        this.persona = new Persona();
        this.listaDoc = usService.getTiposDoc();
        System.out.println(listaDoc);
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

    public List<Usuario> getUsuarios() {
        return usService.getUsuarios();
    }

    public List<Tipo_Documento> getListaDoc() {
        return listaDoc;
    }

    public void setListaDoc(List<Tipo_Documento> listaDoc) {
        this.listaDoc = listaDoc;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void addUsuario() throws IOException {
        Usuario newUs = new Usuario();
        newUs.setLogin(us.getLogin());
        newUs.setClave(us.getClave());
        newUs.setEstado("Activo");
        newUs.setRol(new Rol(1)); //Siempre los que se registren en la interfaz de registro.xhtml van a ser interesados

        if (usService.addUsuario(newUs) == 1) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Registro de Usuario.xhtml");
        }

    }

    public int updateUsuario(Usuario us) {
        return usService.updateUsuario(us);
    }

    public int deleteUsuario(Usuario us) {
        return usService.delete(us);
    }

    public Usuario getByID(String login) {
        return usService.getUsByID(login);
    }

    private boolean validarDocumento(String tipoDoc, String numDoc) {
        if (numDoc == null) {
            return false; // Si el número de documento es nulo, retorna falso
        }

        switch (tipoDoc) {
            case "DNI":
                return numDoc.matches("\\d{8}"); // Verifica si son 8 dígitos numéricos
            case "Carnet_Extranjeria":
                return numDoc.matches("[a-zA-Z0-9]{12}"); // Verifica 12 caracteres alfanuméricos
            case "Pasaporte":
                return numDoc.matches("[a-zA-Z0-9]{12}"); // Verifica 12 caracteres alfanuméricos
            case "Ced_Diplomatica_Identidad":
                return numDoc.matches("[a-zA-Z0-9]{15}"); // Verifica 15 caracteres alfanuméricos
            default:
                return false; // Si el tipo de documento no coincide, retorna falso
        }
    }

    public int Registro() throws IOException {

        Persona newPer = new Persona(persona.getPri_nombre(), persona.getSeg_nombre(), persona.getApe_paterno(), persona.getApe_materno(), 
                new Tipo_Documento(persona.getTipoDoc().getNombre_TD()), persona.getNum_Doc(), persona.getCorreo(), 
                new Usuario(us.getLogin(), us.getClave(), "'Activo'", new Rol(1)));
//        newPer.setPri_nombre(persona.getPri_nombre());
//        newPer.setSeg_nombre(persona.getSeg_nombre());
//        newPer.setApe_paterno(persona.getApe_paterno());
//        newPer.setApe_materno(persona.getApe_materno());
//        newPer.setTipoDoc(persona.getTipoDoc());
//        newPer.setNum_Doc(persona.getNum_Doc());
//        newPer.setCorreo(persona.getCorreo());
//        
//        newUs.setLogin(us.getLogin());
//        newUs.setClave(us.getClave());
//        newUs.setEstado("Activo");
//        newUs.setRol(new Rol(1));
//        newPer.setUs(newUs);
        
        

        if (validarDocumento(newPer.getTipoDoc().getNombre_TD(), newPer.getNum_Doc())) {

            //Se registro correctamente
            if (usService.Registro(newPer) == 1) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Registro de Usuario.xhtml");
                return 1;
            } else { //Ocurrio un erro
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al registrar."));
                return 2;
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Numero de Documento no valido"));
            return 0; //Documento no valido
        }

    }
    
    public void redireccionar() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
    }

}

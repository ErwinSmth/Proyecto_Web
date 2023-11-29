
package model;

public class Opciones {

    private int id;
    private String documento;
    private String estado;
    private String descripcion;  
    private String grupo;
    

    public Opciones() {

    }

    public Opciones(int id, String documento, String estado, String descripcion, String grupo) {
        this.id = id;
        this.documento = documento;
        this.estado = estado;
        this.descripcion = descripcion;
        this.grupo = grupo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    

}

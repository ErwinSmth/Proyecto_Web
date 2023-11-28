/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Opciones;
import java.util.List;

public interface IOpcionesDAO {

    public String agregarOpciones(Opciones op);

    public String actualizarOpciones(Opciones op);

    public String eliminarOpciones(Opciones op);

    public List<Opciones> getOpciones();

    public Opciones getOpcionById(int id);

}

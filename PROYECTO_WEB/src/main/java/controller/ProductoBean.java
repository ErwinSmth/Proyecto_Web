/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProductoDAO;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import model.Producto;

@ManagedBean
@ViewScoped
public class ProductoBean {

    private List<Producto> listaDeProductos;
    private ProductoDAO productoDAO;

    @PostConstruct
    public void init() {
        productoDAO = new ProductoDAO();
        listaDeProductos = productoDAO.obtenerTodosLosProductos();
    }

    // Getter y Setter para listaDeProductos
    public List<Producto> getListaDeProductos() {
        return listaDeProductos;
    }

    public void setListaDeProductos(List<Producto> listaDeProductos) {
        this.listaDeProductos = listaDeProductos;
    }
}

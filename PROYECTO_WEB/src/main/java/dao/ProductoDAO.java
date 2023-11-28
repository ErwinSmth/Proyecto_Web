/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author LAB-USR-CHIM-A0208
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import model.Opciones;
import model.Producto;

public class ProductoDAO {

    private Connection conexion;

    public ProductoDAO() {
        // Código para establecer la conexión a la base de datos (puede variar según tu base de datos)
        // Ejemplo con MySQL:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/tramite_documentario";
            String usuario = "Admin";
            String contraseña = "Web";
            conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todos los productos de la base de datos
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> listaDeProductos = new ArrayList<>();
        String consulta = "SELECT * FROM producto"; // Consulta SQL para obtener los productos

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId_per(rs.getInt("id_per"));
                producto.setFS_na(rs.getString("FS_na"));
                producto.setFS_na(rs.getString("LS_na"));
                listaDeProductos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaDeProductos;
    }

    // Otros métodos como agregarProducto, actualizarProducto, eliminarProducto, etc.
    public static void main(String[] args) {
        
        ProductoDAO pro = new ProductoDAO();
        
        List<Producto> lista = pro.obtenerTodosLosProductos();
        
        for (Producto i : lista) {
            
            System.out.println("ID" +i.getId_per());
            System.out.println("Nombre "+i.getFS_na());
            System.out.println("--------------------");
            
        }
        
        
    }
    
}

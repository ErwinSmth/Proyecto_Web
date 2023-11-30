/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Tipo_Tramite;
import util.DataSource;

/**
 *
 * @author DAVID
 */
public class Tipo_TramiteDAOImpl implements IDAO<Tipo_Tramite>{

    Connection conn = DataSource.obtenerConexion();
    
    //1 para exito
    //2 para error
    @Override
    public int add(Tipo_Tramite objeto) {
       
        String query = "Insert into tipo_tramite (Nom_TT, Descripcion) values (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            
            ps.setString(1, objeto.getNom_TT());
            ps.setString(2, objeto.getDescripcion());
            
            int exito = ps.executeUpdate();
            
            if (exito > 0) {
                return 1;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }

    @Override
    public int update(Tipo_Tramite objeto) {
        
        String query = "Update tipo_tramite set Descripcion = ? where Nom_TT = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            
            ps.setString(1, objeto.getDescripcion());
            ps.setString(2, objeto.getNom_TT());
            
            int exito = ps.executeUpdate();
            
            if (exito > 0) {
                return 1;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }

    @Override
    public int delete(Tipo_Tramite objeto) {
        
        String query = "Delete from tipo_tramite where Nom_TT = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            
            ps.setString(1, objeto.getNom_TT());
            
            int exito = ps.executeUpdate();
            
            if (exito > 0) {
                return 1;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }

    @Override
    public List<Tipo_Tramite> getListado() {
        
        List<Tipo_Tramite> listado = new ArrayList<>();
        String query = "Select * from tipo_tramite";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                
                Tipo_Tramite tipoTra = new Tipo_Tramite();
                tipoTra.setNom_TT(rs.getString("Nom_TT"));
                tipoTra.setDescripcion(rs.getString("Descripcion"));
                listado.add(tipoTra);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listado;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author DAVID
 */
public class ArchivoHelper {
    
    public String subirArchivo(InputStream inputStream, String nombreArchivo) throws FileNotFoundException, IOException{
        
        //Ruta del archivo
        String ruta = "C:/xampp/htdocs/docs/" + nombreArchivo;
        OutputStream outputStream = new FileOutputStream(new File(ruta));
        
        int read = 0;
        byte[] bytes = new byte[1024];
        
        while ((read = inputStream.read(bytes)) != -1) {            
            outputStream.write(bytes, 0, read);
        }
        
        outputStream.close();
        inputStream.close();
        
        return ruta;
    }
    
}

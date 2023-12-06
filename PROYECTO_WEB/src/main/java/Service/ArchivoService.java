/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author DAVID
 */
public class ArchivoService {
    
    public void guardarArchivo(InputStream archivo, String rutaArchivo) throws IOException  {
        
        try (FileOutputStream outputStream = new FileOutputStream(rutaArchivo)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = archivo.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
        }
    }
    
}

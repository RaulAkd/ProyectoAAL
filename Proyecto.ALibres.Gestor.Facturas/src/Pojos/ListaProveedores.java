/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author por-tosh
 */
public class ListaProveedores {
    private ArrayList<Proveedor> listaProveedor;// = new ArrayList<Proveedor>();

    public ListaProveedores() {
        this.listaProveedor = new ArrayList<Proveedor>();
    }
    
    public void cargarDatos(){
        try {
            FileReader fileEntrada = new FileReader("src\\ArchivosLecturaAuxiliar\\Proveedores.txt");
            BufferedReader bufferEntrada = new BufferedReader(fileEntrada);
            String cadena, nombreProveedor, rucProveedor, direcProveedor, ciudadProveedor;
            
            //Construccion de expresion regular
            //System.out.println("entra2");
            while((cadena = bufferEntrada.readLine())!=null) {
                nombreProveedor = cadena.split(",")[0].split(":")[1];
                rucProveedor = cadena.split(",")[1].split(":")[1];
                direcProveedor = cadena.split(",")[2].split(":")[1];
                ciudadProveedor = cadena.split(",")[3].split(":")[1];
                Proveedor provAux = new Proveedor(nombreProveedor, rucProveedor, direcProveedor, ciudadProveedor);
                this.listaProveedor.add(provAux);
            }
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("no hay archivo");
        }
 //       }
        catch (IOException ex) {
            //Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarDatos(){
        FileWriter fichero;
        PrintWriter pw;
        
    }
}


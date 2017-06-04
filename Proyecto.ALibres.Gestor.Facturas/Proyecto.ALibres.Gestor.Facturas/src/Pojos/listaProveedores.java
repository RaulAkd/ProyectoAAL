/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author por-tosh
 */
public class listaProveedores {
    private ArrayList<Proveedor> listaProveedores;// = new ArrayList<Proveedor>();

    public listaProveedores() {
        this.listaProveedores = new ArrayList<Proveedor>();
    }
    
    public void cargarProveedores(){
        try {
            FileReader fileEntrada = new FileReader("src\\ArchivosLecturaAuxiliar\\ClasificacionGastos.txt");
            BufferedReader bufferEntrada = new BufferedReader(fileEntrada);
            
            
            
        }catch (FileNotFoundException ex) {
            //Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("no hay archivo");
        }
    }
    
}

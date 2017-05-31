/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import InterfazGrafica.IPrincipal;
import InterfazGrafica.PantallaPresentacion;
import LectorXML.ConvertirXML;
import LectorXML.LectorXml;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author por-tosh
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        //String direccion = "C://Users//por-tosh//Desktop//EPN//ALibres2017A//FacturasXML//063370785.xml";

        //ConvertirXML xmlNuevo = new ConvertirXML(direccion);
        
        //String direccionB = "C://Users//por-tosh//Desktop//EPN//ALibres2017A//FacturasXML//Factura Patricio.xml";
        
        //String direccionB = "C://Users//por-tosh//Desktop//EPN//ALibres2017A//FacturasXML//prueba3.xml";
        //LectorXml leerXml = new LectorXml(direccionB);
        //leerXml.leerFacturaXml();
        PantallaPresentacion p1 = new PantallaPresentacion();
        p1.setVisible(true);
        //IPrincipal p = new IPrincipal();
        //p.setVisible(true);
    }
    
}

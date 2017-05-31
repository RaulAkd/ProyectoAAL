/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LectorXML;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static jdk.nashorn.internal.parser.TokenType.EOF;

/**
 *
 * @author por-tosh
 */
public class ConvertirXML {
    private String direccion;
    FileReader fileEntrada;
    BufferedReader bufferEntrada;
    
    FileWriter fichero;
    PrintWriter pw;

    public ConvertirXML(String direccion) throws FileNotFoundException, IOException {
        this.direccion = direccion;
        String cadena;
        this.fileEntrada = new FileReader(this.direccion);
        bufferEntrada = new BufferedReader(fileEntrada);
        
        fichero = new FileWriter("C:\\Users\\por-tosh\\Desktop\\EPN\\ALibres2017A\\FacturasXML\\factura.xml");
        pw = new PrintWriter(fichero);
        this.direccion="C:\\Users\\por-tosh\\Desktop\\EPN\\ALibres2017A\\FacturasXML\\factura.xml";
        boolean cdata = false;
        boolean corchetesFinal = true;
        
        while((cadena = bufferEntrada.readLine())!=null && corchetesFinal) {
            Pattern pat = Pattern.compile(".*CDATA.*");
            Matcher mat = pat.matcher(cadena);
            Pattern patB = Pattern.compile(".*]].*");
            Matcher matB = patB.matcher(cadena);
            
            if (matB.matches()){
                int posicionB = cadena.indexOf("]]");
                //System.out.println(cadena.substring(0, posicionB ));
                pw.println(cadena.substring(0, posicionB ));
                corchetesFinal = false;
                }
            
            if(cdata && corchetesFinal){
                pw.println(cadena);
                //System.out.println(cadena);
            }
            if (mat.matches()) {
         //System.out.println("SI");
                int posicion = cadena.indexOf("CDATA");
                //System.out.println(cadena.substring(posicion + 6));
                pw.println(cadena.substring(posicion + 6));
                cdata = true;
            }
            else{
         //System.out.println("NO");
            }
            
        }
        //pw.print("</factura>");
        pw.close();
        bufferEntrada.close();
        fichero.close();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}

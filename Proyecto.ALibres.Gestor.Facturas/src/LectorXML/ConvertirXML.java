/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LectorXML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;
//import static jdk.nashorn.internal.parser.TokenType.EOF;

/**
 *
 * @author por-tosh
 */
public class ConvertirXML {
    private String direccion;
    FileReader fileEntrada;
    BufferedReader bufferEntrada;
    //sfsdf
    FileWriter fichero;
    PrintWriter pw;
     private static final Logger log = Logger.getLogger(ConvertirXML.class.getName());

    public ConvertirXML(String direccion) throws FileNotFoundException, IOException {
        this.direccion = direccion;
        String cadena;
        String nuevaDireccion;
        String direccionCarpeta;
        this.fileEntrada = new FileReader(this.direccion);
        bufferEntrada = new BufferedReader(fileEntrada);
        nuevaDireccion=direccionAbsoluta(this.direccion);
        nuevaDireccion+="\\\\ArchivosGestorFacturas";
   
        File carpeta = new File(nuevaDireccion);
        carpeta.mkdirs();
        
        nuevaDireccion+="\\\\factura.xml";
        nuevaDireccion=nuevaDireccion.replace("/", "\\");
        //prueba
        //FileWriter archivoPrueba = new FileWriter("C:\\Users\\por-tosh\\Desktop\\pruebaXml.txt");
        //PrintWriter escribirPrueba = new PrintWriter(archivoPrueba);
        //BufferedReader numeroLineas = new BufferedReader(fileEntrada);
        
        fichero = new FileWriter(nuevaDireccion);
        pw = new PrintWriter(fichero);
        this.direccion=nuevaDireccion;
        boolean cdata = false;
        boolean corchetesFinal = true;
        /*
        int lNumeroLineas = 0;
        String nCadena;
        while ((nCadena = numeroLineas.readLine())!=null) {
            lNumeroLineas++;
        }
        numeroLineas.close();
        System.out.println("numero de lineas = "+ lNumeroLineas);
        */
        //bufferEntrada = new BufferedReader(fileEntrada);
        log.log(Level.FINE, "Antes de while");
        while((cadena = bufferEntrada.readLine())!=null && corchetesFinal) {
            //System.out.println("..LINEA "+contadorlinea + cadena);
            //contadorlinea++;
            Pattern pat = Pattern.compile(".*CDATA.*");
            Matcher mat = pat.matcher(cadena);
            Pattern patB = Pattern.compile(".*]].*");
            Matcher matB = patB.matcher(cadena);
            log.log(Level.FINE, "Pasa expresion regular");
            if(matB.matches() && mat.matches()){
                int posicion = cadena.indexOf("CDATA");
                int posicionB = cadena.indexOf("]]");
                //escribirPrueba.println(cadena.substring(posicion + 6,posicionB));
                pw.println(cadena.substring(posicion + 6, posicionB));
            }
            else{
            
                if (matB.matches()){
                    int posicionB = cadena.indexOf("]]");
                    //System.out.println("SI ENCONTRO ]]");
                    //System.out.println(cadena.substring(0, posicionB ));
                    pw.println(cadena.substring(0, posicionB ));
                    //escribirPrueba.println(cadena.substring(0, posicionB ));
                    corchetesFinal = false;
                    }

                if(cdata && corchetesFinal){
                    pw.println(cadena);
                    //System.out.println(cadena);
                }

                if (mat.matches()) {
                    int posicion = cadena.indexOf("CDATA");
                    //System.out.println("SI ENCONTRO CDATA");
                    //System.out.println("........." + cadena.substring(posicion + 6));
                    //escribirPrueba.println(cadena.substring(posicion + 6));
                    pw.println(cadena.substring(posicion + 6));
                    cdata = true;
                }
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
    
    public String direccionAbsoluta(String dir)
    {
        String auxiliar="";
        int i=0, contador=0;
                
        do
        {
            if(dir.substring(i, i+1).equals("\\")||dir.substring(i, i+1).equals("/"))
            {
                contador++;
            }
            auxiliar+=dir.substring(i, i+1);
            i++;
        }
        while(i<dir.length()-1&&contador<=4);
        return auxiliar;
    }
}

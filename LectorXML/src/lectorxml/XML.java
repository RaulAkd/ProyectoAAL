/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorxml;

//import accesoDatos.Conexion;
//import bean.*;
//import gui.IngresarXml;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.StringTokenizer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author PorTosh
 */
public class XML {
    
    //public ProveedorBean objProveedor = new ProveedorBean();
    //public FacturaBean objFactura = new FacturaBean();

    private String Proveedor = "", Comprador = "", Facturas = "", Detalle = "", Producto = "", Tempresa = "";
    //private Conexion con;
    private String ubicacionArchivo = "";

    XML(String archivo) {
        //con = new Conexion();
        //con.Conexion();

        ubicacionArchivo = archivo;
        try {
            leerConfiguracion();
        } catch (NullPointerException e) {
            System.err.println("fallo");
        }
    }

    public void leerConfiguracion() {
        System.out.println("Entro");
        Proveedor = "INSERT INTO proveedor VALUES(";
        Comprador = "INSERT INTO comprador VALUES(";
        Facturas = "INSERT INTO facturas VALUES (";

        try {
            int temp = 0;
            File fXmlFile = new File(ubicacionArchivo);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            try {

                NodeList nList = doc.getElementsByTagName("infoTributaria");
                Node nNode = nList.item(temp);
                Element eElement = (Element) nNode;
                NodeList nList1 = doc.getElementsByTagName("infoFactura");
                Node nNode1 = nList1.item(temp);
                Element eElement1 = (Element) nNode1;
                NodeList nList2 = doc.getElementsByTagName("detalles");
                Node nNode2 = nList2.item(temp);
                Element eElement2 = (Element) nNode2;
                NodeList nAuxiliar = doc.getElementsByTagName("detalle");
                System.out.println("2");
                System.out.println("Razon Social  " + Tempresa);

                /*PROVEEDOR*/
                /**
                 * ******************************************************************************************************************
                 */
                //RUC
                //objProveedor.setStrRucProv(eElement.getElementsByTagName("ruc").item(0).getTextContent());
                System.out.println("Proveedor");
                System.out.println("Ruc : " + eElement.getElementsByTagName("ruc").item(0).getTextContent());
                
                //Proveedor += "'" + eElement.getElementsByTagName("ruc").item(0).getTextContent() + "'";
                //Razón Social
                
                //objProveedor.setStrNombreProv(eElement.getElementsByTagName("razonSocial").item(0).getTextContent());
                System.out.println("Razon Social : " + eElement.getElementsByTagName("razonSocial").item(0).getTextContent());
                //Proveedor += ",'" + eElement.getElementsByTagName("razonSocial").item(0).getTextContent() + "','','')";
                
                //Direccion Matriz
                //objProveedor.setStrDireccionProv(eElement.getElementsByTagName("dirMatriz").item(0).getTextContent());
                System.out.println("Direccion Matriz : " + eElement.getElementsByTagName("dirMatriz").item(0).getTextContent());
                /**
                 * ******************************************************************************************************************
                 */
                 /*COMPRADOR*/
                /**
                 * ******************************************************************************************************************
                 */
                //Cedula
                System.out.println("Identificador Comprador :" +  eElement1.getElementsByTagName("identificacionComprador").item(0).getTextContent());
                //Nombre
                //Comprador += ",'" + eElement1.getElementsByTagName("razonSocialComprador").item(0).getTextContent() + "'";
                System.out.println("Identificador Comprador :" +  eElement1.getElementsByTagName("razonSocialComprador").item(0).getTextContent());
                //Direccion
                Comprador += ",'" + eElement1.getElementsByTagName("dirEstablecimiento").item(0).getTextContent() + "','','','')";
                System.out.println("Identificador Comprador :" +  eElement1.getElementsByTagName("dirEstablecimiento").item(0).getTextContent());
                //**

                
                }

             catch (NullPointerException e) {
                //leerConfiguracion1();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void leerConfiguracion1() {
        System.out.println("entrooo");
        Proveedor = "INSERT INTO proveedor VALUES (";
        Comprador = "INSERT INTO comprador VALUES (";
        Facturas = "INSERT INTO facturas VALUES (";
        try {
            int temp = 0;
            File fXmlFile = new File(ubicacionArchivo);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            try {
                NodeList nListp = doc.getElementsByTagName("autorizacion");
                Node nNodep = nListp.item(temp);
                Element eElementp = (Element) nNodep;
                System.out.println(eElementp.getElementsByTagName("comprobante").item(0).getTextContent());
                File archivo = new File(ubicacionArchivo);
                BufferedWriter bw;
                if (archivo.exists()) {
                    bw = new BufferedWriter(new FileWriter(archivo));
                    bw.write(eElementp.getElementsByTagName("comprobante").item(0).getTextContent());
                } else {
                    bw = new BufferedWriter(new FileWriter(archivo));
                    bw.write(eElementp.getElementsByTagName("comprobante").item(0).getTextContent());
                }

                bw.close();
                leerConfiguracion();

            } catch (NullPointerException e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String intercalarFecha(String fecha) {
        String fe[] = new String[3];
        int i = 0;
        StringTokenizer tokens = new StringTokenizer(fecha);
        while (tokens.hasMoreTokens()) {
            fe[i++] = tokens.nextToken("/");
        }
        fecha = "";
        for (int l = 2; l >= 0; l--) {
            fecha += fe[l];
            if (l > 0) {
                fecha += "-";
            }
        }
        return fecha;
    }
}

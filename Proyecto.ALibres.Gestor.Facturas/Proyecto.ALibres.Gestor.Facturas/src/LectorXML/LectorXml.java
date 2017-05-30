/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LectorXML;

import Pojos.Cliente;
import Pojos.Factura;
import Pojos.Producto;
import Pojos.Proveedor;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author por-tosh
 */
public class LectorXml {
    private Proveedor proveedor = new Proveedor();
    private Cliente cliente = new Cliente();
    private Factura factura = new Factura();
    private String direccionArchivo;

    public LectorXml(String direccionArchivo) {
        this.direccionArchivo = direccionArchivo;
    }
    
    public void leerFacturaXml(){
        try {
            int temp = 0;
            File fXmlFile = new File(this.direccionArchivo);
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
                /*PROVEEDOR*/
                /**
                 * ******************************************************************************************************************
                 */
                //RUC
                //System.out.println("si llega    ruc "+eElement.getElementsByTagName("ruc").item(0).getTextContent());
                this.proveedor.setRuc(eElement.getElementsByTagName("ruc").item(0).getTextContent());
                //objProveedor.setStrRucProv(eElement.getElementsByTagName("ruc").item(0).getTextContent());
                //Proveedor += "'" + eElement.getElementsByTagName("ruc").item(0).getTextContent() + "'";
                //Razón Social
                this.proveedor.setNombre(eElement.getElementsByTagName("razonSocial").item(0).getTextContent());
                //Proveedor += ",'" + eElement.getElementsByTagName("razonSocial").item(0).getTextContent() + "','','')";
                //Direccion Matriz
                this.proveedor.setDireccion(eElement.getElementsByTagName("dirMatriz").item(0).getTextContent());
                /*COMPRADOR*/
                /**
                 * ******************************************************************************************************************
                 */
                //Cedula
                //Comprador += "'" + eElement1.getElementsByTagName("identificacionComprador").item(0).getTextContent() + "'";
                
                this.cliente.setNombres(eElement1.getElementsByTagName("razonSocialComprador").item(0).getTextContent());
                this.cliente.setRucCi(eElement1.getElementsByTagName("identificacionComprador").item(0).getTextContent());
                
                this.factura.setCodigo(eElement.getElementsByTagName("secuencial").item(0).getTextContent());
                this.factura.setFecha(eElement1.getElementsByTagName("fechaEmision").item(0).getTextContent());
                this.factura.setTotalSinIva(Double.parseDouble(eElement1.getElementsByTagName("totalSinImpuestos").item(0).getTextContent()));
                this.factura.setTotalConIva(Double.parseDouble(eElement1.getElementsByTagName("importeTotal").item(0).getTextContent()));
                //Nombre
                //Comprador += ",'" + eElement1.getElementsByTagName("razonSocialComprador").item(0).getTextContent() + "'";
                //Direccion
                //Comprador += ",'" + eElement1.getElementsByTagName("dirEstablecimiento").item(0).getTextContent() + "','','','')";
                for (temp = 0; temp < nAuxiliar.getLength(); temp++) {
                    //Detalle = "INSERT INTO detalle_producto VALUES ("
                    /*DETALLE_PRODUCTO*/
                    /**
                     * ******************************************************************************************************************
                     */
                    //Codigo Producto
                    Producto producto = new Producto();
                    //Detalle += "'" + eElement2.getElementsByTagName("codigoPrincipal").item(temp).getTextContent()+ "'";
                    producto.setCodigo(eElement2.getElementsByTagName("codigoPrincipal").item(temp).getTextContent());
                    //Descripcion Producto
                    //Detalle += ",'" + eElement2.getElementsByTagName("descripcion").item(temp).getTextContent()+ "'";
                    producto.setNombre(eElement2.getElementsByTagName("descripcion").item(temp).getTextContent());
                    //Cantidad de Productos
                    //Detalle += ",'" + eElement2.getElementsByTagName("cantidad").item(temp).getTextContent()+ "'";
                    producto.setCantidad(Double.parseDouble(eElement2.getElementsByTagName("cantidad").item(temp).getTextContent()));
                    //Precio Unitario Producto
                    //Detalle += ",'" + eElement2.getElementsByTagName("precioUnitario").item(temp).getTextContent()+ "'";
                    producto.setPrecioUnitario(Double.parseDouble(eElement2.getElementsByTagName("precioUnitario").item(temp).getTextContent()));
                    //Valor Total de Productos
                    //Detalle += ",'" + eElement2.getElementsByTagName("baseImponible").item(temp).getTextContent()+ "'";
                    producto.setValorTotal(Double.parseDouble(eElement2.getElementsByTagName("baseImponible").item(temp).getTextContent()));
                    //Codigo de Factura
                    //Detalle += ",'" + eElement.getElementsByTagName("secuencial").item(0).getTextContent() + "')";
                    this.factura.setProducto(producto);
                }
                
            } catch (NullPointerException e) {
                //leerConfiguracion1();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println(this.proveedor);
        System.out.println(this.cliente);
        System.err.println(this.factura);
        System.err.println(this.factura.listaToString());
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getDireccionArchivo() {
        return direccionArchivo;
    }

    public void setDireccionArchivo(String direccionArchivo) {
        this.direccionArchivo = direccionArchivo;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LectorXML;

import Operaciones.Operaciones;
import Pojos.Cliente;
import Pojos.Factura;
import Pojos.FacturaNegocio;
import Pojos.Producto;
import Pojos.Proveedor;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author por-tosh
 */
public class LectorXml {
    private Proveedor proveedor = new Proveedor();
    private Cliente cliente = new Cliente();
    private Factura factura = new Factura();
    private FacturaNegocio facturaNegocio = new FacturaNegocio();
    private String direccionArchivo;
    private static final Logger log = Logger.getLogger(LectorXml.class.getName());

    public LectorXml(String direccionArchivo) {
        this.direccionArchivo = direccionArchivo;
    }
    
    public void leerFacturaXml(int tipoFacturaIngresar){
        //tipoFacturaIngresar = 1       tipo personal
        //tipoFacturaIngresar = 2       tipo negocio
        log.log(Level.FINE, "Si llega aqui");
        
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
                //RUC
                this.proveedor.setRuc(eElement.getElementsByTagName("ruc").item(0).getTextContent());
                //Razón Social
                this.proveedor.setNombre(eElement.getElementsByTagName("razonSocial").item(0).getTextContent());
                //Direccion Matriz
                this.proveedor.setDireccion(eElement.getElementsByTagName("dirMatriz").item(0).getTextContent());
                /*COMPRADOR*/
                this.cliente.setNombres(eElement1.getElementsByTagName("razonSocialComprador").item(0).getTextContent());
                this.cliente.setRucCi(eElement1.getElementsByTagName("identificacionComprador").item(0).getTextContent());
                
                if(tipoFacturaIngresar == 1){
                    this.factura.setCodigo(eElement.getElementsByTagName("secuencial").item(0).getTextContent());
                    this.factura.setFecha(eElement1.getElementsByTagName("fechaEmision").item(0).getTextContent());
                    this.factura.setTotalSinIva(Double.parseDouble(eElement1.getElementsByTagName("totalSinImpuestos").item(0).getTextContent()));
                    this.factura.setTotalConIva(Double.parseDouble(eElement1.getElementsByTagName("importeTotal").item(0).getTextContent()));
                }
                if(tipoFacturaIngresar == 2){
                    this.facturaNegocio.setCodigo(eElement.getElementsByTagName("secuencial").item(0).getTextContent());
                    this.facturaNegocio.setFecha(eElement1.getElementsByTagName("fechaEmision").item(0).getTextContent());
                    this.facturaNegocio.setTotalSinIva(Double.parseDouble(eElement1.getElementsByTagName("totalSinImpuestos").item(0).getTextContent()));
                    this.facturaNegocio.setTotalConIva(Double.parseDouble(eElement1.getElementsByTagName("importeTotal").item(0).getTextContent()));
                }
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
                    producto.setCodigo(eElement2.getElementsByTagName("codigoPrincipal").item(temp).getTextContent());
                    //Descripcion Producto
                    producto.setNombre(eElement2.getElementsByTagName("descripcion").item(temp).getTextContent());
                    //Cantidad de Productos
                    producto.setCantidad(Double.parseDouble(eElement2.getElementsByTagName("cantidad").item(temp).getTextContent()));
                    //Precio Unitario Producto
                    producto.setPrecioUnitario(Double.parseDouble(eElement2.getElementsByTagName("precioUnitario").item(temp).getTextContent()));
                    //Valor Total de Productos
                    producto.setValorTotal(Double.parseDouble(eElement2.getElementsByTagName("baseImponible").item(temp).getTextContent()));
                    //Codigo de Factura
                    if(tipoFacturaIngresar == 1){
                        this.factura.setProducto(producto);
                    }
                    if(tipoFacturaIngresar == 2){
                        this.facturaNegocio.setProducto(producto);
                    }
                }
                
            } catch (NullPointerException e) {
                //leerConfiguracion1();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(tipoFacturaIngresar == 1){
            this.factura.setRucCliente(this.cliente.getRucCi());
            this.factura.setRucProveedor(this.proveedor.getRuc());
            //
            this.factura.calcularIva();
            //
            //this.factura.clasificar();
        }
        if(tipoFacturaIngresar == 2){
            this.facturaNegocio.setRucCliente(this.cliente.getRucCi());
            this.facturaNegocio.setRucProveedor(this.proveedor.getRuc());
            //
            this.facturaNegocio.calcularIva();
            //
            //this.facturaNegocio.clasificar();
        }
        //JOptionPane.showMessageDialog(null, this.facturaNegocio.getListaProductos().size());
        System.out.println(this.proveedor);
        System.out.println(this.cliente);
        System.err.println(this.factura);
        System.err.println(this.factura.listaToString());
        System.err.println(this.facturaNegocio.listaToString());
        
    }

    public FacturaNegocio getFacturaNegocio() {
        return facturaNegocio;
    }

    public void setFacturaNegocio(FacturaNegocio facturaNegocio) {
        this.facturaNegocio = facturaNegocio;
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

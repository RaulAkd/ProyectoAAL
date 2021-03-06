/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

//import Objetos.Persona;
import Pojos.Cliente;
import Pojos.Factura;
import Pojos.FacturaNegocio;
import Pojos.Gasto;
import Pojos.Producto;
import Pojos.Proveedor;
import java.awt.Choice;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Operaciones extends Conexion{
    /**
     * Constructor for objects of class Operaciones
     */
    public Operaciones(String dir)
    {
        super(dir);
        // initialise instance variables
    }
    
    public boolean existeFactura(String codigoFactura, String rucCiCliente, String RucProveedor){
        boolean retorno = true;
        ResultSet resultado = null;
        String sql = "SELECT * FROM FACTURA_DATOS";
        try{
            resultado = consultar(sql);
            if((resultado != null)&&(codigoFactura!=null)){
                //int numeroColumna = resultado.getMetaData().getColumnCount();
                //JOptionPane.showMessageDialog(null, resultado.getObject(1).toString());
                //JOptionPane.showMessageDialog(null, resultado.getObject(2).toString());
                //JOptionPane.showMessageDialog(null, codigoFactura);
                while(resultado.next()){
                    if((resultado.getObject(1).toString().compareTo(rucCiCliente) == 0)
                            &&(resultado.getObject(2).toString().compareTo(codigoFactura) == 0)
                            &&(resultado.getObject(3).toString().compareTo(RucProveedor) == 0)){
                        retorno = false;
                    }
                }
            }
            resultado.close();
        }
        catch(SQLException e){
            
        }
        return retorno;
    }
    
    public boolean existeFacturaNegocio(String codigoFactura, String rucCiCliente, String RucProveedor){
        boolean retorno = true;
        ResultSet resultado = null;
        String sql = "SELECT * FROM FACTURA_NEGOCIO_DATOS";
        try{
            resultado = consultar(sql);
            if((resultado != null)&&(codigoFactura!=null)){
                
                while(resultado.next()){
                    if((resultado.getObject(1).toString().compareTo(rucCiCliente) == 0)
                            &&(resultado.getObject(2).toString().compareTo(codigoFactura) == 0)
                            &&(resultado.getObject(3).toString().compareTo(RucProveedor) == 0)){
                        retorno = false;
                    }
                }
            }
            resultado.close();
        }
        catch(SQLException e){
            
        }
        return retorno;
    }
    
    public boolean insertar(String sql){
        //JOptionPane.showMessageDialog(null, sql);
        boolean valor = true;
        conectar();
        try {
            consulta.executeUpdate(sql);
        } catch (SQLException e) {
                valor = false;
                //JOptionPane.showMessageDialog(null, "Factura ya registrada");
            }      
        finally{  
            try{    
                 consulta.close();  
                 conexion.close();  
             }catch (Exception e){                 
                 e.printStackTrace();  
             }  
        }
        return valor;
    }
    public ResultSet consultar(String sql){
        conectar();
        ResultSet resultado = null;
        try {
            resultado = consulta.executeQuery(sql);

        } catch (SQLException e) {
                System.out.println("Mensaje:"+e.getMessage());
                System.out.println("Estado:"+e.getSQLState());
                System.out.println("Codigo del error:"+e.getErrorCode());
                //JOptionPane.showMessageDialog(null, ""+e.getMessage());
            }
        return resultado;
    }
    
    public void guardarFactura(Factura factura){
        //JOptionPane.showMessageDialog(null, "llego a metodo guardar factura....");
        ResultSet resultadoIdcliente = consultar("SELECT ID_CLIENTE FROM CLIENTE WHERE RUC_CI_CLIENTE = '"+factura.getRucCliente()+"'");
        ResultSet resultadoIdProveedor = consultar("SELECT ID_PROVEEDOR FROM PROVEEDOR WHERE RUC_PROVEEDOR = '"+factura.getRucProveedor()+"'");
        String idCliente = "", idProveedor = "";
        try {
            while(resultadoIdcliente.next()){
                idCliente = resultadoIdcliente.getObject(1).toString();
            }
            while(resultadoIdProveedor.next()){
                idProveedor = resultadoIdProveedor.getObject(1).toString();
            }
            resultadoIdcliente.close();
            resultadoIdProveedor.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        //JOptionPane.showMessageDialog(null, factura.getFecha());
        //JOptionPane.showMessageDialog(null, "id cliente...." + idCliente + "   id proveedor...." + idProveedor);
        insertar("INSERT INTO FACTURA (ID_PROVEEDOR, ID_CLIENTE, CODIGO_FACTURA, FECHA, IVA, "
                + "TOTAL_SIN_IVA, TOTAL_CON_IVA, TOTAL_ALIMENTACION_CLIENTE, TOTAL_VESTIMENTA_CLIENTE, "
                + "TOTAL_VIVIENDA_CLIENTE, TOTAL_SALUD_CLIENTE, TOTAL_EDUCACION_CLIENTE, TOTAL_OTROS_CLIENTE) VALUES('" + 
                    idProveedor + "','" +
                    idCliente + "','" +
                    factura.getCodigo() + "','" + 
                    factura.getFecha() + "','" + 
                    factura.getIva() + "','" + 
                    factura.getTotalSinIva() + "','" + 
                    factura.getTotalConIva() + "','" + 
                    factura.getListaGastos().get(1).getTotalSinIva() + "','" + 
                    factura.getListaGastos().get(0).getTotalSinIva() + "','" + 
                    factura.getListaGastos().get(4).getTotalSinIva() + "','" + 
                    factura.getListaGastos().get(2).getTotalSinIva() + "','" + 
                    factura.getListaGastos().get(3).getTotalSinIva() + "','" + 
                    factura.getListaGastos().get(5).getTotalSinIva() +"')");
        //JOptionPane.showMessageDialog(null, "factura guardada....");
        sumarGastos(idCliente, factura.getListaGastos(), factura.getFecha());
    }
    
    public void guardarFacturaNegocio(FacturaNegocio factura){
        ResultSet resultadoIdcliente = consultar("SELECT ID_CLIENTE FROM CLIENTE WHERE RUC_CI_CLIENTE = '"+factura.getRucCliente()+"'");
        ResultSet resultadoIdProveedor = consultar("SELECT ID_PROVEEDOR FROM PROVEEDOR WHERE RUC_PROVEEDOR = '"+factura.getRucProveedor()+"'");
        String idCliente = "", idProveedor = "";
        try {
            while(resultadoIdcliente.next()){
                idCliente = resultadoIdcliente.getObject(1).toString();
            }
            while(resultadoIdProveedor.next()){
                idProveedor = resultadoIdProveedor.getObject(1).toString();
            }
            resultadoIdcliente.close();
            resultadoIdProveedor.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        insertar("INSERT INTO FACTURA_NEGOCIO (ID_PROVEEDOR, ID_CLIENTE, CODIGO_FACTURA, FECHA, IVA, "
                + "TOTAL_SIN_IVA, TOTAL_CON_IVA) VALUES('" + 
                    idProveedor + "','" +
                    idCliente + "','" +
                    factura.getCodigo() + "','" + 
                    factura.getFecha() + "','" + 
                    factura.getIva() + "','" + 
                    factura.getTotalSinIva() + "','" + 
                    factura.getTotalConIva() +"')");
        //JOptionPane.showMessageDialog(null, "factura guardada....");
        guardarGastosFacturaNegocio(factura.getListaGastos(), factura.getCodigo(), factura.getFecha(), idCliente);
    }
    
    public void guardarGastosFacturaNegocio(ArrayList<Gasto> gastosFNegocio, String codigoFactura, String fecha, String idCliente){
        ResultSet resultadoIdFactura = consultar("SELECT ID_FACTURA2 FROM FACTURA_NEGOCIO WHERE CODIGO_FACTURA = '"+codigoFactura+"'");
        String idFactura = "";
        try {
            while(resultadoIdFactura.next()){
                idFactura = resultadoIdFactura.getObject(1).toString();
            }
            resultadoIdFactura.close();
            for(Gasto gasto : gastosFNegocio){
                insertar("INSERT INTO GASTOS_DE_NEGOCIO_FACTURA (ID_FACTURA2, NOMBRE_GASTO_EXTRA_FACTURA, TOTAL_GASTO_EXTRA_FACTURA) VALUES('" + 
                            idFactura + "','" +
                            gasto.getTipo() + "','" + 
                            gasto.getTotalSinIva() +"')");
                ResultSet resultadoExisteGasto = consultar("SELECT * FROM GASTOS_DE_NEGOCIO"
                        + " WHERE NOMBRE_GASTO_EXTRA = '" + gasto.getTipo() + "'");
                if(resultadoExisteGasto.next()){
                    //JOptionPane.showMessageDialog(null, "...GASTO YA SE ENCUENTRA REGISTRADO");
                    double sumaGasto =Double.parseDouble(resultadoExisteGasto.getObject(4).toString().replace(",", ".")) 
                            + gasto.getTotalSinIva();
                            //ResultSet resulActualizacion = null
                    
                    consultar("UPDATE GASTOS SET TOTAL_GASTO_EXTRA = '" + sumaGasto + "' WHERE ID_GASTO_EXTRA = '"
                            + resultadoExisteGasto.getObject(1).toString() + "'");
                    resultadoExisteGasto.close();
                }else{
                    //JOptionPane.showMessageDialog(null, fecha);
                    String nuevaFecha = fecha.substring(6);
                    //JOptionPane.showMessageDialog(null, fecha);
                    insertar("INSERT INTO GASTOS_DE_NEGOCIO (ID_CLIENTE, NOMBRE_GASTO_EXTRA, TOTAL_GASTO_EXTRA, "
                + "ANIO_GASTO_EXTRA) VALUES('"+idCliente+"', '" + gasto.getTipo() + "','" + 
                gasto.getTotalSinIva() + "','" + nuevaFecha + "')");
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarCliente(Cliente cliente, String fecha){
        //JOptionPane.showMessageDialog(null, "llego a metodo guardar cliente....");
        ResultSet resultado = null;
        try {
            resultado = consultar("SELECT ID_CLIENTE FROM CLIENTE WHERE\n" +
                                    "RUC_CI_CLIENTE = '"+ cliente.getRucCi()/* + 
                    "' AND NOMBRES_CLIENTE = '"+cliente.getNombres()*/+"'");
            //if(resultado != null){
            if(resultado.next()){
                JOptionPane.showMessageDialog(null, "...CLIENTE YA SE ENCUENTRA REGISTRADO");
            }
            else{
                //JOptionPane.showMessageDialog(null, "no existe cliente, se ingresara uno nuevo....");
                insertar("INSERT INTO CLIENTE (RUC_CI_CLIENTE, NOMBRES_CLIENTE) VALUES('"+cliente.getRucCi()
                        +"','"+cliente.getNombres()+"')");
                ResultSet resultadoIdcliente = consultar("SELECT ID_CLIENTE FROM CLIENTE WHERE RUC_CI_CLIENTE = '"+cliente.getRucCi()+/*"' AND NOMBRES_CLIENTE = '"+cliente.getNombres()+*/"'");
                String idCliente = "";
                try {
                    while(resultadoIdcliente.next()){
                        idCliente = resultadoIdcliente.getObject(1).toString();
                  //      JOptionPane.showMessageDialog(null, "id de cliente nuevo...." + idCliente);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                guardarGastos(idCliente, fecha);
            }
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarClienteFNegocios(Cliente cliente, String fecha){
        //JOptionPane.showMessageDialog(null, "llego a metodo guardar cliente....");
        ResultSet resultado = null;
        try {
            resultado = consultar("SELECT ID_CLIENTE FROM CLIENTE WHERE\n" +
                    "RUC_CI_CLIENTE = '"+ cliente.getRucCi()/* + 
                    "' AND NOMBRES_CLIENTE = '"+cliente.getNombres()*/+"'");
            //if(resultado != null){
            if(resultado.next()){
                //JOptionPane.showMessageDialog(null, "...CLIENTE YA SE ENCUENTRA REGISTRADO");
            }
            else{
                //JOptionPane.showMessageDialog(null, "no existe cliente, se ingresara uno nuevo....");
                insertar("INSERT INTO CLIENTE (RUC_CI_CLIENTE, NOMBRES_CLIENTE) VALUES('"+cliente.getRucCi()
                        +"','"+cliente.getNombres()+"')");
                ResultSet resultadoIdcliente = consultar("SELECT ID_CLIENTE FROM CLIENTE WHERE RUC_CI_CLIENTE = '"+cliente.getRucCi()+/*"' AND NOMBRES_CLIENTE = '"+cliente.getNombres()+*/"'");
                String idCliente = "";
                try {
                    while(resultadoIdcliente.next()){
                        idCliente = resultadoIdcliente.getObject(1).toString();
                  //      JOptionPane.showMessageDialog(null, "id de cliente nuevo...." + idCliente);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                ////guardarGastos(idCliente, fecha);
            }
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sumarGastos(String idCliente, ArrayList<Gasto> gastosFactura, String fecha){
        //JOptionPane.showMessageDialog(null, "llego a metodo sumar gastos....");
        double sumaVestimenta, sumaAlimentacion, sumaSalud, sumaEducacion, sumaVivienda, sumaOtros;
        int sumaFacturas;
        ResultSet resultado = null;
        try {
            resultado = consultar("SELECT * FROM GASTOS WHERE\n" +
                    " ID_CLIENTE = '" + idCliente +"' AND ANIO_GASTOS = '" + fecha.substring(6) + "'");
            //if(resultado != null){
            if(resultado.next()){    
                //JOptionPane.showMessageDialog(null, "gastos......" + resultado.getObject(3).toString());
                sumaVestimenta = Double.parseDouble(resultado.getObject(3).toString().replace(",", ".")) + gastosFactura.get(0).getTotalSinIva();
                sumaAlimentacion = Double.parseDouble(resultado.getObject(4).toString().replace(",", ".")) + gastosFactura.get(1).getTotalSinIva();
                sumaSalud = Double.parseDouble(resultado.getObject(5).toString().replace(",", ".")) + gastosFactura.get(2).getTotalSinIva();
                sumaEducacion = Double.parseDouble(resultado.getObject(6).toString().replace(",", ".")) + gastosFactura.get(3).getTotalSinIva();
                sumaVivienda = Double.parseDouble(resultado.getObject(7).toString().replace(",", ".")) + gastosFactura.get(4).getTotalSinIva();
                sumaOtros = Double.parseDouble(resultado.getObject(8).toString().replace(",", ".")) + gastosFactura.get(5).getTotalSinIva();
                sumaFacturas = Integer.parseInt(resultado.getObject(10).toString().replace(",", ".")) + 1;
                //JOptionPane.showMessageDialog(null, "id cliente a modificar gastos...." + idCliente);
                resultado.close();
                consultar("UPDATE GASTOS SET TOTAL_VESTIMENTA_CLIENTE = '" + sumaVestimenta + "',\n" +
                            "TOTAL_ALIMENTACION_CLIENTE = '" + sumaAlimentacion +
                            "', TOTAL_SALUD_CLIENTE = '" + sumaSalud +
                            "', TOTAL_EDUCACION_CLIENTE = '" + sumaEducacion +
                            "', TOTAL_VIVIENDA_CLIENTE = '" + sumaVivienda +
                            "', TOTAL_OTROS_CLIENTE = '" + sumaOtros + 
                            "', TOTAL_FACTURAS = '" + sumaFacturas 
                            + "' WHERE ID_CLIENTE = '" + idCliente + "'");
                //JOptionPane.showMessageDialog(null, "gastos modificados....");
            }
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarGastos(String idCliente, String fecha){
        //JOptionPane.showMessageDialog(null, "llego a metodo guardar GASTOS...." + idCliente + fecha);
        fecha = fecha.substring(6);
        insertar("INSERT INTO GASTOS (ID_CLIENTE, TOTAL_ALIMENTACION_CLIENTE, TOTAL_VESTIMENTA_CLIENTE, "
                + "TOTAL_VIVIENDA_CLIENTE, TOTAL_SALUD_CLIENTE, TOTAL_EDUCACION_CLIENTE, "
                + "TOTAL_OTROS_CLIENTE, ANIO_GASTOS, TOTAL_FACTURAS) VALUES('" + idCliente + "','" + 
                "0,00','0,00','0,00','0,00','0,00','0,00','" + fecha + "','0')");
    }
    
    public void guardarProveedorPersonal(Proveedor proveedor){
        //JOptionPane.showMessageDialog(null, "llego a metodo proveedor....");
        ResultSet resultado = null;
        try {
            resultado = consultar("SELECT ID_PROVEEDOR FROM PROVEEDOR WHERE\n" +
                    "RUC_PROVEEDOR = '" + proveedor.getRuc() +/* 
                    "' AND NOMBRE_PROVEEDOR = '" + proveedor.getNombre()+*/"'");
            //if(resultado != null){
            if(resultado.next()){
                JOptionPane.showMessageDialog(null, "PROVEEDOR YA SE ENCUENTRA REGISTRADO");
                JOptionPane.showMessageDialog(null, proveedor.getTipoGastoPersonal() + " " +resultado.getObject(1).toString());
                String idProveedor = resultado.getObject(1).toString();
                resultado.close();
                consultar("UPDATE PROVEEDOR SET TIPO_GASTO_PERSONAL = '" + proveedor.getTipoGastoPersonal() + "' WHERE ID_PROVEEDOR = '"
                            + idProveedor + "'");
            }
            else{
            //JOptionPane.showMessageDialog(null, "no existe proveedor, se creara uno nuevo");
            insertar("INSERT INTO PROVEEDOR (RUC_PROVEEDOR, NOMBRE_PROVEEDOR, CIUDAD_PROVEEDOR, DIRECCION_PROVEEDOR, TIPO_GASTO_PERSONAL, TIPO_GASTO_NEGOCIO) VALUES('" + 
                    proveedor.getRuc() + "','" + 
                    proveedor.getNombre() + "','" + 
                    proveedor.getCiudad() + "','" +
                    proveedor.getDireccion() + "','" +
                    proveedor.getTipoGastoPersonal() + "','" +
                    proveedor.getTipoGastoNegocio()+"')");
            }
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarProveedorNegocio(Proveedor proveedor){
        //JOptionPane.showMessageDialog(null, "llego a metodo proveedor....");
        ResultSet resultado = null;
        try {
            resultado = consultar("SELECT ID_PROVEEDOR FROM PROVEEDOR WHERE\n" +
                    "RUC_PROVEEDOR = '" + proveedor.getRuc() +/* 
                    "' AND NOMBRE_PROVEEDOR = '" + proveedor.getNombre()+*/"'");
            //if(resultado != null){
            if(resultado.next()){
                /*JOptionPane.showMessageDialog(null, "PROVEEDOR YA SE ENCUENTRA REGISTRADO");
                JOptionPane.showMessageDialog(null, proveedor.getTipoGastoPersonal() + " " +resultado.getObject(1).toString());
                String idProveedor = resultado.getObject(1).toString();
                resultado.close();
                consultar("UPDATE PROVEEDOR SET TIPO_GASTO_PERSONAL = '" + proveedor.getTipoGastoPersonal() + "' WHERE ID_PROVEEDOR = '"
                            + idProveedor + "'");*/
                JOptionPane.showMessageDialog(null, "PROVEEDOR YA SE ENCUENTRA REGISTRADO");
                String idProveedor = resultado.getObject(1).toString();
                resultado.close();
                consultar("UPDATE PROVEEDOR SET TIPO_GASTO_NEGOCIO = '" + proveedor.getTipoGastoNegocio() + "' WHERE ID_PROVEEDOR = '"
                            + idProveedor + "'");
            }
            else{
            //JOptionPane.showMessageDialog(null, "no existe proveedor, se creara uno nuevo");
            insertar("INSERT INTO PROVEEDOR (RUC_PROVEEDOR, NOMBRE_PROVEEDOR, CIUDAD_PROVEEDOR, DIRECCION_PROVEEDOR, TIPO_GASTO_PERSONAL, TIPO_GASTO_NEGOCIO) VALUES('" + 
                    proveedor.getRuc() + "','" + 
                    proveedor.getNombre() + "','" + 
                    proveedor.getCiudad() + "','" +
                    proveedor.getDireccion() + "','" +
                    proveedor.getTipoGastoPersonal() + "','" +
                    proveedor.getTipoGastoNegocio()+"')");
            }
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Boolean existeProveedor(String ruc){
        Boolean existe = true;
        ResultSet resultado = null;
        try {
            resultado = consultar("SELECT ID_PROVEEDOR FROM PROVEEDOR WHERE\n" +
                    "RUC_PROVEEDOR = '" + ruc+/* 
                    "' AND NOMBRE_PROVEEDOR = '" + proveedor.getNombre()+*/"'");
            if(resultado.next()){
                JOptionPane.showMessageDialog(null, "PROVEEDOR YA SE ENCUENTRA REGISTRADO");
                existe = false;
            }
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
    
    public Proveedor consultarProveedor(String nombreProv){
        //Boolean existe = true;
        ResultSet resultado = null;
        Proveedor prov = new Proveedor();
        try {
            resultado = consultar("SELECT * FROM PROVEEDOR WHERE\n" +
                    " NOMBRE_PROVEEDOR = '" + nombreProv+/* 
                    "' AND NOMBRE_PROVEEDOR = '" + proveedor.getNombre()+*/"'");
            if(resultado.next()){
                prov.setId((String)resultado.getObject(1).toString());
                prov.setRuc((String)resultado.getObject(2));
                prov.setDireccion((String)resultado.getObject(5));
            }
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prov;
    }
    
    public Cliente consultarCliente(String nombreCliente){
        //Boolean existe = true;
        ResultSet resultado = null;
        Cliente clie = new Cliente();
        try {
            resultado = consultar("SELECT * FROM CLIENTE WHERE\n" +
                    " NOMBRES_CLIENTE = '" + nombreCliente+/* 
                    "' AND NOMBRE_PROVEEDOR = '" + proveedor.getNombre()+*/"'");
            if(resultado.next()){
                clie.setId((String)resultado.getObject(1).toString());
                clie.setRucCi((String)resultado.getObject(2));
            }
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clie;
    }
    
    public Boolean tieneGastoPersonalProveedor(String ruc){
        Boolean existe = true;
        ResultSet resultado = null;
        try {
            resultado = consultar("SELECT TIPO_GASTO_PERSONAL FROM PROVEEDOR WHERE\n" +
                    "RUC_PROVEEDOR = '" + ruc+/* 
                    "' AND NOMBRE_PROVEEDOR = '" + proveedor.getNombre()+*/"'");
            if((resultado.next())&&(resultado!=null)){
                JOptionPane.showMessageDialog(null, "PROVEEDOR YA TIENE GASTO PERSONAL REGISTRADO");
                //JOptionPane.showMessageDialog(null, (String)resultado.getObject(1));
                existe = false;
                if(resultado.getObject(1).toString().equals("null")){
                    existe = true;
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "PROVEEDOR NO TIENE GASTO PERSONAL REGISTRADO");
            }
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
    
    public Boolean tieneGastoNegocioProveedor(String ruc){
        Boolean existe = true;
        ResultSet resultado = null;
        try {
            resultado = consultar("SELECT TIPO_GASTO_NEGOCIO FROM PROVEEDOR WHERE \n" +
                    "RUC_PROVEEDOR = '" + ruc+/* 
                    "' AND NOMBRE_PROVEEDOR = '" + proveedor.getNombre()+*/"'");
            if((resultado.next())&&(resultado!=null)){
                JOptionPane.showMessageDialog(null, "PROVEEDOR YA TIENE GASTO NEGOCIO REGISTRADO");
                existe = false;
                if(resultado.getObject(1).toString().equals("null")){
                    existe = true;
                }
            }
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
    
    public String tipoGastoProveedorPersonal(String ruc){
        String existe = "";
        ResultSet resultado = null;
        try {
            resultado = consultar("SELECT TIPO_GASTO_PERSONAL FROM PROVEEDOR WHERE\n" +
                    "RUC_PROVEEDOR = '" + ruc+/* 
                    "' AND NOMBRE_PROVEEDOR = '" + proveedor.getNombre()+*/"'");
            if(resultado.next()){
                JOptionPane.showMessageDialog(null, "PROVEEDOR YA SE ENCUENTRA REGISTRADO");
                existe = (String)resultado.getObject(1);
            }
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
    
    public String tipoGastoProveedorNegocio(String ruc){
        String existe = "";
        ResultSet resultado = null;
        try {
            resultado = consultar("SELECT TIPO_GASTO_NEGOCIO FROM PROVEEDOR WHERE\n" +
                    "RUC_PROVEEDOR = '" + ruc+/* 
                    "' AND NOMBRE_PROVEEDOR = '" + proveedor.getNombre()+*/"'");
            if(resultado.next()){
                JOptionPane.showMessageDialog(null, "PROVEEDOR YA SE ENCUENTRA REGISTRADO");
                existe = (String)resultado.getObject(1);
            }
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
    
    public String existeProductoTipoDeGasto(String nombreProducto){
        String existe = null;
        ResultSet resultado = null;
        try {
            resultado = consultar("SELECT TIPO_GASTO FROM PRODUCTO WHERE NOMBRE_PRODUCTO = '" + nombreProducto+"'");
            if(resultado.next()){
                //JOptionPane.showMessageDialog(null, "SI EXISTE PRODUCTO");
                existe = (String)resultado.getObject(1);
            }
            resultado.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
  
    public void totalPersonas(DefaultTableModel tableModel){
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sql = "select rucProv AS \"RUC PROVEEDOR\", nombreProv AS \"NOMBRE PROVEEDOR\", direccionProv AS \"DIRECCION PROVEEDOR\" from Proveedor";
        try {
            resultado = consultar(sql);
            if(resultado != null){
                int numeroColumna = resultado.getMetaData().getColumnCount();
                for(int j = 1;j <= numeroColumna;j++){
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }
                while(resultado.next()){
                    Object []objetos = new Object[numeroColumna];
                    for(int i = 1;i <= numeroColumna;i++){
                        objetos[i-1] = resultado.getObject(i);
                    }
                    tableModel.addRow(objetos);
                }
            }
        }catch(SQLException e){
        }

        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    
    public void totalFacturasPorCliente(DefaultTableModel tableModel, String nombreCliente){
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sql = "SELECT P.NOMBRE_PROVEEDOR AS 'NOMBRE DE PROVEEDOR', C.CODIGO_FACTURA AS 'CODIGO', C.FECHA AS 'FECHA', C.IVA AS 'IVA', \n" +
                        "C.TOTAL_CON_IVA AS 'TOTAL', C.TOTAL_VESTIMENTA_CLIENTE AS 'VESTIMENTA', \n" +
                        "C.TOTAL_ALIMENTACION_CLIENTE AS 'ALIMENTACION', C.TOTAL_SALUD_CLIENTE AS 'SALUD', \n" +
                        "C.TOTAL_EDUCACION_CLIENTE AS 'EDUCACION', C.TOTAL_VIVIENDA_CLIENTE AS 'VIVIENDA', \n" +
                        "C.TOTAL_OTROS_CLIENTE AS 'OTROS' FROM PROVEEDOR P INNER JOIN FACTURA C \n" +
                        "ON C.ID_PROVEEDOR = P.ID_PROVEEDOR \n" +
                        "INNER JOIN CLIENTE CLI \n" +
                        "ON CLI.ID_CLIENTE = C.ID_CLIENTE \n" +
                        "WHERE CLI.NOMBRES_CLIENTE = '" + nombreCliente + "'";
        try {
            resultado = consultar(sql);
            if(resultado != null){
                int numeroColumna = resultado.getMetaData().getColumnCount();
                for(int j = 1;j <= numeroColumna;j++){
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }
                while(resultado.next()){
                    Object []objetos = new Object[numeroColumna];
                    for(int i = 1;i <= numeroColumna;i++){
                        objetos[i-1] = resultado.getObject(i);
                    }
                    tableModel.addRow(objetos);
                }
            }
        }catch(SQLException e){
        }

        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    
    public void totalFacturasPorClientePorAnio(DefaultTableModel tableModel, String nombreCliente){
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sql = "SELECT  C.RUC_CI_CLIENTE AS 'RUC/CI CLIENTE', C.NOMBRES_CLIENTE AS 'NOMBRES CLIENTE',\n" +
                    " G.ANIO_GASTOS AS 'ANIO GASTOS', G.TOTAL_FACTURAS AS 'TOTAL FACTURAS'\n" +
                    " FROM CLIENTE C INNER JOIN GASTOS G\n" +
                    " ON C.ID_CLIENTE = G.ID_CLIENTE\n" +
                    " WHERE C.NOMBRES_CLIENTE = '" + nombreCliente + "'";
        try {
            resultado = consultar(sql);
            if(resultado != null){
                int numeroColumna = resultado.getMetaData().getColumnCount();
                for(int j = 1;j <= numeroColumna;j++){
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }
                while(resultado.next()){
                    Object []objetos = new Object[numeroColumna];
                    for(int i = 1;i <= numeroColumna;i++){
                        objetos[i-1] = resultado.getObject(i);
                    }
                    tableModel.addRow(objetos);
                }
            }
        }catch(SQLException e){
        }

        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    
    public void totalFacturasPorClientePorAnioNegocio(DefaultTableModel tableModel, String nombreCliente){
        ResultSet resultado = null;
        ResultSet resultadoNF = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sql2 = "SELECT count(F.ID_FACTURA2) FROM FACTURA_NEGOCIO F INNER JOIN CLIENTE C \n"+
                        "ON F.ID_CLIENTE = C.ID_CLIENTE\n"+
                        " WHERE C.NOMBRES_CLIENTE = '"+nombreCliente+"'";
        String sql = "SELECT  C.RUC_CI_CLIENTE AS 'RUC/CI CLIENTE', C.NOMBRES_CLIENTE AS 'NOMBRES CLIENTE',\n" +
                    " G.ANIO_GASTO_EXTRA AS 'ANIO GASTOS', G.ID_GASTO_EXTRA AS 'NUMERO DE FACTURAS' \n" +
                    " FROM CLIENTE C INNER JOIN GASTOS_DE_NEGOCIO G\n" +
                    " ON C.ID_CLIENTE = G.ID_CLIENTE\n" +
                    " WHERE C.NOMBRES_CLIENTE = '" + nombreCliente + "'";
        try {
            resultado = consultar(sql);
            resultadoNF = consultar(sql2);
            if(resultado != null){
                int numeroColumna = resultado.getMetaData().getColumnCount();
                for(int j = 1;j <= numeroColumna;j++){
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }
                while(resultado.next()){
                    Object []objetos = new Object[numeroColumna];
                    for(int i = 1;i <= numeroColumna;i++){
                        objetos[i-1] = resultado.getObject(i);
                        if(i==4){
                            objetos[i-1] = resultadoNF.getObject(1);
                        }
                    }
                    resultadoNF.close();
                    tableModel.addRow(objetos);
                }
            }
        }catch(SQLException e){
        }
        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
                //resultadoNF.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    
    public void totalClientes(Choice choiceClientes){
        ResultSet resultado = null;
        //String sql = "SELECT NOMBRES_CLIENTE FROM CLIENTE";
        String sql = "SELECT DISTINCT C.NOMBRES_CLIENTE FROM CLIENTE C INNER JOIN FACTURA F \n" +
                        "ON C.ID_CLIENTE = F.ID_CLIENTE \n";
        try {
            resultado = consultar(sql);
            if(resultado != null){
                while(resultado.next()){
                        choiceClientes.addItem((String) resultado.getObject(1));
                }
            }
        }catch(SQLException e){
        }
        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    
    public void totalClientesNegocio(Choice choiceClientes){
        ResultSet resultado = null;
        //String sql = "SELECT NOMBRES_CLIENTE FROM CLIENTE";
        String sql = "SELECT DISTINCT C.NOMBRES_CLIENTE FROM CLIENTE C INNER JOIN FACTURA_NEGOCIO F \n" +
                        "ON C.ID_CLIENTE = F.ID_CLIENTE \n";
        try {
            resultado = consultar(sql);
            if(resultado != null){
                while(resultado.next()){
                        choiceClientes.addItem((String) resultado.getObject(1));
                }
            }
        }catch(SQLException e){
        }
        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    ////////////////
    public void totalProveedoresPorClientePorAnio(Choice choiceProveedores, String nombreCliente, String anio){
        ResultSet resultado = null;
        //tableModel.setRowCount(0);
        //tableModel.setColumnCount(0);
        String sql = "SELECT P.NOMBRE_PROVEEDOR FROM PROVEEDOR P INNER JOIN FACTURA F \n" +
                        "ON P.ID_PROVEEDOR = F.ID_PROVEEDOR \n" +
                        "INNER JOIN CLIENTE C \n" +
                        "ON C.ID_CLIENTE = F.ID_CLIENTE \n" +
                        "WHERE C.NOMBRES_CLIENTE = '" + nombreCliente 
                        +"' AND F.FECHA LIKE '%" + anio + "' GROUP BY P.NOMBRE_PROVEEDOR";
        try {
            resultado = consultar(sql);
            
            if(resultado != null){
                while(resultado.next()){
                        choiceProveedores.addItem((String) resultado.getObject(1));
                }
            }
        }catch(SQLException e){
        }
        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    
    public void totalProveedoresPorClientePorAnioNegocio(Choice choiceProveedores, String nombreCliente, String anio){
        ResultSet resultado = null;
        //tableModel.setRowCount(0);
        //tableModel.setColumnCount(0);
        String sql = "SELECT P.NOMBRE_PROVEEDOR FROM PROVEEDOR P INNER JOIN FACTURA_NEGOCIO F \n" +
                        "ON P.ID_PROVEEDOR = F.ID_PROVEEDOR \n" +
                        "INNER JOIN CLIENTE C \n" +
                        "ON C.ID_CLIENTE = F.ID_CLIENTE \n" +
                        "WHERE C.NOMBRES_CLIENTE = '" + nombreCliente 
                        +"' AND F.FECHA LIKE '%" + anio + "' GROUP BY P.NOMBRE_PROVEEDOR";
        try {
            resultado = consultar(sql);
            
            if(resultado != null){
                while(resultado.next()){
                        choiceProveedores.addItem((String) resultado.getObject(1));
                }
            }
        }catch(SQLException e){
        }
        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    
    public void totalAniosPorCliente(Choice choiceProveedores, String nombreCliente){
        ResultSet resultado = null;
        //tableModel.setRowCount(0);
        //tableModel.setColumnCount(0);
        String sql = "SELECT G.ANIO_GASTOS FROM GASTOS G INNER JOIN CLIENTE C\n" +
                        " ON G.ID_CLIENTE = C.ID_CLIENTE\n" +
                        " WHERE C.NOMBRES_CLIENTE = '" + nombreCliente + "'";
        try {
            resultado = consultar(sql);
            
            if(resultado != null){
                while(resultado.next()){
                        //JOptionPane.showMessageDialog(null, resultado.getObject(1).getClass());
                        choiceProveedores.addItem((String) resultado.getObject(1).toString());
                }
            }
        }catch(SQLException e){
        }
        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    
    public void totalAniosPorClienteNegocio(Choice choiceProveedores, String nombreCliente){
        ResultSet resultado = null;
        //tableModel.setRowCount(0);
        //tableModel.setColumnCount(0);
        String sql = "SELECT DISTINCT G.ANIO_GASTO_EXTRA FROM GASTOS_DE_NEGOCIO G INNER JOIN CLIENTE C\n" +
                        " ON G.ID_CLIENTE = C.ID_CLIENTE\n" +
                        " WHERE C.NOMBRES_CLIENTE = '" + nombreCliente + "'";
        try {
            resultado = consultar(sql);
            
            if(resultado != null){
                while(resultado.next()){
                        //JOptionPane.showMessageDialog(null, resultado.getObject(1).getClass());
                        choiceProveedores.addItem((String) resultado.getObject(1).toString());
                }
            }
        }catch(SQLException e){
        }
        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    
    public void totalFacturasPorClienteProveedorAnio(DefaultTableModel tableModel, String anio, String nombreCliente,
            String nombreProveedor, DefaultTableModel tableTotales) throws SQLException{
        DecimalFormat formateador = new DecimalFormat("###.##");
        Double ivaTotal = 0.0, total = 0.0, vestimenta = 0.0, alimentacion = 0.0, salud = 0.0;
        Double educacion = 0.0, vivienda = 0.0, otros = 0.0;
        int numeroFacturas = 0;
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sql = "SELECT P.NOMBRE_PROVEEDOR AS 'NOMBRE DE PROVEEDOR', C.CODIGO_FACTURA AS 'CODIGO', C.FECHA AS 'FECHA', C.IVA AS 'IVA', \n" +
                            "C.TOTAL_CON_IVA AS 'TOTAL', C.TOTAL_VESTIMENTA_CLIENTE AS 'VESTIMENTA', \n" +
                            "C.TOTAL_ALIMENTACION_CLIENTE AS 'ALIMENTACION', C.TOTAL_SALUD_CLIENTE AS 'SALUD', \n" +
                            "C.TOTAL_EDUCACION_CLIENTE AS 'EDUCACION', C.TOTAL_VIVIENDA_CLIENTE AS 'VIVIENDA', \n" +
                            "C.TOTAL_OTROS_CLIENTE AS 'OTROS' FROM PROVEEDOR P INNER JOIN FACTURA C \n" +
                            "ON C.ID_PROVEEDOR = P.ID_PROVEEDOR \n" +
                            "INNER JOIN CLIENTE CLI \n" +
                            "ON CLI.ID_CLIENTE = C.ID_CLIENTE \n" +
                            "WHERE CLI.NOMBRES_CLIENTE = '" + nombreCliente + "' AND P.NOMBRE_PROVEEDOR = '" + nombreProveedor + 
                            "' AND C.FECHA LIKE '%" + anio + "'";
        
        try {
            resultado = consultar(sql);
            if(resultado != null){
                int numeroColumna = resultado.getMetaData().getColumnCount();
                for(int j = 1;j <= numeroColumna;j++){
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }
                while(resultado.next()){
                    numeroFacturas++;
                    Object []objetos = new Object[numeroColumna];
                    for(int i = 1;i <= numeroColumna;i++){
                        //objetos[i-1] = resultado.getObject(i);
                        if (i < 4){
                            objetos[i-1] = resultado.getObject(i);
                        }else{
                            objetos[i-1] = formateador.format(resultado.getObject(i));
                        }
                        if(i==4)    ivaTotal = ivaTotal + Double.parseDouble(resultado.getObject(i).toString().replace(",", "."));    
                        if(i==5)    total = total + Double.parseDouble(resultado.getObject(i).toString().replace(",", "."));
                        if(i==6)    vestimenta = vestimenta + Double.parseDouble(resultado.getObject(i).toString().replace(",", "."));
                        if(i==7)    alimentacion = alimentacion + Double.parseDouble(resultado.getObject(i).toString().replace(",", "."));
                        if(i==8)    salud = salud + Double.parseDouble(resultado.getObject(i).toString().replace(",", "."));
                        if(i==9)    educacion = educacion + Double.parseDouble(resultado.getObject(i).toString().replace(",", "."));
                        if(i==10)   vivienda = vivienda + Double.parseDouble(resultado.getObject(i).toString().replace(",", "."));
                        if(i==11)   otros = otros + Double.parseDouble(resultado.getObject(i).toString().replace(",", "."));
                        
                    }
                    tableModel.addRow(objetos);
                }
            }
            Object []objetosTotales = {nombreProveedor,numeroFacturas,formateador.format(ivaTotal),formateador.format(total),vestimenta,alimentacion,salud,educacion,vivienda,otros};
            //String[] DATA = {nombreProveedor,(String)numeroFacturas, "Dato 3", "Dato 4"};
            tableTotales.addRow(objetosTotales);
        }catch(SQLException e){
        }

        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
//////////////////////////////////////////////////////////////////////////////////////////    
    public void totalFacturasPorClienteProveedorAnioNegocio(DefaultTableModel tableModel, String anio, String nombreCliente,
            String nombreProveedor, DefaultTableModel tableTotales) throws SQLException{
        
        int numeroFacturas = 0;
        ResultSet resultado = null;
        ResultSet resultadoNombreGastos = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sqlNG = "SELECT NOMBRE_GASTO_EXTRA FROM GASTOS_DE_NEGOCIO";
        String sql = "SELECT P.NOMBRE_PROVEEDOR AS 'NOMBRE DE PROVEEDOR', C.CODIGO_FACTURA AS 'CODIGO', C.FECHA AS 'FECHA', C.IVA AS 'IVA', \n" +
                            "C.TOTAL_CON_IVA AS 'TOTAL' FROM PROVEEDOR P INNER JOIN FACTURA_NEGOCIO C \n" +
                            "ON C.ID_PROVEEDOR = P.ID_PROVEEDOR \n" +
                            "INNER JOIN CLIENTE CLI \n" +
                            "ON CLI.ID_CLIENTE = C.ID_CLIENTE \n" +
                            "WHERE CLI.NOMBRES_CLIENTE = '" + nombreCliente + "' AND P.NOMBRE_PROVEEDOR = '" + nombreProveedor + 
                            "' AND C.FECHA LIKE '%" + anio + "'";
        try {
            resultado = consultar(sql);
            resultadoNombreGastos = consultar(sqlNG);
            if(resultado != null){
                int numeroColumna = resultado.getMetaData().getColumnCount();
                for(int j = 1;j <= numeroColumna;j++){
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }
                int numeroColumnaGastos = 0;
                while(resultadoNombreGastos.next()){
                    numeroColumnaGastos++;
                    tableModel.addColumn(resultadoNombreGastos.getObject(1));
                }
                resultadoNombreGastos.close();
                while(resultado.next()){
                    numeroFacturas++;
                    Object []objetos = new Object[numeroColumna + numeroColumnaGastos];
                    for(int i = 1;i <= numeroColumna;i++){
                        objetos[i-1] = resultado.getObject(i);
                    }
                    for(int i = numeroColumna+1 ;i <= numeroColumnaGastos+numeroColumna;i++){
                        objetos[i-1] = consultarTotalGastoFacturaNegocio((String) resultado.getObject(2), tableModel.getColumnName(i-1));
                    }
                    tableModel.addRow(objetos);
                }
            }
        }catch(SQLException e){
        }

        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    
    public void totalFacturasPorClienteYAnio(DefaultTableModel tableModel, String anio, String nombreCliente) throws SQLException{
        DecimalFormat formateador = new DecimalFormat("###.##");
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sql = "SELECT P.NOMBRE_PROVEEDOR AS 'NOMBRE DE PROVEEDOR', C.CODIGO_FACTURA AS 'CODIGO', C.FECHA AS 'FECHA', C.IVA AS 'IVA', \n" +
                            "C.TOTAL_CON_IVA AS 'TOTAL', C.TOTAL_VESTIMENTA_CLIENTE AS 'VESTIMENTA', \n" +
                            "C.TOTAL_ALIMENTACION_CLIENTE AS 'ALIMENTACION', C.TOTAL_SALUD_CLIENTE AS 'SALUD', \n" +
                            "C.TOTAL_EDUCACION_CLIENTE AS 'EDUCACION', C.TOTAL_VIVIENDA_CLIENTE AS 'VIVIENDA', \n" +
                            "C.TOTAL_OTROS_CLIENTE AS 'OTROS' FROM PROVEEDOR P INNER JOIN FACTURA C \n" +
                            "ON C.ID_PROVEEDOR = P.ID_PROVEEDOR \n" +
                            "INNER JOIN CLIENTE CLI \n" +
                            "ON CLI.ID_CLIENTE = C.ID_CLIENTE \n" +
                            "WHERE CLI.NOMBRES_CLIENTE = '" + nombreCliente + "' AND C.FECHA LIKE '%" + anio + "'";
        
        try {
            resultado = consultar(sql);
            if(resultado != null){
                int numeroColumna = resultado.getMetaData().getColumnCount();
                for(int j = 1;j <= numeroColumna;j++){
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }
                while(resultado.next()){
                    Object []objetos = new Object[numeroColumna];
                    for(int i = 1;i <= numeroColumna;i++){
                        if(i >= 4){
                        objetos[i-1] = formateador.format(resultado.getObject(i));
                        }else{
                            objetos[i-1] = resultado.getObject(i);
                        }
                    }
                    tableModel.addRow(objetos);
                }
            }
        }catch(SQLException e){
        }

        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    
    public String consultarTotalGastoFacturaNegocio(String codigoFactura, String nombreGasto){
        //JOptionPane.showMessageDialog(null, "codigo "+codigoFactura+" nombregasto "+nombreGasto);
        ResultSet resultado = null;
        String total = "";
        String sql = "SELECT G.TOTAL_GASTO_EXTRA_FACTURA FROM GASTOS_DE_NEGOCIO_FACTURA G INNER JOIN FACTURA_NEGOCIO F "
                + " ON G.ID_FACTURA2 = F.ID_FACTURA2"
                + " WHERE F.CODIGO_FACTURA = '"+codigoFactura+"' AND G.NOMBRE_GASTO_EXTRA_FACTURA = '"+nombreGasto+"'";
        try {
            System.out.println(sql);
            resultado = consultar(sql);
            System.out.println(resultado.toString());
            //JOptionPane.showMessageDialog(null,sql); 
                while(resultado.next()){
                    //JOptionPane.showMessageDialog(null, resultado.getObject(1));
                    total = resultado.getObject(1).toString();
                }
        }catch(Exception e){
        }
         return total;
    }
    
    public void totalFacturasPorClienteYAnioNegocio(DefaultTableModel tableModel, String anio, String nombreCliente) throws SQLException{
        ResultSet resultado = null;
        ResultSet resultadoNombreGastos = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sqlNG = "SELECT NOMBRE_GASTO_EXTRA FROM GASTOS_DE_NEGOCIO";
        String sql = "SELECT P.NOMBRE_PROVEEDOR AS 'NOMBRE DE PROVEEDOR', C.CODIGO_FACTURA AS 'CODIGO', C.FECHA AS 'FECHA', C.IVA AS 'IVA', \n" +
                            "C.TOTAL_CON_IVA AS 'TOTAL' FROM PROVEEDOR P INNER JOIN FACTURA_NEGOCIO C \n" +
                            "ON C.ID_PROVEEDOR = P.ID_PROVEEDOR \n" +
                            "INNER JOIN CLIENTE CLI \n" +
                            "ON CLI.ID_CLIENTE = C.ID_CLIENTE \n" +
                            "WHERE CLI.NOMBRES_CLIENTE = '" + nombreCliente + "' AND C.FECHA LIKE '%" + anio + "'";
        
        try {
            resultado = consultar(sql);
            resultadoNombreGastos = consultar(sqlNG);
            if(resultado != null){
                int numeroColumna = resultado.getMetaData().getColumnCount();
                for(int j = 1;j <= numeroColumna;j++){
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }
                int numeroColumnaGastos = 0;
                while(resultadoNombreGastos.next()){
                    numeroColumnaGastos++;
                    tableModel.addColumn(resultadoNombreGastos.getObject(1));
                }
                resultadoNombreGastos.close();
                while(resultado.next()){
                    //JOptionPane.showMessageDialog(null,"NUMERO DE COLUMNAS TOTAL = " + (numeroColumna+numeroColumnaGastos));
                    Object []objetos = new Object[numeroColumna+numeroColumnaGastos];
                    for(int i = 1;i <= numeroColumna;i++){
                        objetos[i-1] = resultado.getObject(i);
                    }
                    for(int i = numeroColumna+1 ;i <= numeroColumnaGastos+numeroColumna;i++){
                        //JOptionPane.showMessageDialog(null,"sdf");
                        //JOptionPane.showMessageDialog(null, tableModel.getColumnName(i-1));
                        objetos[i-1] = consultarTotalGastoFacturaNegocio((String) resultado.getObject(2), tableModel.getColumnName(i-1));
                    }
                    tableModel.addRow(objetos);
                }
            }
        }catch(SQLException e){
        }
/*
        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }*/
    }
    
    public void consultarProductos(DefaultTableModel tableModel, String codigoFactura) throws SQLException{
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sql = "SELECT P.CODIGO_PRODUCTO AS 'CODIGO', P.NOMBRE_PRODUCTO AS 'NOMBRE',"
                            + "C.CANTIDAD AS 'CANTIDAD', P.PRECIO_UNITARIO AS 'P UNIT', P.TIPO_GASTO AS 'GASTO' \n" +
                            "FROM PRODUCTO P INNER JOIN CONTIENE C \n" +
                            "ON P.ID_PRODUCTO = C.ID_PRODUCTO \n" +
                            "INNER JOIN FACTURA F \n" +
                            "ON C.ID_FACTURA = F.ID_FACTURA \n" +
                            "WHERE F.CODIGO_FACTURA = '" + codigoFactura + "'";
        try {
            resultado = consultar(sql);
            if(resultado != null){
                int numeroColumna = resultado.getMetaData().getColumnCount();
                for(int j = 1;j <= numeroColumna;j++){
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }
                while(resultado.next()){
                    Object []objetos = new Object[numeroColumna];
                    for(int i = 1;i <= numeroColumna;i++){
                        objetos[i-1] = resultado.getObject(i);
                    }
                    tableModel.addRow(objetos);
                }
            }
        }catch(SQLException e){
        }

        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    
    public void consultarProductosFNegocio(DefaultTableModel tableModel, String codigoFactura) throws SQLException{
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sql = "SELECT P.CODIGO_PRODUCTO AS 'CODIGO', P.NOMBRE_PRODUCTO AS 'NOMBRE',"
                            + "C.CANTIDAD AS 'CANTIDAD', P.PRECIO_UNITARIO AS 'P UNIT', P.TIPO_GASTO AS 'GASTO' \n" +
                            "FROM PRODUCTO P INNER JOIN CONTIENE_PROD C \n" +
                            "ON P.ID_PRODUCTO = C.ID_PRODUCTO \n" +
                            "INNER JOIN FACTURA_NEGOCIO F \n" +
                            "ON C.ID_FACTURA2 = F.ID_FACTURA2 \n" +
                            "WHERE F.CODIGO_FACTURA = '" + codigoFactura + "'";
        try {
            resultado = consultar(sql);
            if(resultado != null){
                int numeroColumna = resultado.getMetaData().getColumnCount();
                for(int j = 1;j <= numeroColumna;j++){
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }
                while(resultado.next()){
                    Object []objetos = new Object[numeroColumna];
                    for(int i = 1;i <= numeroColumna;i++){
                        objetos[i-1] = resultado.getObject(i);
                    }
                    tableModel.addRow(objetos);
                }
            }
        }catch(SQLException e){
        }

        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    
    public void consultarGastosFNegocio(DefaultTableModel tableModel, String codigoFactura) throws SQLException{
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sql = "SELECT G.NOMBRE_GASTO_EXTRA_FACTURA AS 'TIPO',"
                            + " G.TOTAL_GASTO_EXTRA_FACTURA AS 'TOTAL' \n" +
                            "FROM GASTOS_DE_NEGOCIO_FACTURA G INNER JOIN FACTURA_NEGOCIO F \n" +
                            "ON G.ID_FACTURA2 = F.ID_FACTURA2 \n" +
                            "WHERE F.CODIGO_FACTURA = '" + codigoFactura + "'";
        try {
            resultado = consultar(sql);
            if(resultado != null){
                int numeroColumna = resultado.getMetaData().getColumnCount();
                for(int j = 1;j <= numeroColumna;j++){
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }
                while(resultado.next()){
                    Object []objetos = new Object[numeroColumna];
                    for(int i = 1;i <= numeroColumna;i++){
                        objetos[i-1] = resultado.getObject(i);
                    }
                    tableModel.addRow(objetos);
                }
            }
        }catch(SQLException e){
        }

        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    }
    
    public ArrayList<String> leerNombreDeGastosDeNegocioGuardadados(){
    ArrayList<String> listaGastosNombres = new ArrayList<String>();
    ResultSet resultado = null;
        String sql = "SELECT NOMBRE_GASTO_EXTRA FROM GASTOS_DE_NEGOCIO";
        try {
            resultado = consultar(sql);
            if(resultado != null){
                while(resultado.next()){
                        //choiceClientes.addItem((String) resultado.getObject(1));
                        listaGastosNombres.add((String)resultado.getObject(1));
                }
            }
        }catch(SQLException e){
        }
        finally
     {
         try
         {
             consulta.close();
             conexion.close();
             if(resultado != null){
                resultado.close();
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
        return listaGastosNombres;
    }
    
    public void guardarProductosFacturaPersonal(ArrayList<Producto> listaProductos, String codigoFactura){
        for(Producto prod : listaProductos){
            ResultSet resultado = null;
            try {
                resultado = consultar("SELECT ID_PRODUCTO FROM PRODUCTO WHERE\n" +
                        "NOMBRE_PRODUCTO = '" + prod.getNombre() + "'");
                
                if(resultado.next()){
                    //JOptionPane.showMessageDialog(null, "PRODUCTO YA SE ENCUENTRA REGISTRADO");
                    String IdProd = String.valueOf(resultado.getObject(1));
                    resultado.close();
                    //funcion para ingresar en tabla contiene 
                    this.guardarEnContieneFacturaPersonal(IdProd, codigoFactura, prod.getCantidad());
                }
                else{
                //JOptionPane.showMessageDialog(null, "no existe producto, se creara uno nuevo "
                //+ prod.getCodigo() +" "+ prod.getNombre() +" "+  prod.getTipo());
                    insertar("INSERT INTO PRODUCTO (CODIGO_PRODUCTO, NOMBRE_PRODUCTO, TIPO_GASTO, PRECIO_UNITARIO) VALUES ('" + 
                        prod.getCodigo() + "','" + 
                        prod.getNombre() + "','" + 
                        prod.getTipo() + "','" + 
                        prod.getPrecioUnitario() +"')");
                    ResultSet resultadoIdProducto = null;
                    resultadoIdProducto = consultar("SELECT ID_PRODUCTO FROM PRODUCTO WHERE\n" +
                        "NOMBRE_PRODUCTO = '" + prod.getNombre() + "'");
                    String IdProd = String.valueOf(resultadoIdProducto.getObject(1));
                    resultadoIdProducto.close();
             ///////////////////
             //JOptionPane.showMessageDialog(null, resultado);
                    this.guardarEnContieneFacturaPersonal(IdProd, codigoFactura, prod.getCantidad());
                }
//                resultado.close();
            } catch (SQLException ex) {
                //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void guardarEnContieneFacturaPersonal(String idProducto, String codigoFactura, double cantidad){
        //JOptionPane.showMessageDialog(null, "llega para guardar en tabla CONTIENE "
                //+ idProducto +" "+ codigoFactura +" "+  cantidad);
            ResultSet resultado = null;
            try {
                resultado = consultar("SELECT ID_FACTURA FROM FACTURA WHERE\n" +
                        "CODIGO_FACTURA = '" + codigoFactura + "'");
                //if(resultado != null){
                if(resultado.next()){
                    //JOptionPane.showMessageDialog(null, "se guardara en tabla CONTIENE "
                    //+ String.valueOf(resultado.getObject(1)) +" "+ idProducto +" "+  String.valueOf(cantidad));
                    String IdFactura = String.valueOf(resultado.getObject(1));
                    resultado.close();
                    insertar("INSERT INTO CONTIENE (ID_FACTURA, ID_PRODUCTO, CANTIDAD) VALUES ('" + 
                        IdFactura + "','" + 
                        idProducto + "','" + 
                        String.valueOf(cantidad) +"')");
                }
                else{
                //JOptionPane.showMessageDialog(null, "no existe proveedor, se creara uno nuevo");
                }
            } catch (SQLException ex) {
                //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void guardarProductosFacturaNegocio(ArrayList<Producto> listaProductos, String codigoFactura){
        for(Producto prod : listaProductos){
            ResultSet resultado = null;
            try {
                resultado = consultar("SELECT ID_PRODUCTO FROM PRODUCTO WHERE\n" +
                        "NOMBRE_PRODUCTO = '" + prod.getNombre() + "'");
                
                if(resultado.next()){
                    //JOptionPane.showMessageDialog(null, "PRODUCTO YA SE ENCUENTRA REGISTRADO");
                    String IdProd = String.valueOf(resultado.getObject(1));
                    resultado.close();
                    //funcion para ingresar en tabla contiene 
                    this.guardarEnContieneFacturaNegocio(IdProd, codigoFactura, prod.getCantidad());
                }
                else{
                //JOptionPane.showMessageDialog(null, "no existe producto, se creara uno nuevo "
                //+ prod.getCodigo() +" "+ prod.getNombre() +" "+  prod.getTipo());
                    insertar("INSERT INTO PRODUCTO (CODIGO_PRODUCTO, NOMBRE_PRODUCTO, TIPO_GASTO, PRECIO_UNITARIO) VALUES ('" + 
                        prod.getCodigo() + "','" + 
                        prod.getNombre() + "','" + 
                        prod.getTipo()+ "','" + 
                        prod.getPrecioUnitario() +"')");
                    ResultSet resultadoIdProducto = null;
                    resultadoIdProducto = consultar("SELECT ID_PRODUCTO FROM PRODUCTO WHERE\n" +
                        "NOMBRE_PRODUCTO = '" + prod.getNombre() + "'");
                    String IdProd = String.valueOf(resultadoIdProducto.getObject(1));
                    resultadoIdProducto.close();
             ///////////////////
             //JOptionPane.showMessageDialog(null, resultado);
                    this.guardarEnContieneFacturaNegocio(IdProd, codigoFactura, prod.getCantidad());
                }
//                resultado.close();
            } catch (SQLException ex) {
                //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void guardarEnContieneFacturaNegocio(String idProducto, String codigoFactura, double cantidad){
        //JOptionPane.showMessageDialog(null, "llega para guardar en tabla CONTIENE_PROD "
                //+ idProducto +" "+ codigoFactura +" "+  cantidad);
            ResultSet resultado = null;
            try {
                resultado = consultar("SELECT ID_FACTURA2 FROM FACTURA_NEGOCIO WHERE\n" +
                        "CODIGO_FACTURA = '" + codigoFactura + "'");
                //if(resultado != null){
                if(resultado.next()){
                    //JOptionPane.showMessageDialog(null, "se guardara en tabla CONTIENE_PROD "
                    //+ String.valueOf(resultado.getObject(1)) +" "+ idProducto +" "+  String.valueOf(cantidad));
                    String IdFactura = String.valueOf(resultado.getObject(1));
                    resultado.close();
                    insertar("INSERT INTO CONTIENE_PROD (ID_FACTURA2, ID_PRODUCTO, CANTIDAD) VALUES ('" + 
                        IdFactura + "','" + 
                        idProducto + "','" + 
                        String.valueOf(cantidad) +"')");
                }
                else{
                //JOptionPane.showMessageDialog(null, "no existe proveedor, se creara uno nuevo");
                }
            } catch (SQLException ex) {
                //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
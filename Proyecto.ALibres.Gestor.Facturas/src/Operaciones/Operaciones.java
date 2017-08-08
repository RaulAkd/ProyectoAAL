/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

//import Objetos.Persona;
import Pojos.Cliente;
import Pojos.Factura;
import Pojos.Gasto;
import Pojos.Producto;
import Pojos.Proveedor;
import java.awt.Choice;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            if(resultado != null){
                //int numeroColumna = resultado.getMetaData().getColumnCount();

                while(resultado.next()){
                    if((resultado.getObject(1).toString().compareTo(codigoFactura) == 0)
                            &&(resultado.getObject(2).toString().compareTo(rucCiCliente) == 0)
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
            if(resultado != null){
                //int numeroColumna = resultado.getMetaData().getColumnCount();

                while(resultado.next()){
                    if((resultado.getObject(1).toString().compareTo(codigoFactura) == 0)
                            &&(resultado.getObject(2).toString().compareTo(rucCiCliente) == 0)
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
        //JOptionPane.showMessageDialog(null, factura.getFechaDate());
        //JOptionPane.showMessageDialog(null, "id cliente...." + idCliente + "   id proveedor...." + idProveedor);
        insertar("INSERT INTO FACTURA (ID_PROVEEDOR, ID_CLIENTE, CODIGO_FACTURA, FECHA, IVA, "
                + "TOTAL_SIN_IVA, TOTAL_CON_IVA, TOTAL_ALIMENTACION_CLIENTE, TOTAL_VESTIMENTA_CLIENTE, "
                + "TOTAL_VIVIENDA_CLIENTE, TOTAL_SALUD_CLIENTE, TOTAL_EDUCACION_CLIENTE, TOTAL_OTROS_CLIENTE) VALUES('" + 
                    idProveedor + "','" +
                    idCliente + "','" +
                    factura.getCodigo() + "','" + 
                    factura.getFecha() + "','" + 
                    //factura.getFechaDate() + "','" + 
                    factura.getIva() + "','" + 
                    factura.getTotalSinIva() + "','" + 
                    factura.getTotalConIva() + "','" + 
                    factura.getListaGastos().get(0).getTotalSinIva() + "','" + 
                    factura.getListaGastos().get(1).getTotalSinIva() + "','" + 
                    factura.getListaGastos().get(2).getTotalSinIva() + "','" + 
                    factura.getListaGastos().get(3).getTotalSinIva() + "','" + 
                    factura.getListaGastos().get(4).getTotalSinIva() + "','" + 
                    factura.getListaGastos().get(5).getTotalSinIva() +"')");
        //JOptionPane.showMessageDialog(null, "factura guardada....");
        sumarGastos(idCliente, factura.getListaGastos(), factura.getFecha());
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
        fecha = fecha.substring(6);
        //JOptionPane.showMessageDialog(null, fecha);
        //JOptionPane.showMessageDialog(null, "llego a metodo guardar GASTOS....");
        insertar("INSERT INTO GASTOS (ID_CLIENTE, TOTAL_ALIMENTACION_CLIENTE, TOTAL_VESTIMENTA_CLIENTE, "
                + "TOTAL_VIVIENDA_CLIENTE, TOTAL_SALUD_CLIENTE, TOTAL_EDUCACION_CLIENTE, "
                + "TOTAL_OTROS_CLIENTE, ANIO_GASTOS, TOTAL_FACTURAS) VALUES('" + idCliente + "','" + 
                "0,00','0,00','0,00','0,00','0,00','0,00','" + fecha + "','0')");
    }
    
    public void guardarProveedor(Proveedor proveedor){
        //JOptionPane.showMessageDialog(null, "llego a metodo proveedor....");
        ResultSet resultado = null;
        
        try {
            resultado = consultar("SELECT ID_PROVEEDOR FROM PROVEEDOR WHERE\n" +
                    "RUC_PROVEEDOR = '" + proveedor.getRuc() +/* 
                    "' AND NOMBRE_PROVEEDOR = '" + proveedor.getNombre()+*/"'");
            //if(resultado != null){
            if(resultado.next()){
                JOptionPane.showMessageDialog(null, "PROVEEDOR YA SE ENCUENTRA REGISTRADO");
            }
            else{
            //JOptionPane.showMessageDialog(null, "no existe proveedor, se creara uno nuevo");
            insertar("INSERT INTO PROVEEDOR (RUC_PROVEEDOR, NOMBRE_PROVEEDOR, CIUDAD_PROVEEDOR, DIRECCION_PROVEEDOR, TIPO_GASTO) VALUES('" + 
                    proveedor.getRuc() + "','" + 
                    proveedor.getNombre() + "','" + 
                    proveedor.getCiudad() + "','" + 
                    proveedor.getDireccion() +  "','" + 
                    proveedor.getCiudad() +"')");
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
    
    public String existeProductoTipoDeGasto(String nombreProducto){
        String existe = null;
        ResultSet resultado = null;
        try {
            resultado = consultar("SELECT TIPO_GASTO FROM PRODUCTO WHERE NOMBRE_PRODUCTO = '" + nombreProducto+"'");
            if(resultado.next()){
                JOptionPane.showMessageDialog(null, "SI EXISTE PRODUCTO");
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
                        "C.TOTAL_CON_IVA AS 'TOTAL', C.TOTAL_VESTIMENTA_CLIENTE AS 'TOTAL VESTIMENTA', \n" +
                        "C.TOTAL_ALIMENTACION_CLIENTE AS 'TOTAL ALIMENTACION', C.TOTAL_SALUD_CLIENTE AS 'TOTAL SALUD', \n" +
                        "C.TOTAL_EDUCACION_CLIENTE AS 'TOTAL EDUCACION', C.TOTAL_VIVIENDA_CLIENTE AS 'TOTAL VIVIENDA', \n" +
                        "C.TOTAL_OTROS_CLIENTE AS 'TOTAL OTROS' FROM PROVEEDOR P INNER JOIN FACTURA C \n" +
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
    
    public void totalClientes(Choice choiceClientes){
        ResultSet resultado = null;
        String sql = "SELECT NOMBRES_CLIENTE FROM CLIENTE";
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
    
    public void totalFacturasPorClienteProveedorAnio(DefaultTableModel tableModel, String anio, String nombreCliente,
            String nombreProveedor) throws SQLException{
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sql = "SELECT P.NOMBRE_PROVEEDOR AS 'NOMBRE DE PROVEEDOR', C.CODIGO_FACTURA AS 'CODIGO', C.FECHA AS 'FECHA', C.IVA AS 'IVA', \n" +
                            "C.TOTAL_CON_IVA AS 'TOTAL', C.TOTAL_VESTIMENTA_CLIENTE AS 'TOTAL VESTIMENTA', \n" +
                            "C.TOTAL_ALIMENTACION_CLIENTE AS 'TOTAL ALIMENTACION', C.TOTAL_SALUD_CLIENTE AS 'TOTAL SALUD', \n" +
                            "C.TOTAL_EDUCACION_CLIENTE AS 'TOTAL EDUCACION', C.TOTAL_VIVIENDA_CLIENTE AS 'TOTAL VIVIENDA', \n" +
                            "C.TOTAL_OTROS_CLIENTE AS 'TOTAL OTROS' FROM PROVEEDOR P INNER JOIN FACTURA C \n" +
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
    
    public void totalFacturasPorClienteYAnio(DefaultTableModel tableModel, String anio, String nombreCliente) throws SQLException{
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sql = "SELECT P.NOMBRE_PROVEEDOR AS 'NOMBRE DE PROVEEDOR', C.CODIGO_FACTURA AS 'CODIGO', C.FECHA AS 'FECHA', C.IVA AS 'IVA', \n" +
                            "C.TOTAL_CON_IVA AS 'TOTAL', C.TOTAL_VESTIMENTA_CLIENTE AS 'TOTAL VESTIMENTA', \n" +
                            "C.TOTAL_ALIMENTACION_CLIENTE AS 'TOTAL ALIMENTACION', C.TOTAL_SALUD_CLIENTE AS 'TOTAL SALUD', \n" +
                            "C.TOTAL_EDUCACION_CLIENTE AS 'TOTAL EDUCACION', C.TOTAL_VIVIENDA_CLIENTE AS 'TOTAL VIVIENDA', \n" +
                            "C.TOTAL_OTROS_CLIENTE AS 'TOTAL OTROS' FROM PROVEEDOR P INNER JOIN FACTURA C \n" +
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
                //if(resultado != null){
                if(resultado.next()){
                    JOptionPane.showMessageDialog(null, "PRODUCTO YA SE ENCUENTRA REGISTRADO");
                    //funcion para ingresar en tabla contiene 
                    this.guardarEnContieneFacturaPersonal((String)resultado.getObject(1), codigoFactura, prod.getCantidad());
                }
                else{
                //JOptionPane.showMessageDialog(null, "no existe proveedor, se creara uno nuevo");
                insertar("INSERT INTO PROVEEDOR (RUC_PROVEEDOR, NOMBRE_PROVEEDOR, CIUDAD_PROVEEDOR, DIRECCION_PROVEEDOR, TIPO_GASTO) VALUES('" + 
                        proveedor.getRuc() + "','" + 
                        proveedor.getNombre() + "','" + 
                        proveedor.getCiudad() + "','" + 
                        proveedor.getDireccion() +  "','" + 
                        proveedor.getCiudad() +"')");
                }
                resultado.close();
            } catch (SQLException ex) {
                //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void guardarEnContieneFacturaPersonal(String idProducto, String codigoFactura, double cantidad){
            ResultSet resultado = null;
            try {
                resultado = consultar("SELECT ID_FACTURA FROM FACTURA WHERE\n" +
                        "CODIGO_FACTURA = '" + codigoFactura + "'");
                //if(resultado != null){
                if(resultado.next()){
                    //JOptionPane.showMessageDialog(null, "PRODUCTO YA SE ENCUENTRA REGISTRADO");
                    insertar("INSERT INTO CONTIENE (ID_FACTURA, ID_PRODUCTO, CANTIDAD) VALUES ('" + 
                        (String)resultado.getObject(1) + "','" + 
                        idProducto + "','" + 
                        String.valueOf(cantidad) +"')");
                    
                    resultado.close();
                }
                else{
                //JOptionPane.showMessageDialog(null, "no existe proveedor, se creara uno nuevo");
                }
            } catch (SQLException ex) {
                //Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    /*public void crearNuevoGastoNegocio(String idCliente, String nombreGastoExtra, String fecha){
        fecha = fecha.substring(6);
        //JOptionPane.showMessageDialog(null, fecha);
        //JOptionPane.showMessageDialog(null, "llego a metodo guardar GASTOS....");
        insertar("INSERT INTO GASTOS (ID_CLIENTE, TOTAL_ALIMENTACION_CLIENTE, TOTAL_VESTIMENTA_CLIENTE, "
                + "TOTAL_VIVIENDA_CLIENTE, TOTAL_SALUD_CLIENTE, TOTAL_EDUCACION_CLIENTE, "
                + "TOTAL_OTROS_CLIENTE, ANIO_GASTOS, TOTAL_FACTURAS) VALUES('" + idCliente + "','" + 
                "0,00','0,00','0,00','0,00','0,00','0,00','" + fecha + "','0')");
    }*/
    
}
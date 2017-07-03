/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

//import Objetos.Persona;
import Pojos.Cliente;
import Pojos.Factura;
import Pojos.Proveedor;
import java.awt.Choice;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                JOptionPane.showMessageDialog(null, ""+e.getMessage());
            }
        return resultado;
    }
    
    public void guardarFactura(Factura factura){
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
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    }
    
    public void guardarCliente(Cliente cliente){
        JOptionPane.showMessageDialog(null, "llego a metodo guardar cliente....");
        ResultSet resultado = null;
        resultado = consultar("SELECT ID_CLIENTE FROM CLIENTE WHERE\n" +
                    "RUC_CI_CLIENTE = '"+ cliente.getRucCi()/* + 
                    "' AND NOMBRES_CLIENTE = '"+cliente.getNombres()*/+"'");
        try {
            //if(resultado != null){
            if(resultado.next()){
                JOptionPane.showMessageDialog(null, "CLIENTE YA SE ENCUENTRA REGISTRADO");
            }
            else{
                JOptionPane.showMessageDialog(null, "no existe cliente, se ingresara uno nuevo....");
                insertar("INSERT INTO CLIENTE (RUC_CI_CLIENTE, NOMBRES_CLIENTE) VALUES('"+cliente.getRucCi()
                        +"','"+cliente.getNombres()+"')");
                ResultSet resultadoIdcliente = consultar("SELECT ID_CLIENTE FROM CLIENTE WHERE RUC_CI_CLIENTE = '"+cliente.getRucCi()+/*"' AND NOMBRES_CLIENTE = '"+cliente.getNombres()+*/"'");
                String idCliente = "";
                try {
                    while(resultadoIdcliente.next()){
                        idCliente = resultadoIdcliente.getObject(1).toString();
                        JOptionPane.showMessageDialog(null, "id de cliente nuevo...." + idCliente);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                guardarGastos(idCliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sumarGastos(String idCliente){
        
    }
    
    public void guardarGastos(String idCliente){
        JOptionPane.showMessageDialog(null, "llego a metodo guardar GASTOS....");
        insertar("INSERT INTO GASTOS (ID_CLIENTE, TOTAL_ALIMENTACION_CLIENTE, TOTAL_VESTIMENTA_CLIENTE, "
                + "TOTAL_VIVIENDA_CLIENTE, TOTAL_SALUD_CLIENTE, TOTAL_EDUCACION_CLIENTE, "
                + "TOTAL_OTROS_CLIENTE) VALUES('" + idCliente + "','" + 
                "0,00','0,00','0,00','0,00','0,00','0,00')");
    }
    
    public void guardarProveedor(Proveedor proveedor){
        JOptionPane.showMessageDialog(null, "llego a metodo proveedor....");
        ResultSet resultado = null;
        resultado = consultar("SELECT ID_PROVEEDOR FROM PROVEEDOR WHERE\n" +
                    "RUC_PROVEEDOR = '" + proveedor.getRuc() + 
                    "' AND NOMBRE_PROVEEDOR = '" + proveedor.getNombre()+"'");
        try {
            //if(resultado != null){
            if(resultado.next()){
                JOptionPane.showMessageDialog(null, "PROVEEDOR YA SE ENCUENTRA REGISTRADO");
            }
            else{
            JOptionPane.showMessageDialog(null, "no existe proveedor, se creara uno nuevo");
            insertar("INSERT INTO PROVEEDOR (RUC_PROVEEDOR, NOMBRE_PROVEEDOR, CIUDAD_PROVEEDOR, DIRECCION_PROVEEDOR) VALUES('" + 
                    proveedor.getRuc() + "','" + 
                    proveedor.getNombre() + "','" + 
                    proveedor.getCiudad() + "','" + 
                    proveedor.getDireccion() +"')");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void totalProveedores(Choice choiceProveedores){
        ResultSet resultado = null;
        //tableModel.setRowCount(0);
        //tableModel.setColumnCount(0);
        String sql = "select * from Proveedor";
        try {
            
            resultado = consultar(sql);
            
            if(resultado != null){
                /*int numeroColumna = resultado.getMetaData().getColumnCount();
                for(int j = 1;j <= numeroColumna;j++){
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }*/
                while(resultado.next()){
                    //Object []objetos = new Object[1];
                    //for(int i = 1;i <= 1;i++){
                        //objetos[i-1] = resultado.getObject(i);
                        choiceProveedores.addItem((String) resultado.getObject(2));
                      
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
    
    public void totalFacturas(DefaultTableModel tableModel, String nombreProveedor) throws SQLException{
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        //select rucProv from Proveedor where nombreProv = 'MEGA SANTAMARIA S.A.'
        String sqlRucProveedor = "select rucProv from Proveedor where nombreProv = '" + nombreProveedor + "'";
        ResultSet resultadoRuc = null;
        resultadoRuc = consultar(sqlRucProveedor);
        String rucProveedor = ((String) resultadoRuc.getObject(1));
        
        
        String sql = "select codigoFactura AS \"CODIGO\", fechaEmision AS \"FECHA\", totalVestimenta AS \"VESTIMENTA\", totalAlimentacion AS \"ALIMENTACION\", totalSalud AS \"SALUD\", totalEducacion AS \"EDUCACION\", totalVivienda AS \"VIVIENDA\", totalOtros AS \"OTROS\", totalConIva AS \"TOTAL\" from Factura where rucProveedor = '" + rucProveedor + "'";
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
    
}
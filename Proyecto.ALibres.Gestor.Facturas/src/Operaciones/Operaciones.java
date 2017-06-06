/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

//import Objetos.Persona;
import Pojos.Cliente;
import Pojos.Factura;
import Pojos.Proveedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Operaciones extends Conexion{
    /**
     * Constructor for objects of class Operaciones
     */
    public Operaciones()
    {
        // initialise instance variables
    }
    
    public boolean insertar(String sql){
        boolean valor = true;
        conectar();
        try {
            consulta.executeUpdate(sql);
        } catch (SQLException e) {
                valor = false;
                JOptionPane.showMessageDialog(null, e.getMessage());
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

    /*public void guardarUsuario(Persona persona){
        insertar("insert into Persona values("+persona.getId()
                    +",'"+persona.getPrimer_nombre()
                    +"','"+persona.getSegundo_nombre()
                    +"','"+persona.getPrimer_apellido()
                    +"','"+persona.getSegundo_apellido()+"')");
    }*/
    public void guardarFactura(Factura factura){
        insertar("insert into Factura values("+factura.getCodigo()
                    +",'"+ factura.getRucCliente()
                    +"','"+factura.getRucProveedor()
                    +"','"+factura.getFecha()
                    +"','"+factura.getListaGastos().get(0).getTotalSinIva()
                    +"','"+factura.getListaGastos().get(1).getTotalSinIva()
                    +"','"+factura.getListaGastos().get(2).getTotalSinIva()
                    +"','"+factura.getListaGastos().get(3).getTotalSinIva()
                    +"','"+factura.getListaGastos().get(4).getTotalSinIva()
                    +"','"+factura.getListaGastos().get(5).getTotalSinIva()
                    +"','"+factura.getTotalSinIva()
                    +"','"+factura.getTotalConIva()+"')");
    }
    
    public void guardarCliente(Cliente cliente){
        insertar("insert into Cliente values('"+cliente.getRucCi()
                    +"','"+cliente.getNombres()+"')");
    }
    
    public void guardarProveedor(Proveedor proveedor){
        insertar("insert into Proveedor values('"+proveedor.getRuc()
                    +"','"+ proveedor.getNombre()
                    +"','"+proveedor.getCiudad()
                    +"','"+proveedor.getDireccion()+"')");
    }
  
    public void totalPersonas(DefaultTableModel tableModel){
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sql = "select * from Persona";
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
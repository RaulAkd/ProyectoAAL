/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author por-tosh
 */
public class Factura {
    DecimalFormat df = new DecimalFormat("#.##");
    private String codigo;
    private String fecha;
    private String rucCliente;
    private String rucProveedor;
    //private Date fechaDate;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DecimalFormat getDf() {
        return df;
    }

    public void setDf(DecimalFormat df) {
        this.df = df;
    }

    public String getRucCliente() {
        return rucCliente;
    }

    public void setRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
    }

    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }
    private double totalSinIva;
    private double totalConIva;
    private double iva;
    private ArrayList<Gasto> listaGastos;
    private ArrayList<Producto> listaProductos;

    public Factura(String codigo, String fecha, double totalSinIva, double totalConIva, double iva) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.totalSinIva = totalSinIva;
        this.totalConIva = totalConIva;
        this.iva = iva;
    }

    public Factura() {
        this.listaProductos = new ArrayList<Producto>();
        this.listaGastos = new ArrayList<Gasto>();
        this.listaGastos.add(new Gasto("vestimenta"));
        this.listaGastos.add(new Gasto("alimentacion"));
        this.listaGastos.add(new Gasto("salud"));
        this.listaGastos.add(new Gasto("educacion"));
        this.listaGastos.add(new Gasto("vivienda"));
        this.listaGastos.add(new Gasto("otros"));
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotalSinIva() {
        return totalSinIva;
    }

    public void setTotalSinIva(double totalSinIva) {
        this.totalSinIva = totalSinIva;
    }

    public double getTotalConIva() {
        return totalConIva;
    }

    public void setTotalConIva(double totalConIva) {
        this.totalConIva = totalConIva;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public ArrayList<Gasto> getListaGastos() {
        return listaGastos;
    }

    public void setListaGastos(ArrayList<Gasto> listaGastos) {
        this.listaGastos = listaGastos;
    }
    
    public void setProducto(Producto prod){
        this.listaProductos.add(prod);
    }
    
    public void calcularIva(){
        this.iva = this.totalConIva - this.totalSinIva;
    }
    
    public String listaToString(){
        String salida = "";
        for(Producto prod :  this.listaProductos){
            salida = salida + prod.toString() + "\n";
        }
        return salida;
    }

    @Override
    public String toString() {
        return "Factura{" + "codigo=" + codigo + ", fecha=" + fecha + ", totalSinIva=" + totalSinIva + ", totalConIva=" + totalConIva + ", iva=" + iva + ", listaGastos=" + listaGastos + '}';
    }
    
    public void clasificar(){
        //Declaracion de String, Expresion Regular
            String inicioER = ".*(?i)(";
            String vestimentaER = inicioER, alimentacionER = inicioER,
                    saludER = inicioER, educacionER = inicioER, 
                    viviendaER = inicioER, TempER = inicioER;
        try {
            FileReader fileEntrada = new FileReader("src\\ArchivosLecturaAuxiliar\\ClasificacionGastos.txt");
            BufferedReader bufferEntrada = new BufferedReader(fileEntrada);
            String cadena, listaGastos[], nombreGasto;
            
            //Construccion de expresion regular
            //System.out.println("entra2");
            while((cadena = bufferEntrada.readLine())!=null) {
                nombreGasto = cadena.split(":")[0];
                listaGastos = cadena.split(":")[1].split(",");
                for(String productoStr : listaGastos){
                    TempER += "(" + productoStr + ")|";
                }
                TempER = TempER.substring(0, TempER.length()-1) + ").*";
                
                if(nombreGasto.compareToIgnoreCase("vestimenta") == 0)      vestimentaER = TempER;
                if(nombreGasto.compareToIgnoreCase("alimentacion") == 0)    alimentacionER = TempER;
                if(nombreGasto.compareToIgnoreCase("salud") == 0)           saludER = TempER;
                if(nombreGasto.compareToIgnoreCase("educacion") == 0)       educacionER = TempER;
                if(nombreGasto.compareToIgnoreCase("vivienda") == 0)        viviendaER = TempER;
                
                TempER = inicioER;
            }
            
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("no hay archivo");
        } catch (IOException ex) {
            //Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Pattern patVestimenta = Pattern.compile(".*vest.*|.*pant.*|.*conjunto.* | .*blusa.* |"+ " .*medias.* | .*zapat.* | .*pant.* | .*cami.* | .*mochi.* | .*buso.* | .*gorra.* | .*CONJUNTO.* | .*BLUSA.* | .*MEDIAS.* | .*ZAPAT.* | .*PANT.* | .*CAMI.* | .*MOCHI.* | .*BUSO.* | .*GORRA.*");
        Pattern patVestimenta = Pattern.compile(vestimentaER);
        //Pattern patAlimentacion = Pattern.compile(".*huevo.*|.*carne.*|.*aceite.*|.*arroz.*|.*leche.*|.*enlatad.*|.*fruta.*|"+ ".*sal.* | .*ques.* | .*azu.* | .*ari.* | .*past.* | .*caf.* | .*agua.* | .*carne.* | .*pollo.* | .*pan.* | .tomate.* | .*cebolla.* | .*natura.* | .*ajo.* | .*naranja.* | .*limon.* | .*aguacate.* | .*papa.* | .*atun.* | .*enlatado.* | .*lechuga.* | .*broco.* | .*bot.* | .*SAL.* | .*QUES.* | .*AZU.* | .*ARI.* | .*PAST.* | .*CAF.* | .*AGUA.* | .*CARNE.* | .*RES.* | .*POLLO.* | .*PAN.* | .TOMATE.* | .*CEBOLLA.* | .*NATURA.* | .*AJO.* | .*NARANJA.* | .*LIMON.* | .*AGUACATE.* | .*PAPA.* | .*ATUN.* | .*ENLATADO.* | .*LECHUGA.* | .*BROCO.* | .*BOT.*");
        Pattern patAlimentacion = Pattern.compile(alimentacionER);
        //Pattern patSalud = Pattern.compile(".*medicina.*|.*seguro.*|.*protesis.*|.*medico.*|.*lentes.*|.*tabletas.* |"+ " .*aspirina.* | .*capsulas.* | .*ampollas.* | .*past.* | .*paract.* | .*vitam.* | .*algodon.* | .*alcohol.* | .*bandas.* | .*gasa.* | .*vendas.* | .*inmovilizador.* | .*TABLETAS.* | .*ASPIRINA.* | .*CAPSULAS.* | .*AMPOLLAS.* | .*PAST.* | .*PARACT.* | .*VITAM.* | .*ALGODON.* | .*ALCOHOL.* | .*BANDAS.* | .*GASA.* | .*VENDAS.* | .*INMOVILIZADOR.*");
        Pattern patSalud = Pattern.compile(saludER);
        //Pattern patEducacion = Pattern.compile(".*matricula.*|.*uniforme.*|.*escol.*|.*didactico.*|.*libro.*|.*cuaderno.*|"+ ".*cuaderno.* | .*lapiz.* | .*esfero.* | .*libro.* | .*borrador.* | .*juego.* | .*jgo.* | .*hojas.* | .*dicc.* | .*pliego.* | .*pintura.* | .*A4.* | .*adhesiv.* | .*didactico.* | .*usb.* | .*laptop.* | .*CUADERNO.* | .*LAPIZ.* | .*ESFERO.* | .*LIBRO.* | .*BORRADOR.* | .*JUEGO.* | .*JGO.* | .*HOJAS.* | .*DICC.* | .*PLIEGO.* | .*PINTURA.* | .*A4.* | .*ADHESI.* | .*DIDACTICO.* | .*USB.* | .*LAPTOP.*");
        Pattern patEducacion = Pattern.compile(educacionER);
        //Pattern patVivienda = Pattern.compile(".*arriendo.*|.*construcci.*|."+ "*electricidad.*|.*alicuota.*|.*llamada.*|.*cemento.*|.*CARBONATO.*|.*ESPESANTE.*|.*RODILLO.*|.*ESPUMA.*");
        Pattern patVivienda = Pattern.compile(viviendaER);
        //Pattern patOtros = Pattern.compile(".*vest.*|.*pant.*");
        Matcher matVestimenta;// = patVestimenta.matcher(cadena);
        Matcher matAlimentacion;
        Matcher matSalud;
        Matcher matEducacion;
        Matcher matVivienda;
        Matcher matOtros;
        for(Producto prod :  this.listaProductos){
            matVestimenta = patVestimenta.matcher(prod.getNombre());
            matAlimentacion = patAlimentacion.matcher(prod.getNombre());
            matSalud = patSalud.matcher(prod.getNombre());
            matEducacion = patEducacion.matcher(prod.getNombre());
            matVivienda = patVivienda.matcher(prod.getNombre());
            
            if(matVestimenta.matches()){
                this.listaGastos.get(0).sumarGasto(prod.getValorTotal());
                prod.setTipo("vestimenta");
            }
            
            else if(matAlimentacion.matches()){
                this.listaGastos.get(1).sumarGasto(prod.getValorTotal());
                prod.setTipo("alimentacion");
            }
            
            else if(matSalud.matches()){
                this.listaGastos.get(2).sumarGasto(prod.getValorTotal());
                prod.setTipo("salud");
            }
            
            else if(matEducacion.matches()){
                this.listaGastos.get(3).sumarGasto(prod.getValorTotal());
                prod.setTipo("educacion");
            }
            
            else if(matVivienda.matches()){
                this.listaGastos.get(4).sumarGasto(prod.getValorTotal());
                prod.setTipo("vivienda");
                //System.out.print("llega");
            }
            
            else{
                this.listaGastos.get(5).sumarGasto(prod.getValorTotal());
                prod.setTipo("otros");
            }
        }
    }
    
    public void clasificarGastosNegocio(){
        
    }
    
    public void actualizarValores(){
        
        for(Gasto gasto : this.listaGastos){
            gasto.reiniciarGasto();
        }
        
        for(Producto prod :  this.listaProductos){
            if(prod.getTipo().compareToIgnoreCase("vestimenta") == 0){
                this.listaGastos.get(0).sumarGasto(prod.getValorTotal());
            }
            
            else if(prod.getTipo().compareToIgnoreCase("alimentacion") == 0){
                this.listaGastos.get(1).sumarGasto(prod.getValorTotal());
            }
            
            else if(prod.getTipo().compareToIgnoreCase("salud") == 0){
                this.listaGastos.get(2).sumarGasto(prod.getValorTotal());
            }
            
            else if(prod.getTipo().compareToIgnoreCase("educacion") == 0){
                this.listaGastos.get(3).sumarGasto(prod.getValorTotal());
            }
            
            else if(prod.getTipo().compareToIgnoreCase("vivienda") == 0){
                this.listaGastos.get(4).sumarGasto(prod.getValorTotal());
            }
            
            else{
                this.listaGastos.get(5).sumarGasto(prod.getValorTotal());
            }
        }
    }
}

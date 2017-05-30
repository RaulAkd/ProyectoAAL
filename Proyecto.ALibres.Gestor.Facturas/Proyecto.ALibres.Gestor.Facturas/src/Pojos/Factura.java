/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author por-tosh
 */
public class Factura {
    private String codigo;
    private String fecha;
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
    
    
}

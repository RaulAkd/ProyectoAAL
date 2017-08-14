/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

/**
 *
 * @author por-tosh
 */
public class Proveedor {
    private String nombre;
    private String ruc;
    private String direccion;
    private String ciudad;
    private String tipoGastoPersonal;
    private String tipoGastoNegocio;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Proveedor() {
    }

    public String getTipoGastoPersonal() {
        return tipoGastoPersonal;
    }

    public void setTipoGastoPersonal(String tipoGastoPersonal) {
        this.tipoGastoPersonal = tipoGastoPersonal;
    }

    public String getTipoGastoNegocio() {
        return tipoGastoNegocio;
    }

    public void setTipoGastoNegocio(String tipoGastoNegocio) {
        this.tipoGastoNegocio = tipoGastoNegocio;
    }
    
    public Proveedor(String nombre, String ruc, String direccion, String ciudad) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }*/

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "nombre=" + nombre + ", ruc=" + ruc + ", direccion=" + direccion + ", ciudad=" + ciudad + ", tipoGastoPersonal=" + tipoGastoPersonal + ", tipoGastoNegocio=" + tipoGastoNegocio + '}';
    }

    
    
}

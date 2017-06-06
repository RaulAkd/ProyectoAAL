/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.text.DecimalFormat;

/**
 *
 * @author por-tosh
 */
public class Gasto {
    DecimalFormat df = new DecimalFormat("#.##");
    //txfIva.setText(String.valueOf(df.format(objFactura.getDblTotalFac()-objFactura.getDblSubtotal0Fac())));
    private String tipo;
    private double totalConIva;
    private double totalSinIva;

    public Gasto(String tipo) {
        this.tipo = tipo;
        this.totalConIva = 0.00;
        this.totalSinIva = 0.00;
    }
    
    public void sumarGasto(double valor){
        this.totalSinIva = this.totalSinIva + valor;
    }

    public DecimalFormat getDf() {
        return df;
    }

    public void setDf(DecimalFormat df) {
        this.df = df;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getTotalConIva() {
        return totalConIva;
    }

    public void setTotalConIva(double totalConIva) {
        this.totalConIva = totalConIva;
    }

    public double getTotalSinIva() {
        return totalSinIva;
    }

    public void setTotalSinIva(double totalSinIva) {
        this.totalSinIva = totalSinIva;
    }
    
    
}

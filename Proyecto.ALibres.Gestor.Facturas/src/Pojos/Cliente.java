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
public class Cliente {
    private String rucCi;
    private String nombres;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

    public String getRucCi() {
        return rucCi;
    }

    public void setRucCi(String rucCi) {
        this.rucCi = rucCi;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Override
    public String toString() {
        return "Cliente{" + "rucCi=" + rucCi + ", nombres=" + nombres + '}';
    }
    
    
    
}

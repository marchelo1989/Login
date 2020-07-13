/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Login.ENT;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author march
 */
public class ClRegistroPc implements Serializable{
    private int idRegistroPc;
    private String keyPC;
    private String keyActivacion;
    private Date fechaTermino;
    private boolean activo;

    public int getIdRegistroPc() {
        return idRegistroPc;
    }

    public void setIdRegistroPc(int idRegistroPc) {
        this.idRegistroPc = idRegistroPc;
    }

    public String getKeyPC() {
        return keyPC;
    }

    public void setKeyPC(String keyPC) {
        this.keyPC = keyPC;
    }

    public String getKeyActivacion() {
        return keyActivacion;
    }

    public void setKeyActivacion(String keyActivacion) {
        this.keyActivacion = keyActivacion;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    //Registro de nuevo pc
    public ClRegistroPc(String keyPC, String keyActivacion) {
        this.keyPC = keyPC;
        this.keyActivacion = keyActivacion;
    }

    public ClRegistroPc(String keyActivacion, Date fechaTermino, boolean activo) {
        this.keyActivacion = keyActivacion;
        this.fechaTermino = fechaTermino;
        this.activo = activo;
    }
    public ClRegistroPc(String keyPC, String keyActivacion, Date fechaTermino, boolean activo) {
        this.keyPC = keyPC;
        this.keyActivacion = keyActivacion;
        this.fechaTermino = fechaTermino;
        this.activo = activo;
    }

    public ClRegistroPc(String keyPC) {
        this.keyPC = keyPC;
    }
    
    @Override
    public String toString() {
        return "ClRegistroPc{" + "keyPC=" + keyPC + ", keyActivacion=" + keyActivacion + ", fechaTermino=" + fechaTermino + ", activo=" + activo + '}';
    }
    
    
    
}

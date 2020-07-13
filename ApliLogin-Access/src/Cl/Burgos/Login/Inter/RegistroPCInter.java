/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Login.Inter;

import Cl.Burgos.Login.ENT.ClRegistroPc;
import java.util.Date;

/**
 *
 * @author march
 */
public interface RegistroPCInter {
    //Validar el numero de placa del pc si esta registrado o no
    public boolean sqlValidarClavePC(String key);
    //Validar si el pc esta activado para el uso
    public boolean sqlValidarActivoPC(String  key);
    //Registrar nuevo pc 
    public boolean sqlInsertarPC(ClRegistroPc clRegistroPc);
    //Activar el nuveo pc
    public boolean sqlActivarPC(ClRegistroPc clRegistroPc);
    //Validar la fecha para el uso del programa
    public Date sqlValidarFechaPC(String  key);
}

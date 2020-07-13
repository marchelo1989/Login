/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Login.Inter;

import Cl.Burgos.Login.ENT.ClRegistroPc;

/**
 *
 * @author march
 */
public interface RegistroPCInter {
    //Validar el numero de placa del pc si esta registrado o no
    public boolean sqlValidarClavePC(String key);
    //Registrar nuevo pc 
    public boolean sqlInsertarPC(ClRegistroPc clRegistroPc);
    //Activar el nuveo pc
    public boolean sqlActivarPC(ClRegistroPc clRegistroPc);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Login.Main;

import Cl.Burgos.Login.Conf.Confi;
import Cl.Burgos.Login.DAO.DAORegistroPC;
import Cl.Burgos.Login.ENT.ClRegistroPc;
import Cl.Burgos.Login.FUN.ComandosCMD;
import Cl.Burgos.Login.FUN.Directorio;
import Cl.Burgos.Login.FUN.EnviarMail;
import Cl.Burgos.Login.FUN.MetodoBase64E;
import Cl.Burgos.Login.FUN.PasswordGenerator;
import Cl.Burgos.Login.FUN.ValidarPC;
import Cl.Burgos.Login.GUI.FrLogin;
import java.io.File;
import javax.swing.JOptionPane;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author march
 */
public class ApliLogin {

    static String keypc="";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File log4jfile = new File(Confi.userProgra+"/Log4j.properties");
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());
    
        Directorio.crearDirecPre();
        Directorio.crearDirecSec();
        ValidarPC validarPC = new ValidarPC();
        validarPC.validarRegistropc();
        
    }
}

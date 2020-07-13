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
import Cl.Burgos.Login.GUI.FrLogin;
import Cl.Burgos.Login.GUI.RegistroPC.FrEnviarCorreo;
import Cl.Burgos.Login.GUI.RegistroPC.FrRegistroPC;
import java.io.File;
import javax.swing.JOptionPane;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author march
 */
public class ApliLogin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File log4jfile = new File(Confi.userProgra+"/Log4j.properties");
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());
    
        Directorio.crearDirecPre();
        Directorio.crearDirecSec();
        
        String numSerie = "cmd /c wmic bios get serialnumber";
        String numSerie2 = "wmic path win32_computersystemproduct get uuid";
        String ns = ComandosCMD.cmd(numSerie);
        ns = ns.replaceAll("\\s*$","");
        if(ns.equals("To be filled by O.E.M.")){
            ns = ComandosCMD.cmd(numSerie2);
        }
        String key = MetodoBase64E.cifrarBase64(generarPASS());
        System.out.println(MetodoBase64E.descifrarBase64(key));
        DAORegistroPC dAORegistroPC = new DAORegistroPC();
        
        //Busca el registro del pc
        if(dAORegistroPC.sqlValidarClavePC(ns)){
            JOptionPane.showMessageDialog(null,"Pc ya Registrado");
            //Validar si esta activo el pc
            if(dAORegistroPC.sqlValidarActivoPC(ns)){
                JOptionPane.showMessageDialog(null,"Pc Activado");
                FrLogin frLogin = new FrLogin();
                frLogin.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"Pc No Activado");
                FrRegistroPC frRegistroPC = new FrRegistroPC();
                frRegistroPC.setVisible(true);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Pc no Registrado");
            ClRegistroPc clRegistroPc = new ClRegistroPc(ns, key);
            //RegistrarPC
            if(dAORegistroPC.sqlInsertarPC(clRegistroPc)){
                JOptionPane.showMessageDialog(null,"Pc Registrado");
                EnviarMail enviarMail = new EnviarMail();
                enviarMail.enviarCorreo("marchelo.1989@live.cl", "Activacion del pc", "La Calve de Activacion es: "+key+" Para el Pc:"+ns);
//                FrEnviarCorreo correo = new FrEnviarCorreo();
//                correo.setVisible(true);
                
            }else{
                JOptionPane.showMessageDialog(null,"Error No se pudo Registrar el PC");
            }
        }
        
//        //Busca el registro del pc
//        if(!dAORegistroPC.sqlValidarClavePC(ns)){
//            JOptionPane.showMessageDialog(null,"Pc ya Registrado");
//            //Busca si el pc esta activo
//            if(!dAORegistroPC.sqlValidarActivoPC(key)){
//               FrRegistroPC frRegistroPC = new FrRegistroPC();
//               frRegistroPC.setVisible(true); 
//            }else{
//                FrLogin frLogin = new FrLogin();
//                frLogin.setVisible(true);
//            }
//            
//        }
//        else{
//            JOptionPane.showMessageDialog(null,"Pc no Registrado");
//            
//            ClRegistroPc clRegistroPc = new ClRegistroPc(ns, key);
//            if(dAORegistroPC.sqlInsertarPC(clRegistroPc)){
//                JOptionPane.showMessageDialog(null,"Pc Registrado");
//                FrEnviarCorreo correo = new FrEnviarCorreo();
////                correo.setVisible(true);
//                
//            }else{
//                JOptionPane.showMessageDialog(null,"Error Contactar con el Administrador");
//            }
//        }
//        java.util.Date date = new java.util.Date();
//        ClRegistroPc clRegistroPc = new ClRegistroPc(MetodoBase64E.descifrarBase64(key), date,true);
//        if(!dAORegistroPC.sqlActivarPC(clRegistroPc)){
//            JOptionPane.showMessageDialog(null,"Pc ACtivado");
//        }else{
//            JOptionPane.showMessageDialog(null,"Error Contactar con el Administrador");
//        }
    }
    public static String generarPASS(){
        String Pass =PasswordGenerator.getPassword(
                PasswordGenerator.NUMEROS+
		PasswordGenerator.MINUSCULAS+
		PasswordGenerator.MAYUSCULAS+
		PasswordGenerator.ESPECIALES,20);
        return Pass;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Login.FUN;

import static Cl.Burgos.Login.Conf.Confi.userProgra;
import Cl.Burgos.Login.DAO.DAORegistroPC;
import Cl.Burgos.Login.ENT.ClRegistroPc;
import Cl.Burgos.Login.GUI.FrLogin;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author march
 */
public class ValidarPC {
    
    public static String keypc;
    public static Date d;
    public void validarRegistropc(){
        String numSerie = "cmd /c wmic bios get serialnumber";
        String numSerie2 = "wmic path win32_computersystemproduct get uuid";
        String ns = ComandosCMD.cmd(numSerie);
        ns = ns.replaceAll("\\s*$","");
        if(ns.equals("To be filled by O.E.M.")){
            keypc = ComandosCMD.cmd(numSerie2);
        }else{
            keypc = ns;
        }
        String key = generarPASS();
        DAORegistroPC dAORegistroPC = new DAORegistroPC();
        
        //Busca el registro del pc
        if(dAORegistroPC.sqlValidarClavePC(keypc)){
            JOptionPane.showMessageDialog(null,"Pc ya Registrado");
            //Validar si esta activo el pc
            if(dAORegistroPC.sqlValidarActivoPC(keypc)){
                JOptionPane.showMessageDialog(null,"Pc Activado");
                validarfecha();
            }else{
                JOptionPane.showMessageDialog(null,"Pc No Activado");
                validarActivoPC();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Pc no Registrado");
            ClRegistroPc clRegistroPc = new ClRegistroPc(keypc, key);
            //RegistrarPC
            if(dAORegistroPC.sqlInsertarPC(clRegistroPc)){
                JOptionPane.showMessageDialog(null,"Pc Registrado \nEspere que se Cree el Archivo");
                crearArchivoActivacion(keypc, key);
//                JOptionPane.showMessageDialog(null,"Pc Registrado \nEspere que se Envie el correo");
//                EnviarMail enviarMail = new EnviarMail();
//                enviarMail.enviarCorreo("marchelo.1989@live.cl", "Activacion del pc", "Pc: "+keypc+" \nLa Clave de Activacion es: "+key);
                
            }else{
                JOptionPane.showMessageDialog(null,"Error No se pudo Registrar el PC");
            }
        }
    }
    public void validarActivoPC(){
        try {
            DAORegistroPC dAORegistroPC = new DAORegistroPC();
            java.util.Date date = new java.util.Date();
            ClRegistroPc clRegistroPc = leerArchivo();
        //        ClRegistroPc clRegistroPc = new ClRegistroPc(txtCodigo.getText(), date,true);
        if(dAORegistroPC.sqlActivarPC(clRegistroPc)){
            JOptionPane.showMessageDialog(null,"Pc Activado");
            eliminarFichero();
            validarfecha();
        }else{
            JOptionPane.showMessageDialog(null,"Error Activar Contactar con el Administrador");
        }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"El Archivo no encontrado");
            System.exit(0);
//            Logger.getLogger(ValidarPC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            System.out.println("2");
            Logger.getLogger(ValidarPC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void crearArchivoActivacion(String keyPC,String key){
        String url="Act.obj";
        String msg=keyPC+";"+key;
        msg=MetodoBase64E.cifrarBase64(msg);
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(url, true)))) {
            out.println("1");
            out.println(msg);
            
            JOptionPane.showMessageDialog(null,"Archivo Listo \nPara Mandarlo al Administrador");
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
            JOptionPane.showMessageDialog(null,"Hubo un error"+e);
        }
    }
    public ClRegistroPc leerArchivo() throws IOException, ParseException{
        String ficher=userProgra+"/Activacion.obj";
        File archivo = new File (ficher);
//        File archivo = new File ("E:/informacion.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String cadena = br.readLine();
        int filas=Integer.parseInt(cadena);
        String[] alinea=new String[filas];
        String separador = ";";
        
        String keyPC,keyActi,d,acti;
        ClRegistroPc r = null;
        
        for (int i = 0; i < alinea.length; i++) {
            cadena=br.readLine();
            alinea[i]=cadena;
        }
        for (int i = 0; i < alinea.length; i++) {
            String cade=alinea[i];
            cade=MetodoBase64E.descifrarBase64(cade);
            StringTokenizer st=new StringTokenizer(cade, separador);
            while (st.hasMoreTokens()) {
                keyPC=st.nextToken();
                keyActi=st.nextToken();
                d=st.nextToken();
                d=d.substring(0, d.indexOf('.'));
                acti=st.nextToken();
                
                if(acti.equals("true")){
                    r = new ClRegistroPc(keyPC, keyActi, FormatoFecha.mostrarFechaYMDHMS(d), true);
                }else{
                    r = new ClRegistroPc(keyPC, keyActi, FormatoFecha.mostrarFechaYMDHMS(d), false);
                }
              
            }     
        }      
        return r;
    }
    public void validarfecha(){
        DAORegistroPC dAORegistroPC = new DAORegistroPC();
        d =dAORegistroPC.sqlValidarFechaPC(ValidarPC.keypc);
        java.util.Date date = new java.util.Date();
        String keyAct = generarPASS();
        if(date.before(d)){
            JOptionPane.showMessageDialog(null,"Fecha Valida: "+d);
            FrLogin frLogin = new FrLogin();
            frLogin.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"Fecha no Valida: "+d);
            int resp = JOptionPane.showConfirmDialog(null, "¿Quiere Reactivar el Programa?", "Alerta!", JOptionPane.YES_NO_OPTION);
                if(resp==0){
                    if(dAORegistroPC.sqldesabilitarPC(ValidarPC.keypc,keyAct)){
                        crearArchivoActivacion(keypc, keyAct);
                        JOptionPane.showMessageDialog(null,"Reactivacion solicitada");
                        
                    }else{
                        JOptionPane.showMessageDialog(null,"Error en la Reactivacion solicitada");
                    }
//                    this.dispose();
                }else{
                    System.exit(0);
                }
        }
    }
    public static String generarPASS(){
        String Pass =PasswordGenerator.getPassword(
                PasswordGenerator.NUMEROS+
		PasswordGenerator.MINUSCULAS+
		PasswordGenerator.MAYUSCULAS+
		PasswordGenerator.ESPECIALES,20);
        return Pass;
    }
    
    public static void eliminarFichero() {
        String ficher=userProgra+"/Activacion.obj";
        File fichero = new File (ficher);
        if (!fichero.exists()) {
            System.out.println("El archivo data no existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo data fue eliminado.");
        }

    }
}

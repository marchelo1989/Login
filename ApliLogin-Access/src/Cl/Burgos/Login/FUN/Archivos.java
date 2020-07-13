/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Login.FUN;

import Cl.Burgos.Login.Conf.Confi;
import static Cl.Burgos.Login.Conf.Confi.userProgra;
import Cl.Burgos.Login.ENT.ClRegistroPc;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.log4j.Logger;

/**
 *
 * @author march
 */
public class Archivos {
    //Variables del Log4j
    static  Logger log =Logger.getLogger(Archivos.class);
    
    public static ClRegistroPc leerArchivo() throws Exception{
        String ficher=userProgra+"/Datos.txt";
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
    
    public static void crearArchivo(String keyPC,String keyActi,java.sql.Timestamp d,String acti){
        String url="Datos.txt";
        String msg=keyPC+";"+keyActi+";"+d+";"+acti;
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(url, true)))) {
            out.println("1");
            out.println(msg);
            
            
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
            JOptionPane.showMessageDialog(null,"Hubo un error"+e);
        }
    }
    
//    public static String selectArchivo(){
//        String url=null;
//        //Creamos el objeto JFileChooser
//        JFileChooser fileChooser = new JFileChooser();
//        //Indicamos lo que podemos seleccionar
//        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//        //Creamos el filtro
//        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo De Texto", "txt");
//        //Le indicamos el filtro
//        fileChooser.setFileFilter(filtro);
//        //Indicamos que podemos seleccionar varios ficheros
//        fileChooser.setMultiSelectionEnabled(false);
//        //Indicamos un lugar de busqueda predeterminada
//        if(SO.startsWith("Windows")){
//            fileChooser.setCurrentDirectory(new File(userDir +"/Desktop"));
//        }else{
//            fileChooser.setCurrentDirectory(new File(userDir +"/Escritorio"));
//        }
//        //Abrimos la ventana, guardamos la opcion seleccionada por el usuario
//        int aceptar=fileChooser.showOpenDialog(null);
//        //Si el usuario, pincha en aceptar
//        if(aceptar==JFileChooser.APPROVE_OPTION){
//            url = fileChooser.getSelectedFile().toString();
//        }else{
//            JOptionPane.showMessageDialog(null,"Hubo un error al seleccionar el Archivo");
//            Log.log("Hubo un error al seleccionar el Archivo");
//            log.info("Hubo un error al seleccionar el Archivo");
//        }
//        return url;
//    }
}

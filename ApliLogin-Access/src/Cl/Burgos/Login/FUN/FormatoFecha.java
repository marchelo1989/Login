/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Login.FUN;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author march
 */
public class FormatoFecha {
    public static String mostrarFecha(Date date){
        //Muestra la dia mes año
        DateFormat dateFormatFecha = new SimpleDateFormat("yyyy-MM-dd");
        String d=dateFormatFecha.format(date);
//        System.out.println("Muestra El dia-mes-año: "+d);
        return d;
    }
    public static Date mostrarFechaD(String date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(date);
        return d;
    }
    public static Date mostrarFechaYMDHMS(String date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d = sdf.parse(date);
        return d;
    }
}

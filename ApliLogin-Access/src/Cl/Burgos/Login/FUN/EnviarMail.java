/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Login.FUN;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author march
 */
public class EnviarMail {
    public void enviarCorreo(String destinatario,String asunto,String mensaje){
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");
        
        Session sesion = Session.getDefaultInstance(propiedad);
        
        String correoEnvia = "Prueba@gmail.com";
        String contrasena = "ABCabc123";
//        String destinatario = txtCorreo.getText();
//        String asunto = txtAsunto.getText();
//        String mensaje = txtMensaje.getText();
        
        
        MimeMessage mail = new MimeMessage(sesion);
        
        try {
            mail.setFrom(new InternetAddress (correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mail.setSubject(asunto);
            mail.setText(mensaje);
            
            
            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(correoEnvia,contrasena);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
            
            JOptionPane.showMessageDialog(null, "Correo enviado \nEspere el archivo de activacion");
            
            
            
            
            
        } catch (AddressException ex) {
            Log.log(ex.getMessage());
//            Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
//            Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            Log.log(ex.getMessage());
        }
    }
}

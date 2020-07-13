/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Login.GUI;

import static Cl.Burgos.Login.Conf.Confi.userProgra;
import Cl.Burgos.Login.DAO.DAOLogin;
import Cl.Burgos.Login.ENT.ClLogin;
import Cl.Burgos.Login.FUN.MetodoBase64E;
import Cl.Burgos.Login.FUN.Metodos;
import Cl.Burgos.Login.FUN.PasswordGenerator;
import Cl.Burgos.Login.FUN.Log;
import Cl.Burgos.Login.GUI.FrLogin;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author march
 */
public class FrRecuperarPass extends javax.swing.JFrame {
    
    ClLogin clLogin = FrLogin.clLogin;
    DAOLogin dAOLogin = new DAOLogin();
    /**
     * Creates new form FrRecuperarPass
     */
    public FrRecuperarPass() {
        initComponents();
        dAOLogin.sqlSearchRut(clLogin);
        
        txtRut.setText(clLogin.getRut());
        txtRut.setEditable(false);
        
        lbmPregunta.setText("¿"+clLogin.getPreguntaSecreta()+"?");
        jPanel2.setVisible(false);
        jPanel3.setVisible(false);
        
        jPanel1.setOpaque(false);
        jPanel2.setOpaque(false);
        jPanel3.setOpaque(false);
        setLocationRelativeTo(null); 
        setResizable(false); 
        setTitle("Inicio de Seccion");
        String url="/Cl/Burgos/Login/IMG/";
        setIconImage(new ImageIcon(getClass().getResource(url+"Icono.png")).getImage());
        ((JPanel)getContentPane()).setOpaque(false);
        ImageIcon MyImgCustom =new ImageIcon(this.getClass().getResource(url+"fondo2.jpg"));
        JLabel fondo= new JLabel();
        
        fondo.setIcon(MyImgCustom);
        getLayeredPane().add(fondo,JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0,0,MyImgCustom.getIconWidth(),MyImgCustom.getIconHeight());
    }

    public ClLogin consultarLogin() throws Exception{
        ClLogin login = new ClLogin(txtRut.getText(), txtCorreo.getText(), txtRespuestaSecre.getText());
        return login;
    }
    public ClLogin claveSecreta(){
        ClLogin login = new ClLogin(txtClaveSecre.getText());
        return login;
    }
    
    public String generarPASS(){
        String Pass =PasswordGenerator.getPassword(
                PasswordGenerator.NUMEROS+
		PasswordGenerator.MINUSCULAS+
		PasswordGenerator.MAYUSCULAS+
		PasswordGenerator.ESPECIALES,20);
        return Pass;
    }
    public void crearArchivoActivacion(String rut,String key){
        String url="ActClave.obj";
        String msg=rut+";"+key;
        msg=MetodoBase64E.cifrarBase64(msg);
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(url, true)))) {
            out.println("1");
            out.println(msg);
            
            JOptionPane.showMessageDialog(null,"Archivo Listo");
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
            JOptionPane.showMessageDialog(null,"Hubo un error"+e);
        }
    }
    public void leerArchivo() throws IOException{
        String ficher=userProgra+"/ActClave.obj";
        File archivo = new File (ficher);
//        File archivo = new File ("E:/informacion.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String cadena = br.readLine();
        int filas=Integer.parseInt(cadena);
        String[] alinea=new String[filas];
        String separador = ";";
        String rut = null;
        for (int i = 0; i < alinea.length; i++) {
            cadena=br.readLine();
            alinea[i]=cadena;
        }
        for (int i = 0; i < alinea.length; i++) {
            String cade=alinea[i];
            cade=MetodoBase64E.descifrarBase64(cade);
            StringTokenizer st=new StringTokenizer(cade, separador);
            while (st.hasMoreTokens()) {
                rut=st.nextToken();
                txtClaveSecre.setText(st.nextToken());
              
            }     
        }
        if(!rut.equals(txtRut.getText())){
            JOptionPane.showMessageDialog(null, "Error Rut no corresponde");
            System.exit(0);
        }
    }
    public static void eliminarFichero() {
        String ficher=userProgra+"/ActClave.obj";
        File fichero = new File (ficher);
        if (!fichero.exists()) {
            System.out.println("El archivo data no existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo data fue eliminado.");
        }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lbmPregunta = new javax.swing.JLabel();
        txtRespuestaSecre = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtClaveSecre = new javax.swing.JTextField();
        btnValidarKey = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtPassN = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPassN2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Validar Datos Ingresados"));

        jLabel1.setText("*Rut:");

        jLabel2.setText("*Correo:");

        lbmPregunta.setText("Pregunta:");

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbmPregunta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRespuestaSecre, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(btnConsultar)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbmPregunta)
                    .addComponent(txtRespuestaSecre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnConsultar)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Validar Password Enviada"));

        jLabel3.setText("Verificar Clave Secreta:");

        btnValidarKey.setText("Validar");
        btnValidarKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarKeyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClaveSecre, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnValidarKey)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtClaveSecre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnValidarKey))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cambio de Clave"));

        jLabel4.setText("Clave Nueva:");

        jLabel5.setText("Ingrese Clave de Nuevo:");

        jButton1.setText("Cambiar Clave");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPassN, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPassN2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPassN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtPassN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            // TODO add your handling code here:
            String rut = txtRut.getText();
            String mail = txtCorreo.getText();
            String respSecre = txtRespuestaSecre.getText();
            String key = generarPASS();
            if(!dAOLogin.sqlValidar(rut,mail,respSecre)){
                JOptionPane.showMessageDialog(null, "Datos no coinciden con los almacenados");
            }else{
                if(!dAOLogin.sqlUpdateSolicitud(rut,key)){
                    JOptionPane.showMessageDialog(null, "Solicitar ayuda Al Administrador Solicitud no se puede realizar");
                }else{
                    JOptionPane.showMessageDialog(null, "Solicitud clave creada");
                    crearArchivoActivacion(rut, key);
                    txtCorreo.setEditable(false);
                    txtRespuestaSecre.setEditable(false);
                    btnConsultar.setEnabled(false);
                    jPanel2.setVisible(true);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
//            java.util.logging.Logger.getLogger(FrRecuperarPass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnValidarKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarKeyActionPerformed
        // TODO add your handling code here:
        try {
            leerArchivo();
        } catch (IOException ex) {
            Logger.getLogger(FrRecuperarPass.class.getName()).log(Level.SEVERE, null, ex);
        }
        String rut = txtRut.getText();
        String key = txtClaveSecre.getText();
        if(!dAOLogin.sqlVerificarPass2(rut,key)){
            JOptionPane.showMessageDialog(null, "Solicitar ayuda Al Administrador \n Clave Incorrecta");
        }else{
            JOptionPane.showMessageDialog(null, "Cambie Su clave");
            txtClaveSecre.setEditable(false);
            btnValidarKey.setEnabled(false);
            jPanel3.setVisible(true);
        }
    }//GEN-LAST:event_btnValidarKeyActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        eliminarFichero();
        String pass1 = txtPassN.getText();
        String pass2 = txtPassN2.getText();
        String rut = txtRut.getText();
        if(pass1.equals(pass2)){
            try {
                clLogin.setPassword(pass1);
                String passF = Metodos.encriptaEnMD5(pass1);
                if(dAOLogin.sqlUpdatePass(rut,passF)){
                    JOptionPane.showMessageDialog(null, "Cambio de Clave con exito");
                    FrLogin frLogin = new FrLogin();
                    frLogin.setVisible(true);
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Solicitar ayuda Al Administrador");
                }
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(FrRecuperarPass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Las Claves no son iguales en los 2 campos");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrRecuperarPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrRecuperarPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrRecuperarPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrRecuperarPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrRecuperarPass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnValidarKey;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbmPregunta;
    private javax.swing.JTextField txtClaveSecre;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtPassN;
    private javax.swing.JTextField txtPassN2;
    private javax.swing.JTextField txtRespuestaSecre;
    private javax.swing.JTextField txtRut;
    // End of variables declaration//GEN-END:variables
}

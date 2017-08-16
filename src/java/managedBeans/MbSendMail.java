/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBeans;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author rincostante
 */
public class MbSendMail implements Serializable{

    /**
     * Creates a new instance of MbSendMail
     */
    public MbSendMail() {
    }
    
    @Resource(mappedName ="java:/jboss/mail/gmail")
    private Session mailSesion;
    Message msg;
        
        public void enviarMensaje() throws AddressException, MessagingException{
//            msg = new MimeMessage(mailSesion);
//            try {            
//                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
//                        "yamilamcalvino@gmail.com", "Pirulina"));
//                
//                msg.setSubject("Asunto");
//                msg.setText("Contenido del mensaje");
//                msg.setHeader("Prueba correo wild fly", "testsacvefor@gmail.com");
//                Transport.send(msg);
//                
//            } catch (UnsupportedEncodingException | MessagingException ex) {
//                System.out.println("Hubo un error enviando el correo de prueba" + ex.getMessage());
//            }
            /**
                $config['ext_base_smtp_config_editable']['_smtp_auth'] = TRUE;
                $config['ext_base_smtp_config_editable']['smtp_host'] = 'ssl://smtp.gmail.com';
                $config['ext_base_smtp_config_editable']['smtp_user'] = 'testsacvefor@gmail.com';
                $config['ext_base_smtp_config_editable']['smtp_pass'] = '321654987-';
                $config['ext_base_smtp_config_editable']['smtp_port'] = 465;
                $config['ext_base_smtp_config_editable']['validate'] = '';
                $config['ext_base_smtp_config_editable']['SMTPSecure'] = 'ssl';
                * 
                * https://mundosica.com/enviando-correos-electronicos-desde-java-haciendo-clase-gmail-con-conexion-smpt-con-ssl/
             */
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", "gestionipap@gmail.com");
            p.setProperty("mail.smtp.auth", "true");
            
            Session s = Session.getDefaultInstance(p, null);
            
            MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress("gestionipap@gmail.com"));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress("rincostante@gmail.com"));
            mensaje.setSubject("Asunto");
            mensaje.setContent("Mensaje", "text/html; charset=utf-8");
            
            Transport t = s.getTransport("smtp");
            t.connect("gestionipap@gmail.com", "usgpxriehulvqxmz");
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
        }
}

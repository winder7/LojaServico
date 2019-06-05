package com.waal.uteis;

/**
 * @Data: 27/04/2018
 * @Autor Winder Rezende
 */
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Enviar {

    public static void Email(String usrEmail, String tituloEamil, String mensagem) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Properties props = new Properties();

                //Parâmetros de conexão com servidor de email
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");

                Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("isoinformaticati@gmail.com", "Ctrl270787");
                    }
                });

                session.setDebug(false); //Ativa Debug para sessão

                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("isoinformaticati@gmail.com")); //Remetente

                    Address[] toUser = InternetAddress.parse(usrEmail); //Destinatário(s)

                    message.setRecipients(Message.RecipientType.TO, toUser);
                    message.setSubject(tituloEamil); //Assunto
                    message.setText(mensagem, "utf-8", "html");

                    Transport.send(message);//Método para enviar a mensagem criada

                    System.out.println("Feito!!!");
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}

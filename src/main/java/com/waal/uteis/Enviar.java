package com.waal.uteis;

/**
 * @Data: 27/04/2018
 * @Autor Winder Rezende
 */
import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Enviar {

    public static void Email(String usrEmail, String tituloEamil, String mensagem, File file) {
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

                    MimeBodyPart mbpMensagem = new MimeBodyPart();
                    mbpMensagem.setText(mensagem, "utf-8", "html");

                    Multipart mp = new MimeMultipart();//partes do email
                    mp.addBodyPart(mbpMensagem);

                    if (file != null) {
                        MimeBodyPart mbpAnexo = new MimeBodyPart();
                        DataSource fds = new FileDataSource(file);
                        mbpAnexo.setDisposition(Part.ATTACHMENT);
                        mbpAnexo.setDataHandler(new DataHandler(fds));
                        mbpAnexo.setFileName(file.getName());
                        mp.addBodyPart(mbpAnexo);
                    }

                    message.setContent(mp);
                    message.saveChanges();

                    Transport.send(message);//Método para enviar a mensagem criada

                    System.out.println("Feito!!!");
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}

package br.uneb.gerenciadordespesas.util;

import br.uneb.gerenciadordespesas.model.Usuario;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {

    public static void emailBemVindo(Usuario usuario) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Authenticator autenticacao = new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication("letsorganize2@gmail.com", "lets2024");
            }
        };

        Session sessao = Session.getDefaultInstance(props, autenticacao);

        sessao.setDebug(true);

        try {
            Message mensagem = new MimeMessage(sessao);
            mensagem.setFrom(new InternetAddress("letsorganize2@gmail.com"));
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse("albierygoncalves2@gmail.com"));
            mensagem.setSubject("Teste");

            BodyPart parte = new MimeBodyPart();
            parte.setText("Email teste");

            Transport.send(mensagem);


        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}

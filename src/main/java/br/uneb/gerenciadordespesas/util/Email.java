package br.uneb.gerenciadordespesas.util;

import br.uneb.gerenciadordespesas.model.Usuario;

public class Email {

    public static void emailBemVindo(Usuario usuario) {

//        String enderecoEmail = "letsorganize2@gmail.com";
//        String senha = "";
//
//        SimpleEmail email = new SimpleEmail();
//
//        email.setHostName("smtp.gmail.com");
//        email.setSmtpPort(587);
//        email.setAuthenticator(new DefaultAuthenticator(enderecoEmail, senha));
//        email.setStartTLSEnabled(true);
//        email.setStartTLSRequired(true);
//
//        try {
//            email.setFrom(enderecoEmail);
//            email.setSubject("Teste email");
//            email.setMsg("Testando envio de email com apache commons");
//            email.addTo(enderecoEmail);
//            email.send();
//            System.out.println("OK");
//        } catch (EmailException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        emailBemVindo(new Usuario());
    }
}

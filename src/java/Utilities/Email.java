/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Roberto Santin
 */
public class Email
{
    
    public static void sendEmail(String to, String senha) throws EmailException
    {

        SimpleEmail email = new SimpleEmail();

        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.addTo(to);
        email.setFrom("iotunivates@gmail.com", "IoT");
        email.setSubject("Recuperação de Senha");
        email.setMsg("Nova senha gerada com sucesso: " + senha );
        System.out.println("autenticando...");
        email.setSSL(true);
        email.setAuthentication("iotunivates@gmail.com", "iotunivates985$#");
        System.out.println("enviando e-mail de recuperação de senha para: " + to);
        email.send();
        System.out.println("Email enviado!");
    }
    
}

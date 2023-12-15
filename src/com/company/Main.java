package com.company;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException, MessagingException {
        // Перед началом работы необходимо скачать библиотеку javax.mail.jar
        // Ссылка на него https://javaee.github.io/javamail/
        // Далее подключить (File -> Project Structure -> Librarian)

        Properties properties = new Properties();
        properties.put("mail.smtps.host","smtps.yandex.ru");
        properties.put("mail.smtps.socketFactory.port",465);
        properties.put("mail.smtps.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtps.auth","true");
        properties.put("mail.smtps.port",465);


        /**  //google
        properties.put("mail.transport.protocol","smtps");
        properties.put("mail.smtps.auth","true");
        properties.put("mail.smtps.host","smtps.gmail.com");
        properties.put("mail.smtps.user","email");
         **/

        Session session = Session.getDefaultInstance(
                properties,
                new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication("email", "pas");
                    }
                }
        );


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("email"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("email куда"));
            message.setSubject("Заголовок");
            message.setText("Текст письма");

            Transport.send(message);
            /**
            Transport transport = session.getTransport();
            transport.connect("email", "pas");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
             **/

            JOptionPane.showMessageDialog(null, "Письмо отправлено");
    }
}

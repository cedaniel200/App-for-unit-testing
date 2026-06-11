package com.cedaniel200.practice.console;

import com.cedaniel200.practice.domain.email.EmailDomain;
import com.cedaniel200.practice.model.EmailData;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class EmailCommands {

    private static final String SUCCESS_MESSAGE = "Hemos intentado enviar tu correo, esperamos tengas suerte";
    private static final String FAILURE_MESSAGE = "Error al enviar el correo. Revisa la sección 'Configuración de variables de entorno' en el README para más información";
    private EmailDomain emailDomain;

    public EmailCommands(EmailDomain emailDomain) {
        this.emailDomain = emailDomain;
    }

    @ShellMethod(value = "Send email")
    public String send(String to, String subject, String message) {
        boolean sent = emailDomain.sendMail(new EmailData(to, subject, message));
        return sent ? SUCCESS_MESSAGE : FAILURE_MESSAGE;
    }
}

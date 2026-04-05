package mg.univUf.mami.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import mg.univUf.mami.entity.Utilisateur;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${app.admin.email}")
    private String adminEmail;
    public void envoyerMailAdmin(Utilisateur user) {
    	
    	    SimpleMailMessage message = new SimpleMailMessage();

    	    message.setTo(adminEmail);
    	    message.setSubject("Nouvelle inscription utilisateur");

    	    message.setText(
    	        "Pseudo : " + user.getPseudo() + "\n" +
    	        "Compte en attente d'activation"
    	    );

    	    mailSender.send(message);
    	}
    public void envoyerMailValidation(Utilisateur user) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(user.getMail()); // si pseudo = email
        message.setSubject("Compte activé");

        message.setText("Votre compte est maintenant activé. Vous pouvez vous connecter.");

        mailSender.send(message);
    }
}

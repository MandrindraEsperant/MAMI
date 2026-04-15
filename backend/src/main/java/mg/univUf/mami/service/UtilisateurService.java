package mg.univUf.mami.service;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mg.univUf.mami.entity.ChangePasswordRequest;
import mg.univUf.mami.entity.Utilisateur;
import mg.univUf.mami.entity.Utilisateur.Role;
import mg.univUf.mami.repository.UtilisateurRepo;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepo repo;

    @Autowired
    private MailService mailService;

    @Value("${app.admin.email}")
    private String adminEmail;
    
    public Utilisateur save(Utilisateur user) {
        String salt = BCrypt.gensalt();
        String password = user.getMotDePasse();

        String hashedPassword = BCrypt.hashpw(password, salt);
        user.setMotDePasse(hashedPassword);
        user.setEnabled(false);

        Utilisateur savedUser = repo.save(user);

        // envoi mail après sauvegarde
        mailService.envoyerMailAdmin(savedUser);

        return savedUser;
    }
    public void changerMotDePasse(String pseudo, String ancienPassword, String nouveauPassword) {

        Utilisateur user = repo.findByPseudo(pseudo)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        // 🔥 vérifier actif
        if (!user.isEnabled()) {
            throw new RuntimeException("Compte non activé");
        }

        // 🔥 vérifier ancien mot de passe
        if (!BCrypt.checkpw(ancienPassword, user.getMotDePasse())) {
            throw new RuntimeException("Ancien mot de passe incorrect");
        }

        // 🔥 validation minimum
        if (nouveauPassword.length() < 6) {
            throw new RuntimeException("Mot de passe trop court");
        }

        // 🔥 hash nouveau
        String hashed = BCrypt.hashpw(nouveauPassword, BCrypt.gensalt());
        user.setMotDePasse(hashed);

        repo.save(user);
    }
    
    public Utilisateur validerUtilisateur(Long id) {

        Optional<Utilisateur> user = repo.findById(id);

        if (user == null) {
            throw new RuntimeException("Utilisateur introuvable");
        }
        Utilisateur U = user.get();
        if (U.isEnabled()) {
            throw new RuntimeException("Utilisateur déjà activé");
        }

        U.setEnabled(true);
        U.setRole(Role.ROLE_USER);
        mailService.envoyerMailValidation(U);
        return repo.save(U);
    }
    
}
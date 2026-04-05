package mg.univUf.mami.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mg.univUf.mami.entity.Utilisateur;
import mg.univUf.mami.service.UtilisateurService;

@RestController
public class UtilisateurController {
	@Autowired
	UtilisateurService service;
	
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody Utilisateur user) {

        Utilisateur savedUser = service.save(user);

        return ResponseEntity.ok(savedUser);
    }
	
	@PutMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody Map<String, String> body) {

	    service.changerMotDePasse(
	        body.get("pseudo"),
	        body.get("ancienMotDePasse"),
	        body.get("nouveauMotDePasse")
	    );

	    return ResponseEntity.ok("Mot de passe modifié");
	}
	
	 @PutMapping("/valider/{id}")
	    public ResponseEntity<?> validerUser(@PathVariable Long id) {

	        Utilisateur user = service.validerUtilisateur(id);

	        return ResponseEntity.ok("Utilisateur " + user.getPseudo() + " activé");
	    }
}

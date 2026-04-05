package mg.univUf.mami.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mg.univUf.mami.entity.Eleve;
import mg.univUf.mami.entity.Promotion;
import mg.univUf.mami.entity.StatutEleve;
import mg.univUf.mami.repository.PromotionRepo;
import mg.univUf.mami.service.EleveService;

@RestController
public class EleveController {
	@Autowired
	PromotionRepo promotionRepo;

	@Autowired
	EleveService service;

	@PostMapping(value = "/eleves", consumes = "multipart/form-data")
	public ResponseEntity<?> createEleve(
	        @RequestParam("nom") String nom,
	        @RequestParam("prenom") String prenom,
	        @RequestParam("numero") String numero,
	        @RequestParam("nomFacebook") String nomFacebook,
	        @RequestParam("parcours") String parcours,
	        @RequestParam("adresse") String adresse,
	        @RequestParam("statut") String statut,
	        @RequestParam("promotionId") Long promotionId,
	        @RequestParam(value = "photo", required = false) MultipartFile photo
	) throws IOException {

	    EleveRequest request = new EleveRequest();
	    request.setNom(nom);
	    request.setPrenom(prenom);
	    request.setNumero(numero);
	    request.setNomFacebook(nomFacebook);
	    request.setParcours(parcours);
	    request.setAdresse(adresse);
	    request.setStatut(statut);
	    request.setPromotionId(promotionId);

	    service.createEleve(request, photo);

	    return ResponseEntity.ok("Eleve créé avec photo");
	}
	
	@DeleteMapping("/eleves/{id}")
	public ResponseEntity<?> deleteEleve(@PathVariable Long id) {

	    service.deleteEleve(id);

	    return ResponseEntity.ok("Eleve supprimé avec succès");
	}

}

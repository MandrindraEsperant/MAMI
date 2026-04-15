package mg.univUf.mami.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mg.univUf.mami.controller.EleveRequest;
import mg.univUf.mami.entity.Eleve;
import mg.univUf.mami.entity.Promotion;
import mg.univUf.mami.entity.StatutEleve;
import mg.univUf.mami.repository.EleveRepo;
import mg.univUf.mami.repository.PromotionRepo;

@Service
public class EleveService {

    @Autowired
    private EleveRepo repo;

    @Autowired
    private PromotionRepo promotionRepo;

    public Eleve createEleve(EleveRequest request, MultipartFile file) throws IOException {

        if (request.getNom() == null || request.getNom().isEmpty()) {
            throw new RuntimeException("Nom obligatoire");
        }

        // 🔥 statut
        StatutEleve statut;
        try {
            statut = StatutEleve.valueOf(request.getStatut().toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Statut invalide");
        }

        // 🔥 promotion
        Promotion promo = promotionRepo.findById(request.getPromotionId())
                .orElseThrow(() -> new RuntimeException("Promotion introuvable"));

        // 🔥 gestion fichier
        String fileName = null;

        if (file != null && !file.isEmpty()) {
            fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path path = Paths.get("uploads/" + fileName);
            Files.createDirectories(path.getParent());

            Files.write(path, file.getBytes());
        }

        // 🔥 mapping
        Eleve eleve = new Eleve();
        eleve.setNom(request.getNom());
        eleve.setPrenom(request.getPrenom());
        eleve.setNumero(request.getNumero());
        eleve.setNomFacebook(request.getNomFacebook());
        eleve.setParcours(request.getParcours());
        eleve.setAdresse(request.getAdresse());
        eleve.setStatut(statut);
        eleve.setPromotion(promo);
        eleve.setPhoto(fileName); // 🔥 important

        return repo.save(eleve);
    }
    public void deleteEleve(Long id) {

        Eleve eleve = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Eleve introuvable"));

        // 🔥 option: vérifier relation utilisateur
        if (eleve.getUtilisateur() != null) {
            throw new RuntimeException("Impossible de supprimer : élève lié à un utilisateur");
        }

        repo.delete(eleve);
    }
    
    public List<Eleve> getAll() {
        return repo.findAll();
    }
}
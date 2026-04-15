package mg.univUf.mami.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.univUf.mami.entity.Promotion;
import mg.univUf.mami.repository.PromotionRepo;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepo repo;

    // 🔹 CREATE
    public Promotion savePromotion(Promotion promotion) {

        if (promotion.getNom() == null || promotion.getNom().isEmpty()) {
            throw new RuntimeException("Nom obligatoire");
        }

        return repo.save(promotion);
    }

    // 🔹 UPDATE
    public Promotion updatePromotion(Long id, Promotion newPromotion) {

        Promotion promo = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Promotion introuvable"));

        promo.setNom(newPromotion.getNom());
        promo.setAnnee(newPromotion.getAnnee());

        return repo.save(promo);
    }

}

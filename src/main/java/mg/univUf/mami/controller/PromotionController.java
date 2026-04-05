package mg.univUf.mami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.univUf.mami.entity.Promotion;
import mg.univUf.mami.service.PromotionService;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    @Autowired
    
    private PromotionService service;

    // 🔹 CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Promotion promotion) {
        service.savePromotion(promotion);
        return ResponseEntity.ok("Promotion créée");
    }

    // 🔹 UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Promotion promotion) {
        service.updatePromotion(id, promotion);
        return ResponseEntity.ok("Promotion modifiée");
    }

} 
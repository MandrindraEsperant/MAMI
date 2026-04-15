package mg.univUf.mami.controller;

import lombok.Data;

@Data
public class EleveRequest {

    private String nom;
    private String prenom;
    private String numero;
    private String nomFacebook;
    private String parcours;
    private String adresse;
    private String statut;
    private Long promotionId;

    public void validate() {
        if (nom == null || nom.isEmpty()) {
            throw new RuntimeException("Nom obligatoire");
        }
        if (statut == null) {
            throw new RuntimeException("Statut obligatoire");
        }
        if (promotionId == null) {
            throw new RuntimeException("Promotion obligatoire");
        }
    }
}
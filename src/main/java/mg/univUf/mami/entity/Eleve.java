package mg.univUf.mami.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Eleve {
    @Id @GeneratedValue
    private Long id;

    private String nom;
    private String prenom;
    private String photo;
    private String numero;
    private String nomFacebook;
    private String parcours;
    private String adresse;

    @Enumerated(EnumType.STRING)
    private StatutEleve statut;
    @ManyToOne
    private Promotion promotion;

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}
package mg.univUf.mami.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Promotion {
    @Id @GeneratedValue
    private Long id;

    private int annee;
    private String nom;

    @OneToMany(mappedBy = "promotion")
    private List<Eleve> eleves;
}
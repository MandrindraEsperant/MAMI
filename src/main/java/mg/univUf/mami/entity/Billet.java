package mg.univUf.mami.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Billet {
    @Id @GeneratedValue
    private Long id;

    private LocalDate date;
    private String titre;
    private double prix;
}
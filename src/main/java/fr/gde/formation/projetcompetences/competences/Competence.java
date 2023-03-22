package fr.gde.formation.projetcompetences.competences;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="competence")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    private String description;


}

package fr.gde.formation.projetcompetences.competences;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nom", nullable = false, unique = true, length = 50)
    @NotBlank
    private String nom;

    @Column(name = "description")
    private String description;


}

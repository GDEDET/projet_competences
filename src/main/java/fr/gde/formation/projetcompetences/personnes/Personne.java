package fr.gde.formation.projetcompetences.personnes;

import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetence;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="personne")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "nom", nullable = false, length = 50)
    @NotBlank
    private String nom;
    @Column(name = "prenom", nullable = false, length = 50)
    @NotBlank
    private String prenom;
    @Column(name = "is_manager", columnDefinition = "boolean default false")
    private Boolean isManager;

    @ManyToMany
    @ToString.Exclude
    private Set<NiveauCompetence> competences = new HashSet<>();

}

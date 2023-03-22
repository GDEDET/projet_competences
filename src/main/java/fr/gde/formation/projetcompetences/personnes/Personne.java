package fr.gde.formation.projetcompetences.personnes;

import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetence;
import jakarta.persistence.*;
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
    private String nom;
    private String prenom;
    private Boolean isManager;

    @ManyToMany
    @ToString.Exclude
    private Set<NiveauCompetence> competences = new HashSet<>();

}

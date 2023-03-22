package fr.gde.formation.projetcompetences.niveaucompetences;

import fr.gde.formation.projetcompetences.competences.Competence;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="niveau_competence")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NiveauCompetence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Competence competence;
    private int niveau;
    @ManyToMany
    @ToString.Exclude
    private Set<NiveauCompetence> prerequis = new HashSet<>();

    public NiveauCompetence(Competence competence, int niveau) {
        this.competence = competence;
        this.niveau = niveau;
    }
}

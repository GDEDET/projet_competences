package fr.gde.formation.projetcompetences.equipes;

import fr.gde.formation.projetcompetences.personnes.Personne;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="equipe")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    @ManyToMany
    @ToString.Exclude
    private Set<Personne> membres = new HashSet<>();
}

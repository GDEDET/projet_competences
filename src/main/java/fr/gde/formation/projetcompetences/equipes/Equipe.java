package fr.gde.formation.projetcompetences.equipes;

import fr.gde.formation.projetcompetences.personnes.Personne;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nom", nullable = false, unique = true, length = 50)
    @NotBlank
    private String nom;

    @ManyToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Personne> membres = new HashSet<>();
}

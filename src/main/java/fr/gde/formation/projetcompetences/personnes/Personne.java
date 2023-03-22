package fr.gde.formation.projetcompetences.personnes;

import fr.gde.formation.projetcompetences.auth.roles.Role;
import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetence;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @Column(name = "username", unique = true, nullable = false, length = 20)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany
    private List<Role> roles = new ArrayList<>();

    @ManyToMany
    @ToString.Exclude
    private Set<NiveauCompetence> competences = new HashSet<>();

}

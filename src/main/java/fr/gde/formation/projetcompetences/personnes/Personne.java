package fr.gde.formation.projetcompetences.personnes;

import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetence;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "personnes")
@Data
public class Personne {

    @Id
    private String id;
    private String nom;
    private String prenom;
    private Boolean isManager;

    @DBRef
    private List<NiveauCompetence> competences = new ArrayList<>();

}

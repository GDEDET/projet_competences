package fr.gde.formation.projetcompetences.competences;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Competence {

    @Id
    private String id;

    private String nom;

    private String description;


}

package fr.gde.formation.projetcompetences.niveaucompetences;

import fr.gde.formation.projetcompetences.competences.Competence;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class NiveauCompetence {
    @Id
    private String id;
    @DBRef
    private Competence competence;
    private int niveau;
    @DBRef
    private List<NiveauCompetence> prerequis = new ArrayList<>();

    public NiveauCompetence(Competence competence, int niveau) {
        this.competence = competence;
        this.niveau = niveau;
    }
}

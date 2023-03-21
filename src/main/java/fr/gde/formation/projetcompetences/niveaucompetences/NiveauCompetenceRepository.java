package fr.gde.formation.projetcompetences.niveaucompetences;

import fr.gde.formation.projetcompetences.competences.Competence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NiveauCompetenceRepository extends MongoRepository<NiveauCompetence, String> {

    NiveauCompetence findByNiveauAndCompetence(int niveau, Competence competence);
}

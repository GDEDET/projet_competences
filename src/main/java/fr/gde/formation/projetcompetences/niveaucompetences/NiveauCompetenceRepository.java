package fr.gde.formation.projetcompetences.niveaucompetences;

import fr.gde.formation.projetcompetences.competences.Competence;
import org.springframework.data.repository.CrudRepository;

public interface NiveauCompetenceRepository extends CrudRepository<NiveauCompetence, Long> {

    NiveauCompetence findByNiveauAndCompetence(int niveau, Competence competence);
}

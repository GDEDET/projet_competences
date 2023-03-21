package fr.gde.formation.projetcompetences.competences;

import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetence;
import fr.gde.formation.projetcompetences.utils.CRUDService;
import org.springframework.stereotype.Service;

@Service
public class CompetenceService extends CRUDService<Competence> {

    public CompetenceService(CompetenceRepository competenceRepository) {
        super(competenceRepository);
    }
}

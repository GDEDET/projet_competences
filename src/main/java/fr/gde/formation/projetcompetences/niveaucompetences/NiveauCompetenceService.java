package fr.gde.formation.projetcompetences.niveaucompetences;

import fr.gde.formation.projetcompetences.competences.Competence;
import fr.gde.formation.projetcompetences.competences.CompetenceRepository;
import fr.gde.formation.projetcompetences.utils.CRUDService;
import org.springframework.stereotype.Service;

@Service
public class NiveauCompetenceService extends CRUDService<NiveauCompetence> {

    public NiveauCompetenceService(NiveauCompetenceRepository niveauCompetenceRepository) {
        super(niveauCompetenceRepository);
    }

    public NiveauCompetence addPrerequis(NiveauCompetence prerequis, String idCompetence) {
        NiveauCompetence competence = this.findById(idCompetence);
        competence.getPrerequis().add(prerequis);
        this.save(competence);
        return competence;
    }
}

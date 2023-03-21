package fr.gde.formation.projetcompetences.niveaucompetences;

import fr.gde.formation.projetcompetences.competences.Competence;
import fr.gde.formation.projetcompetences.utils.CRUDService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class NiveauCompetenceService extends CRUDService<NiveauCompetence> {

    private final NiveauCompetenceRepository niveauCompetenceRepository;

    public NiveauCompetenceService(NiveauCompetenceRepository niveauCompetenceRepository) {
        super(niveauCompetenceRepository);
        this.niveauCompetenceRepository = niveauCompetenceRepository;
    }

    public NiveauCompetence addPrerequis(NiveauCompetence prerequis, String idCompetence) {
        NiveauCompetence competence = this.findById(idCompetence);
        competence.getPrerequis().add(prerequis);
        this.save(competence);
        return competence;
    }

    public void initNiveauxCompetence(Competence competence) {
        for (int i = 0; i < 11; i++) {
            this.save(new NiveauCompetence(competence, i));
        }
    }

    public NiveauCompetence findByNiveauAndCompetence(int niveau, Competence competence) {
        NiveauCompetence niveauCompetence = niveauCompetenceRepository.findByNiveauAndCompetence(niveau, competence);
        if(niveauCompetence == null){
            log.warn("Ce niveau de compétence n'existe pas");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce niveau de compétence n'existe pas");
        }
        return niveauCompetence;
    }
}

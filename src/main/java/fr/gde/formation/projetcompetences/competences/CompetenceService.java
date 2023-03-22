package fr.gde.formation.projetcompetences.competences;

import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetenceService;
import fr.gde.formation.projetcompetences.utils.CRUDService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class CompetenceService extends CRUDService<Competence> {

    private final NiveauCompetenceService niveauCompetenceService;

    private final CompetenceRepository competenceRepository;

    public CompetenceService(CompetenceRepository competenceRepository, NiveauCompetenceService niveauCompetenceService) {
        super(competenceRepository);
        this.niveauCompetenceService = niveauCompetenceService;
        this.competenceRepository = competenceRepository;
    }

    /**
     * Méthode qui permet de créer une compétence et de créer les niveaux de base (sans prérequis) associés à celle-ci
     * @param competence : la compétence que l'on souhaite créer
     * @return la nouvelle compétence
     * @throws ResponseStatusException si une compétence avec ce nom existe déjà.
     */
    public Competence initCompetenceAvecSesNiveaux(Competence competence){
        if(this.findByNom(competence.getNom()) != null){
            log.warn("Une compétence avec ce nom existe déjà");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Une compétence avec ce nom existe déjà");
        }
        this.save(competence);
        niveauCompetenceService.initNiveauxCompetence(competence);
        return competence;
    }

    /**
     * Méthode qui permet de trouver une compétence par son nom
     * @param nom : le nom de la compétence recherchée
     * @return la compétence trouvée
     */
    public Competence findByNom(String nom) {
        return competenceRepository.findByNom(nom);
    }
}

package fr.gde.formation.projetcompetences.niveaucompetences;

import fr.gde.formation.projetcompetences.competences.Competence;
import fr.gde.formation.projetcompetences.utils.CRUDService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class NiveauCompetenceService extends CRUDService<NiveauCompetence> {

    private final NiveauCompetenceRepository niveauCompetenceRepository;

    private static final int NIVEAU_MAX = 10;

    public NiveauCompetenceService(NiveauCompetenceRepository niveauCompetenceRepository) {
        super(niveauCompetenceRepository);
        this.niveauCompetenceRepository = niveauCompetenceRepository;
    }

    /**
     * Ajoute un prérequis (avec son niveau) a la compétence (avec son niveau).
     * @param prerequis le prérequis à ajouter.
     * @param idCompetence id de la compétence recoit le prérequis.
     * @throws ResponseStatusException NOT_FOUND si aucune compétence ne porte cet id.
     */
    public NiveauCompetence addPrerequis(NiveauCompetence prerequis, Long idCompetence) {
        NiveauCompetence competence = this.findById(idCompetence);
        competence.getPrerequis().add(prerequis);
        this.save(competence);
        return competence;
    }

    /**
     * Ajoute un prérequis (avec son niveau) à une compétence (avec son niveau) en fonction
     * de l'id de la compétence et l'id du prérequis.
     * @param idCompetence id de la compétence (avec son niveau)
     * @param idPrerequis id du prérequis (avec son niveau)
     * @throws ResponseStatusException NOT_FOUND si l'id de la compétence ou du prérequis n'est pas valide
     */
    public NiveauCompetence addPrerequis(Long idCompetence, Long idPrerequis){
        NiveauCompetence prerequis = this.findById(idPrerequis);
        return this.addPrerequis(prerequis, idCompetence);
    }

    /**
     * Méthode qui permet de créer les niveaux de compétences associés à une nouvelle compétence
     * @param competence : la nouvelle compétence
     */
    public void initNiveauxCompetence(Competence competence) {
        for (int i = 0; i < NIVEAU_MAX+1; i++) {
            this.save(new NiveauCompetence(competence, i));
        }
    }

    /**
     * Méthode qui permet de rechercher les compétences (avec niveau) associées à une liste de prérequis
     * @param prerequis : la liste des prérequis dont on souhaite connaitre les compétences associées
     * @return la liste des compétences correspondantes aux prérequis fournis
     */
    public List<NiveauCompetence> findPrerequisByPersonneCompetences(Set<NiveauCompetence> prerequis) {
        return this.niveauCompetenceRepository.findPrerequisByPersonneCompetences(prerequis);
    }
}

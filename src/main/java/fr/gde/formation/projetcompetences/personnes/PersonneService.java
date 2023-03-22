package fr.gde.formation.projetcompetences.personnes;

import fr.gde.formation.projetcompetences.competences.Competence;
import fr.gde.formation.projetcompetences.competences.CompetenceService;
import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetence;
import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetenceService;
import fr.gde.formation.projetcompetences.utils.CRUDService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonneService extends CRUDService<Personne> {

    private final NiveauCompetenceService niveauCompetenceService;

    private final CompetenceService competenceService;

    private final PersonneRepository personneRepository;

    public PersonneService(PersonneRepository personneRepository, NiveauCompetenceService niveauCompetenceService, CompetenceService competenceService) {
        super(personneRepository);
        this.niveauCompetenceService = niveauCompetenceService;
        this.personneRepository = personneRepository;
        this.competenceService = competenceService;
    }

    /**
     * Ajoute une compétence (avec son niveau) a la personne.
     * @param idPersonne id de l'équipe qui accepte le nouveau membre.
     * @param niveauCompetence nouvelle compétence de la personne
     * @throws ResponseStatusException NOT_FOUND si aucune personne ne porte cet id.
     */
    public Personne ajouterCompetence(Long idPersonne, NiveauCompetence niveauCompetence){
        Personne personne = this.findById(idPersonne);
        personne.getCompetences().add(niveauCompetence);
        this.save(personne);
        return personne;
    }

    /**
     * Ajoute une compétence à une personne en fonction de l'id de la compétence et l'id de la personne.
     * @param idPersonne id de la personne
     * @param idCompetence id de la compétence (avec son niveau)
     * @throws ResponseStatusException NOT_FOUND si l'id de la compétence ou de la personne n'est pas valide.
     */
    public Personne ajouterCompetence(Long idPersonne, Long idCompetence){
        NiveauCompetence nouvelleCompetence = this.niveauCompetenceService.findById(idCompetence);
        return this.ajouterCompetence(idPersonne, nouvelleCompetence);
    }

    public List<NiveauCompetence> findPrerequisByPersonne(Long idPersonne){
        Personne personne = this.findById(idPersonne);
        return this.niveauCompetenceService.findPrerequisByPersonneCompetences(personne.getCompetences());
    }

    public List<Personne> findPersonneNiveauSuperieurCompetence(int niveauBorne, Long idCompetence){
        Competence competence = this.competenceService.findById(idCompetence);
        return this.personneRepository.findPersonneNiveauSuperieurCompetence(niveauBorne, competence);
    }
}

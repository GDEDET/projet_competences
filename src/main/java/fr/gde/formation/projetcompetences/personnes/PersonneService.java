package fr.gde.formation.projetcompetences.personnes;

import fr.gde.formation.projetcompetences.competences.CompetenceService;
import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetence;
import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetenceService;
import fr.gde.formation.projetcompetences.utils.CRUDService;
import org.springframework.stereotype.Service;

@Service
public class PersonneService extends CRUDService<Personne> {

    private final CompetenceService competenceService;
    private final NiveauCompetenceService niveauCompetenceService;
    public PersonneService(PersonneRepository personneRepository, CompetenceService competenceService, NiveauCompetenceService niveauCompetenceService) {
        super(personneRepository);
        this.competenceService = competenceService;
        this.niveauCompetenceService = niveauCompetenceService;
    }

    /**
     * Méthode qui permet d'ajouter une compétence (avec son niveau) à une personne
     * et si celle-ci n'existe pas permet de la créer
     * @param competence (avec niveau) à ajouter à la personne
     * @param idPersonne la personne concernée
     * @return la personne concernée
     */
    public Personne addCompetence(NiveauCompetence competence, String idPersonne) {
        Personne personne = this.findById(idPersonne);
        if (competence.getCompetence().getId() == null) {
            competenceService.save(competence.getCompetence());
        }

        personne.getCompetences().add(competence);
        this.save(personne);
        return personne;
    }
}

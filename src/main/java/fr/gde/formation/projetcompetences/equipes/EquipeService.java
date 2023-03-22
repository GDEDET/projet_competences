package fr.gde.formation.projetcompetences.equipes;

import fr.gde.formation.projetcompetences.personnes.Personne;
import fr.gde.formation.projetcompetences.personnes.PersonneService;
import fr.gde.formation.projetcompetences.utils.CRUDService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EquipeService extends CRUDService<Equipe> {

    private final PersonneService personneService;

    public EquipeService(EquipeRepository equipeRepository, PersonneService personneService) {
        super(equipeRepository);
        this.personneService = personneService;
    }

    /**
     * Ajoute un membre a l'équipe.
     * @param idEquipe id de l'équipe qui accepte le nouveau membre.
     * @param personne nouveau membre de l'équipe.
     * @throws ResponseStatusException NOT_FOUND si aucune équipe ne porte cet id.
     */
    public Equipe ajouterMembre(Long idEquipe, Personne personne){
        Equipe equipe = this.findById(idEquipe);
        equipe.getMembres().add(personne);
        this.save(equipe);
        return equipe;
    }

    /**
     * Ajoute une personne à une équipe en fonction de l'id de la personne et l'id de l'équipe.
     * @param idEquipe id de l'équipe
     * @param idPersonne id de la personne
     * @throws ResponseStatusException NOT_FOUND si l'id de l'équipe ou de la personne n'est pas valide.
     */
    public Equipe ajouterMembre(Long idEquipe, Long idPersonne){
        Personne personne = this.personneService.findById(idPersonne);
        return this.ajouterMembre(idEquipe, personne);
    }
}

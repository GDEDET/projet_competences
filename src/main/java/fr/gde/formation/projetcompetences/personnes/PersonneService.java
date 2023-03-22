package fr.gde.formation.projetcompetences.personnes;

import fr.gde.formation.projetcompetences.auth.dto.RegisterRequestDto;
import fr.gde.formation.projetcompetences.auth.roles.Role;
import fr.gde.formation.projetcompetences.auth.roles.RoleRepository;
import fr.gde.formation.projetcompetences.competences.Competence;
import fr.gde.formation.projetcompetences.competences.CompetenceService;
import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetence;
import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetenceService;
import fr.gde.formation.projetcompetences.utils.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService extends CRUDService<Personne> {

    private final NiveauCompetenceService niveauCompetenceService;

    private final CompetenceService competenceService;

    private final PersonneRepository personneRepository;

    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PersonneService(PersonneRepository personneRepository, NiveauCompetenceService niveauCompetenceService, CompetenceService competenceService, RoleRepository roleRepository) {
        super(personneRepository);
        this.niveauCompetenceService = niveauCompetenceService;
        this.personneRepository = personneRepository;
        this.competenceService = competenceService;
        this.roleRepository = roleRepository;
    }

    /**
     * Permet de créer une personne en lui affectant le role personne par défaut et en encodant son mot de passe
     *
     * @param personneDto : les infos de la personne à creer
     * @return la personne créée
     */
    public Personne creerPersonne(RegisterRequestDto personneDto){
        Personne personne = new Personne();
        personne.setUsername(personneDto.getUsername());
        Optional<Role> personneRole = roleRepository.findByAuthority("PERSONNE");
        personne.setRoles(List.of(personneRole.get()));
        String password = passwordEncoder.encode(personneDto.getPassword());
        personne.setPassword(password);
        personne.setNom(personneDto.getNom());
        personne.setPrenom(personneDto.getPrenom());
        return this.personneRepository.save(personne);
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

    /**
     * Méthode qui permet de rechercher les compétences dont une personne a les prérequis
     * @param idPersonne : la personne dont on cherche a connaitre les compétences accessibles
     * @return la liste des compétences accessibles pour la personne donnée
     */
    public List<NiveauCompetence> findPrerequisByPersonne(Long idPersonne){
        Personne personne = this.findById(idPersonne);
        return this.niveauCompetenceService.findPrerequisByPersonneCompetences(personne.getCompetences());
    }

    /**
     * Méthode qui permet de connaitre la liste des personnes qui possède un niveau supérieur à un niveau donné
     * pour une compétence donnée.
     * @param niveauBorne : le niveau qui borne la recherche
     * @param idCompetence : l'id de la compétence dont on souhaite connaitre les personnes qualifiées
     * @return la liste des personnes qui possède un niveau supérieur au niveau donné pour la compétence donnée
     */
    public List<Personne> findPersonneNiveauSuperieurCompetence(int niveauBorne, Long idCompetence){
        Competence competence = this.competenceService.findById(idCompetence);
        return this.personneRepository.findPersonneNiveauSuperieurCompetence(niveauBorne, competence);
    }
}

package fr.gde.formation.projetcompetences.personnes;

import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetence;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("personnes")
public class PersonneController {

    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @GetMapping
    public Iterable<Personne> findAll() {
        return personneService.findAll();
    }

    @PostMapping
    public Personne save(@RequestBody Personne entity) {
        return personneService.save(entity);
    }

    @GetMapping("{id}")
    public Personne findById(@PathVariable Long id) {
        return personneService.findById(id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        personneService.deleteById(id);
    }

    @PutMapping("{idPersonne}/competence")
    public Personne ajouterCompetence(@PathVariable Long idPersonne, @RequestBody NiveauCompetence nouvelleCompetence) {
        return personneService.ajouterCompetence(idPersonne, nouvelleCompetence);
    }

    @PutMapping("{idPersonne}/competence/{idCompetence}")
    public Personne ajouterCompetence(@PathVariable Long idPersonne, @PathVariable Long idCompetence) {
        return personneService.ajouterCompetence(idPersonne, idCompetence);
    }

    @GetMapping("{idPersonne}/competencesAccessibles")
    public List<NiveauCompetence> findPrerequisByPersonne(@PathVariable Long idPersonne) {
        return personneService.findPrerequisByPersonne(idPersonne);
    }

    @GetMapping("/niveau/{niveau}/competence/{idCompetence}")
    public List<Personne> findPersonneNiveauSuperieurCompetence(@PathVariable int niveau, @PathVariable Long idCompetence) {
        return personneService.findPersonneNiveauSuperieurCompetence(niveau, idCompetence);
    }
}

package fr.gde.formation.projetcompetences.personnes;

import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetence;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/competence/{idPersonne}")
    public Personne addCompetence(@RequestBody NiveauCompetence competence, @PathVariable Long idPersonne) {
        return personneService.addCompetence(competence, idPersonne);
    }
}

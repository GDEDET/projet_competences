package fr.gde.formation.projetcompetences.competences;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("competences")
public class CompetenceController {

    private final CompetenceService competenceService;

    public CompetenceController(CompetenceService competenceService) {
        this.competenceService = competenceService;
    }

    @PostMapping
    public Competence save(@RequestBody Competence competence) {
        return competenceService.initCompetenceAvecSesNiveaux(competence);
    }

    @GetMapping("{id}")
    public Competence findById(@PathVariable Long id) {
        return competenceService.findById(id);
    }

    @GetMapping
    public Iterable<Competence> findAll() {
        return competenceService.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        competenceService.deleteById(id);
    }
}

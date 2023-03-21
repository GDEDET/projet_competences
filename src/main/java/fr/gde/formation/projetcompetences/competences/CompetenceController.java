package fr.gde.formation.projetcompetences.competences;

import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetence;
import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("competences")
public class CompetenceController {

    private final CompetenceService competenceService;
    private final NiveauCompetenceService niveauCompetenceService;

    public CompetenceController(CompetenceService competenceService, NiveauCompetenceService niveauCompetenceService) {
        this.competenceService = competenceService;
        this.niveauCompetenceService = niveauCompetenceService;
    }

    @PostMapping
    public Competence save(@RequestBody Competence document) {
        return competenceService.save(document);
    }

    @GetMapping("{id}")
    public Competence findById(@PathVariable String id) {
        return competenceService.findById(id);
    }

    @GetMapping
    public List<Competence> findAll() {
        return competenceService.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        competenceService.deleteById(id);
    }

    @PostMapping("/prerequis/{idCompetence}")
    public NiveauCompetence addPrerequis(@RequestBody NiveauCompetence prerequis, @PathVariable String idCompetence) {
        return niveauCompetenceService.addPrerequis(prerequis, idCompetence);
    }
}

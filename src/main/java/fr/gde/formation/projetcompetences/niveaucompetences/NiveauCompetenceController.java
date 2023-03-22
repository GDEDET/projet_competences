package fr.gde.formation.projetcompetences.niveaucompetences;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("niveaucompetences")
public class NiveauCompetenceController {

    private final NiveauCompetenceService niveaucompetenceService;

    public NiveauCompetenceController(NiveauCompetenceService niveaucompetenceService) {
        this.niveaucompetenceService = niveaucompetenceService;
    }

    @PostMapping
    public NiveauCompetence save(@RequestBody NiveauCompetence document) {
        return niveaucompetenceService.save(document);
    }

    @GetMapping("{id}")
    public NiveauCompetence findById(@PathVariable Long id) {
        return niveaucompetenceService.findById(id);
    }

    @GetMapping
    public Iterable<NiveauCompetence> findAll() {
        return niveaucompetenceService.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        niveaucompetenceService.deleteById(id);
    }

    @PutMapping("{idCompetence}/prerequis")
    public NiveauCompetence ajouterPrerequis(@RequestBody NiveauCompetence prerequis, @PathVariable Long idCompetence) {
        return this.niveaucompetenceService.addPrerequis(prerequis, idCompetence);
    }

    @PutMapping("{idCompetence}/prerequis/{idPrerequis}")
    public NiveauCompetence ajouterPrerequis(@PathVariable Long idCompetence,@PathVariable Long idPrerequis) {
        return this.niveaucompetenceService.addPrerequis(idCompetence, idPrerequis);
    }

}

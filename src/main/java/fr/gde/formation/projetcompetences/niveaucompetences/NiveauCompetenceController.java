package fr.gde.formation.projetcompetences.niveaucompetences;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public NiveauCompetence findById(@PathVariable String id) {
        return niveaucompetenceService.findById(id);
    }

    @GetMapping
    public List<NiveauCompetence> findAll() {
        return niveaucompetenceService.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        niveaucompetenceService.deleteById(id);
    }
}

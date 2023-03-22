package fr.gde.formation.projetcompetences.equipes;

import fr.gde.formation.projetcompetences.personnes.Personne;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("equipes")
public class EquipeController {

    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @PostMapping
    public Equipe save(@RequestBody Equipe equipe) {
        return equipeService.save(equipe);
    }

    @GetMapping("{id}")
    public Equipe findById(@PathVariable Long id) {
        return equipeService.findById(id);
    }

    @GetMapping
    public Iterable<Equipe> findAll() {
        return equipeService.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        equipeService.deleteById(id);
    }

    @PutMapping("{idEquipe}/membres")
    public Equipe ajouterMembre(@PathVariable Long idEquipe, @RequestBody Personne personne) {
        return equipeService.ajouterMembre(idEquipe, personne);
    }

    @PutMapping("{idEquipe}/membres/{idPersonne}")
    public Equipe ajouterMembre(@PathVariable Long idEquipe, @PathVariable Long idPersonne) {
        return equipeService.ajouterMembre(idEquipe, idPersonne);
    }


}

package fr.gde.formation.projetcompetences.equipes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("equipes")
public class EquipeController {

    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @PostMapping
    public Equipe save(@RequestBody Equipe document) {
        return equipeService.save(document);
    }

    @GetMapping("{id}")
    public Equipe findById(@PathVariable String id) {
        return equipeService.findById(id);
    }

    @GetMapping
    public List<Equipe> findAll() {
        return equipeService.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        equipeService.deleteById(id);
    }
}

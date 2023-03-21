package fr.gde.formation.projetcompetences.equipes;

import fr.gde.formation.projetcompetences.utils.CRUDService;
import org.springframework.stereotype.Service;

@Service
public class EquipeService extends CRUDService<Equipe> {

    public EquipeService(EquipeRepository equipeRepository) {
        super(equipeRepository);
    }
}

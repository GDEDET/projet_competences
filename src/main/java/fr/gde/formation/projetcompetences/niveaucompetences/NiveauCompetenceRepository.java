package fr.gde.formation.projetcompetences.niveaucompetences;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface NiveauCompetenceRepository extends CrudRepository<NiveauCompetence, Long> {

    @Query("select nc from NiveauCompetence nc join nc.prerequis pr where pr IN :competences")
    List<NiveauCompetence> findPrerequisByPersonneCompetences(Set<NiveauCompetence> competences);
}

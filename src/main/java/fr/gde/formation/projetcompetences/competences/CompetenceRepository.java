package fr.gde.formation.projetcompetences.competences;

import org.springframework.data.repository.CrudRepository;

public interface CompetenceRepository extends CrudRepository<Competence, Long> {

    Competence findByNom(String nom);
}

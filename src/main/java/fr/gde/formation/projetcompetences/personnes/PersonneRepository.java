package fr.gde.formation.projetcompetences.personnes;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonneRepository extends CrudRepository<Personne, Long> {

//    @Query("")
//    List<Personne> findPersonneNiveauSuperieurCompetence(int niveau, Competence competence);
}

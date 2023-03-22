package fr.gde.formation.projetcompetences.personnes;

import fr.gde.formation.projetcompetences.competences.Competence;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonneRepository extends CrudRepository<Personne, Long> {

    Personne findByUsername(String username);

    @Query("select p from Personne p join p.competences pc where pc.competence = :competence and pc.niveau > :niveau")
    List<Personne> findPersonneNiveauSuperieurCompetence(int niveau, Competence competence);
}

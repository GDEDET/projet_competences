package fr.gde.formation.projetcompetences.competences;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompetenceRepository extends MongoRepository<Competence, String> {

    Competence findByNom(String nom);
}

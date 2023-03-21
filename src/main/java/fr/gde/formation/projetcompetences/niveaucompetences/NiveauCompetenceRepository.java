package fr.gde.formation.projetcompetences.niveaucompetences;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NiveauCompetenceRepository extends MongoRepository<NiveauCompetence, String> {
}

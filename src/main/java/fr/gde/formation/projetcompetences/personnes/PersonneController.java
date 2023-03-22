package fr.gde.formation.projetcompetences.personnes;

import fr.gde.formation.projetcompetences.auth.dto.RegisterRequestDto;
import fr.gde.formation.projetcompetences.niveaucompetences.NiveauCompetence;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("personnes")
@Tag(name = "Personne", description = "L'API des personnes")
@SecurityRequirement(name = "Bearer Authentication")
@Secured("PERSONNE")
public class PersonneController {

    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @Operation(summary = "Afficher toutes les personnes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personnes trouvées",
                    content = @Content( array = @ArraySchema(schema = @Schema(implementation = Personne.class))))
    })
    @GetMapping
    public Iterable<Personne> findAll() {
        return personneService.findAll();
    }

    @Operation(summary = "Créer une personne")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Personne créée",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterRequestDto.class))})
    })
    @PostMapping
    @Secured("MANAGER")
    public Personne save(@RequestBody RegisterRequestDto entity) {
        return personneService.creerPersonne(entity);
    }

    @Operation(summary = "Met à jour une personne")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Personne mise à jour",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Personne.class))})
    })
    @PutMapping
    @Secured("MANAGER")
    public Personne modifier(@RequestBody Personne personne) {
        return personneService.update(personne);
    }

    @Operation(summary = "Trouver une personne via son Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personne trouvée",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Personne.class))}),
            @ApiResponse(responseCode = "400", description = "Id fourni invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Personne non trouvée")
    })
    @GetMapping("{id}")
    public Personne findById(@Parameter(description = "Id de la personne à afficher") @PathVariable Long id) {
        return personneService.findById(id);
    }

    @Operation(summary = "Supprimer une personne via son Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Personne supprimée"),
            @ApiResponse(responseCode = "400", description = "Id fourni invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Personne non trouvée")
    })
    @DeleteMapping("{id}")
    public void deleteById(@Parameter(description = "Id de la personne à supprimer") @PathVariable Long id) {
        personneService.deleteById(id);
    }

    @Operation(summary = "Permet d'ajouter un niveau de compétence à une personne")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personne mise à jour",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Personne.class))}),
            @ApiResponse(responseCode = "400", description = "Id fourni invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Personne non trouvée")
    })
    @PutMapping("{idPersonne}/competence")
    public Personne ajouterCompetence(@Parameter(description = "Id de la personne concernée par l'ajout") @PathVariable Long idPersonne,
                                      @RequestBody NiveauCompetence nouvelleCompetence) {
        return personneService.ajouterCompetence(idPersonne, nouvelleCompetence);
    }

    @Operation(summary = "Permet d'ajouter un niveau de compétence à une personne via l'id du niveau de compétence")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personne mise à jour",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Personne.class))}),
            @ApiResponse(responseCode = "400", description = "Id fourni invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Personne ou Niveau de compétence non trouvée")
    })
    @PutMapping("{idPersonne}/competence/{idCompetence}")
    public Personne ajouterCompetence(@Parameter(description = "Id de la personne concernée par l'ajout") @PathVariable Long idPersonne,
                                      @Parameter(description = "Id du niveau de compétence à ajouter")@PathVariable Long idCompetence) {
        return personneService.ajouterCompetence(idPersonne, idCompetence);
    }

    @Operation(summary = "Afficher tous les niveaux de compétences accessibles à une personne")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Niveaux de compétences accessibles trouvées",
                    content = @Content( array = @ArraySchema(schema = @Schema(implementation = NiveauCompetence.class)))),
            @ApiResponse(responseCode = "400", description = "Id fourni invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Personne non trouvée")
    })
    @GetMapping("{idPersonne}/competencesAccessibles")
    public List<NiveauCompetence> findPrerequisByPersonne(@Parameter(description = "Id de la personne concernée par la recherche") @PathVariable Long idPersonne) {
        return personneService.findPrerequisByPersonne(idPersonne);
    }

    @Operation(summary = "Afficher toutes les personnes qui possède un niveau de compétence supérieur au niveau donné pour une compétence donnée")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personnes qualifiées trouvées",
                    content = @Content( array = @ArraySchema(schema = @Schema(implementation = Personne.class)))),
            @ApiResponse(responseCode = "400", description = "Id fourni invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Compétence non trouvée")
    })
    @GetMapping("/niveau/{niveau}/competence/{idCompetence}")
    public List<Personne> findPersonneNiveauSuperieurCompetence(@Parameter(description = "Le niveau borne pour la recherche") @PathVariable int niveau,
                                                                @Parameter(description = "Id de la compétence recherchée") @PathVariable Long idCompetence) {
        return personneService.findPersonneNiveauSuperieurCompetence(niveau, idCompetence);
    }
}

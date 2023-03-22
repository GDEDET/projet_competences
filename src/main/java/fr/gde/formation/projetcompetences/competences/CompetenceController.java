package fr.gde.formation.projetcompetences.competences;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("competences")
@Tag(name = "Compétence", description = "L'API des compétences")
public class CompetenceController {

    private final CompetenceService competenceService;

    public CompetenceController(CompetenceService competenceService) {
        this.competenceService = competenceService;
    }

    @Operation(summary = "Créer une compétence et initialiser les niveaux associés à celle-ci")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Compétence créée",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Competence.class))}),
            @ApiResponse(responseCode = "409", description = "Une compétence avec ce nom existe déjà")
    })
    @PostMapping
    public Competence save(@RequestBody Competence competence) {
        return competenceService.initCompetenceAvecSesNiveaux(competence);
    }

    @Operation(summary = "Met à jour une compétence")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compétence mise à jour",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Competence.class))})
    })
    @PutMapping
    public Competence modifier(@RequestBody Competence competence) {
        return competenceService.update(competence);
    }

    @Operation(summary = "Trouver une compétence via son Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compétence trouvée",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Competence.class))}),
            @ApiResponse(responseCode = "400", description = "Id fourni invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Compétence non trouvée")
    })
    @GetMapping("{id}")
    public Competence findById(@Parameter(description = "Id de la compétence recherchée") @PathVariable Long id) {
        return competenceService.findById(id);
    }

    @Operation(summary = "Afficher toutes les compétences")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compétences trouvées",
                    content = @Content( array = @ArraySchema(schema = @Schema(implementation = Competence.class))))
    })
    @GetMapping
    public Iterable<Competence> findAll() {
        return competenceService.findAll();
    }

    @Operation(summary = "Supprimer une compétence via son Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Compétence supprimée"),
            @ApiResponse(responseCode = "400", description = "Id fourni invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Compétence non trouvée")
    })
    @DeleteMapping("{id}")
    public void deleteById(@Parameter(description = "Id de la compétence à supprimer") @PathVariable Long id) {
        competenceService.deleteById(id);
    }
}

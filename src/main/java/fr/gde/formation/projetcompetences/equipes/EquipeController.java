package fr.gde.formation.projetcompetences.equipes;

import fr.gde.formation.projetcompetences.personnes.Personne;
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
@RequestMapping("equipes")
@Tag(name = "Equipe", description = "L'API d'équipe")
public class EquipeController {

    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @Operation(summary = "Créer une équipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Equipe créée",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipe.class))})
    })
    @PostMapping
    public Equipe save(@RequestBody Equipe equipe) {
        return equipeService.save(equipe);
    }

    @Operation(summary = "Met à jour une équipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipe mise à jour",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipe.class))})
    })
    @PutMapping
    public Equipe modifier(@RequestBody Equipe equipe) {
        return equipeService.update(equipe);
    }

    @Operation(summary = "Trouver une équipe via son Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipe trouvée",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipe.class))}),
            @ApiResponse(responseCode = "400", description = "Id fourni invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Equipe non trouvée")
    })
    @GetMapping("{id}")
    public Equipe findById(@Parameter(description = "Id de l'équipe à afficher") @PathVariable Long id) {
        return equipeService.findById(id);
    }

    @Operation(summary = "Afficher toutes les équipes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipes trouvées",
                    content = @Content( array = @ArraySchema(schema = @Schema(implementation = Equipe.class))))
    })
    @GetMapping
    public Iterable<Equipe> findAll() {
        return equipeService.findAll();
    }

    @Operation(summary = "Supprimer une équipe via son Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Equipe supprimée"),
            @ApiResponse(responseCode = "400", description = "Id fourni invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Equipe non trouvée")
    })
    @DeleteMapping("{id}")
    public void deleteById(@Parameter(description = "Id de l'équipe à supprimer") @PathVariable Long id) {
        equipeService.deleteById(id);
    }

    @Operation(summary = "Permet d'ajouter une personne à une équipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipe mise à jour",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipe.class))}),
            @ApiResponse(responseCode = "400", description = "Id fourni invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Equipe non trouvée")
    })
    @PutMapping("{idEquipe}/membres")
    public Equipe ajouterMembre(@Parameter(description = "Id de l'équipe concerné par l'ajout") @PathVariable Long idEquipe,
                                @RequestBody Personne personne
    ) {
        return equipeService.ajouterMembre(idEquipe, personne);
    }

    @Operation(summary = "Permet d'ajouter une personne à une équipe via l'id de la personne")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipe mise à jour",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipe.class))}),
            @ApiResponse(responseCode = "400", description = "Id fourni invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Equipe ou personne non trouvée")
    })
    @PutMapping("{idEquipe}/membres/{idPersonne}")
    public Equipe ajouterMembre(@Parameter(description = "Id de l'équipe concerné par l'ajout") @PathVariable Long idEquipe,
                                @Parameter(description = "Id de la personne à ajouter") @PathVariable Long idPersonne) {
        return equipeService.ajouterMembre(idEquipe, idPersonne);
    }


}

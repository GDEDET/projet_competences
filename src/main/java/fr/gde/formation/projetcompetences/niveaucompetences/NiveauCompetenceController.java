package fr.gde.formation.projetcompetences.niveaucompetences;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("niveaux-competences")
@Secured("MANAGER")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Niveau de Compétence", description = "L'API de gestion des niveaux de compétences")
public class NiveauCompetenceController {

    private final NiveauCompetenceService niveaucompetenceService;

    public NiveauCompetenceController(NiveauCompetenceService niveaucompetenceService) {
        this.niveaucompetenceService = niveaucompetenceService;
    }

    @Operation(summary = "Permet d'ajouter un prérequis à un niveau de compétence")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Niveau de compétence mise à jour",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = NiveauCompetence.class))}),
            @ApiResponse(responseCode = "400", description = "Id fourni invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Niveau de compétence non trouvé")
    })
    @PutMapping("{idCompetence}/prerequis")
    public NiveauCompetence ajouterPrerequis(@RequestBody NiveauCompetence prerequis,
                                             @Parameter(description = "Id du niveau de compétence concerné par l'ajout") @PathVariable Long idCompetence) {
        return this.niveaucompetenceService.addPrerequis(prerequis, idCompetence);
    }

    @Operation(summary = "Permet d'ajouter un prérequis à un niveau de compétence via l'Id du prérequis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Niveau de compétence mise à jour",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = NiveauCompetence.class))}),
            @ApiResponse(responseCode = "400", description = "Id fourni invalide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Niveau de compétence ou Prérequis non trouvé")
    })
    @PutMapping("{idCompetence}/prerequis/{idPrerequis}")
    public NiveauCompetence ajouterPrerequis(@Parameter(description = "Id du niveau de compétence concerné par l'ajout") @PathVariable Long idCompetence,
                                             @Parameter(description = "Id du prérequis à ajouter") @PathVariable Long idPrerequis) {
        return this.niveaucompetenceService.addPrerequis(idCompetence, idPrerequis);
    }


}

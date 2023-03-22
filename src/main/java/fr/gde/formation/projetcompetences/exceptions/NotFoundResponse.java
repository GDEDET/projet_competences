package fr.gde.formation.projetcompetences.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Message de retour dans le cas ou une ressource n'est pas trouvée.
 * Elle prend alors la forme:
 * <code>
 *     {
 *         "status":"Not found",
 *         "timestamp":"<date et heure où l'exception à eu lieu>",
 *         "message":"<message contenue dans l'exception"
 *     }
 * </code>
 * @see NotFoundControllerAdvice
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundResponse {
    private String status = "Not found";
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;

    public NotFoundResponse(String message) {
        this.message = message;
    }
}

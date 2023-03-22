package fr.gde.formation.projetcompetences.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller Advice permettant la gestion des exceptions NotFoundexception générés pas l'API.
 * Quand un controller retourne une exception de type NotFoundException, la méthode
 * {@link NotFoundControllerAdvice::handleException} est execute.  <br>
 */
@Slf4j
@ControllerAdvice
public class NotFoundControllerAdvice {

    /**
     * Méthode appelée quand une exception de type NoFoundException est levé, mais pas géré par le code.
     * @param exception instance de l'exception levée
     * @return un objet json à sérialiser pour le retour à l'utilisateur ayant fait la requête.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value= {NotFoundException.class})
    public ResponseEntity<NotFoundResponse> handleException(RuntimeException exception){
        log.warn(exception.getMessage());
        return new ResponseEntity<>(
                new NotFoundResponse(exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}


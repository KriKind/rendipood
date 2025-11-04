package ee.kristiina.rendipood.exception;

import ee.kristiina.rendipood.model.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@ControllerAdvice
public class ControlAdviseHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(RuntimeException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setStatus(400);
        errorMessage.setTimestamp(new Date());
        return ResponseEntity.status(400).body(errorMessage);
    }
}

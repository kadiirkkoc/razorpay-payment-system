package payment.service.razopay.integration.excepitons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler({NotFoundExceptions.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundExceptions ex){
        ErrorResponse errorDetails = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceAlreadyExistsException.class})
    public ResponseEntity<Object> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex){
        ErrorResponse errorDetails = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
}

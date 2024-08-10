package fullstack.student.exception.handler;

import fullstack.student.exception.FuncErrorException;
import fullstack.student.exception.NotFoundException;
import fullstack.student.models.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(
                new ResponseObject(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null)
        );
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponseObject> handleNotFoundException(NotFoundException nfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(nfe.getMessage(), HttpStatus.NOT_FOUND.value(), null)
        );
    }

    @ExceptionHandler(value = FuncErrorException.class)
    public ResponseEntity<ResponseObject> handleFuncErrorException(FuncErrorException fex){
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject(fex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null)
        );
    }
}

package ir.mosi.airline.reservation.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AirplaneNotFoundException extends Exception {
    public AirplaneNotFoundException(String message){
        super(message);
    }
}

package ir.mosi.airline.reservation.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PassengerNotFoundException extends Exception {
    public PassengerNotFoundException(String message) {
        super(message);
    }
}

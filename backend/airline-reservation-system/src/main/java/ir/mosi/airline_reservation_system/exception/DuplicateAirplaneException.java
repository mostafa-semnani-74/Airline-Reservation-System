package ir.mosi.airline_reservation_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DuplicateAirplaneException extends Exception {
    public DuplicateAirplaneException(String message) {
        super(message);
    }
}

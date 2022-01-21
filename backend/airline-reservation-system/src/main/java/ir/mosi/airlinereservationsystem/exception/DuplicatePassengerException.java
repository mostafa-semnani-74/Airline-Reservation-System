package ir.mosi.airlinereservationsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DuplicatePassengerException extends Exception {
    public DuplicatePassengerException(String message) {
        super(message);
    }
}

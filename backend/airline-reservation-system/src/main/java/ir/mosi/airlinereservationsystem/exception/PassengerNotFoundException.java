package ir.mosi.airlinereservationsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Passenger not found")
public class PassengerNotFoundException extends Exception {

}

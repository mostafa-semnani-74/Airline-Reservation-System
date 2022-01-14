package ir.mosi.airline_reservation_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Airplane not found")
public class AirplaneNotFoundException extends Exception {

}

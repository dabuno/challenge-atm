package ro.challenge.atm;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Employee Not Found") //404
public class AmountException extends Exception {
    public AmountException(BigDecimal amount){
        super("amount with id="+amount + "is invalid");
    }

    public AmountException(String message){
        super(message);
    }
}
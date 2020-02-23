package ro.challenge.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class ATM {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(ATM.class, args);
    }
}

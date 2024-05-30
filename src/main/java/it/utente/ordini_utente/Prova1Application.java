package it.utente.ordini_utente;
//Thymeleaf	Q
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"it.utente.RestController","it.utente.database","it.utente.Controller"})
public class Prova1Application {

	public static void main(String[] args) {
		SpringApplication.run(Prova1Application.class, args);
	}

}

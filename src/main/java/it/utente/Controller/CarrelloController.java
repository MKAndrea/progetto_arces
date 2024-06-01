package it.utente.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.utente.tabelleDatabase.Carrello;

@Controller
public class CarrelloController {
	@Autowired
	 private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/carrello")
	public String carrello(Model model) {
		   List<Carrello> carrello = jdbcTemplate.query("SELECT * FROM carrello", (rs, rowNum) -> {
	           Carrello c = new Carrello();
	           c.setId(rs.getInt("idcarrello"));
	           c.setIdutente(rs.getInt("idutente"));
	           c.setIdshops(rs.getInt("idshops"));
	           c.setData_creazione(rs.getString("data_creazione"));
	           c.setData_acquisto(rs.getString("data_acquisto"));
	           c.setPayment_code(rs.getString("payment_code"));
	           return c;
	       });
	       model.addAttribute("carrello", carrello);
	       return "carrello"; // Questo deve corrispondere al nome del file HTML in src/main/resources/templates senza l'estensione
	   }

	@GetMapping("/carrello/update/{id}")
	public String FormUpdateCarrello(@PathVariable int id, @ModelAttribute("carrello") Carrello carrello) {
		    return "carrello_edit";  // Assicurati che il nome della vista sia corretto
		}



	@PostMapping("/carrello/update/{id}/modifica")
	public String UpdateCarrello(@PathVariable int id,
	                             @RequestParam("idutente") int idutente,
	                             @RequestParam("idshops") int idshops,
	                             @RequestParam("data_creazione") String data_creazione,
	                             @RequestParam("data_acquisto") String data_acquisto,
	                             @RequestParam("payment_code") String payment_code
	                            ) {
	    jdbcTemplate.update(
	        "UPDATE carrello SET  idutente = ?, idshops = ?, data_creazione = ?, data_acquisto = ?, payment_code = ? "
	        + "WHERE idcarrello = ?",
	       idutente,idshops,data_creazione,data_acquisto,payment_code,id);
	    return "redirect:/carrello";
	}

	@GetMapping("/carrello/insert")
	public String FormInserisciCarrello(@ModelAttribute("carrello") Carrello carrello) {
	    return "carrello_insert";  // Assicurati che il nome della vista sia corretto
	}


	@PostMapping("/carrello/insert/inserisci")
	public String InserisciCarrello( @RequestParam("idutente") int idutente,
            @RequestParam("idshops") int idshops,
            @RequestParam("data_creazione") String data_creazione,
            @RequestParam("data_acquisto") String data_acquisto,
            @RequestParam("payment_code") String payment_code) {
	    jdbcTemplate.update(
	        "INSERT INTO carrello (idutente, idshops,data_creazione,data_acquisto,payment_code) VALUES(?,?,?,?,?)",
	        idutente,idshops,data_creazione,data_acquisto,payment_code);
	    return "redirect:/carrello";
	}

	@PostMapping("/carrello/delete/{id}")
	public String CancellaCarrello(@PathVariable int id) {
	   jdbcTemplate.update(
	         "DELETE FROM carrello WHERE idcarrello = ?",
	         id);
	   return "redirect:/carrello";
	}
}

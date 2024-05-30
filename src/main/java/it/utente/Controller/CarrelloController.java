package it.utente.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	           c.setIdshops(rs.getInt("idshops"));
	           c.setIdprodotto(rs.getInt("idprodotto"));
	           c.setQuantita(rs.getInt("quantita"));
	           return c;
	       });
	       model.addAttribute("carrello", carrello);
	       return "carrello"; // Questo deve corrispondere al nome del file HTML in src/main/resources/templates senza l'estensione
	   }

	@GetMapping("/carrello/update/{id}")
	public String FormUpdateCarrello(@PathVariable int id, Model model) {
		    model.addAttribute("carrello", new Carrello());
		    return "carrello_edit";  // Assicurati che il nome della vista sia corretto
		}



	@PostMapping("/carrello/update/{id}/modifica")
	public String UpdateCarrello(@PathVariable int id, @RequestParam("idshops") int idshops,
			@RequestParam("idprodotto") int idprodotto,@RequestParam("quantita")int quantita) {
	   jdbcTemplate.update(
	         "UPDATE carrello SET idshops = ?, idprodotto = ?,quantita = ? WHERE idcarrello = ?",
	         idshops,idprodotto,quantita,id);
	   return "redirect:/carrello";
	}

	@GetMapping("/carrello/insert")
	public String FormInserisciCarrello(Model model) {
	    model.addAttribute("carrello", new Carrello());
	    return "carrello_insert";  // Assicurati che il nome della vista sia corretto
	}


	@PostMapping("/carrello/insert/inserisci")
	public String InserisciCarrello(@RequestParam("idshops") int idshops,
			@RequestParam("idprodotto") int idprodotto,@RequestParam("quantita")int quantita) {
	    jdbcTemplate.update(
	        "INSERT INTO carrello(idshops, idprodotto,quantita) VALUES(?,?,?)",
	        idshops,idprodotto,quantita);
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

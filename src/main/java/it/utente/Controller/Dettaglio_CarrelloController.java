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

import it.utente.tabelleDatabase.Dettaglio_Carrello;

@Controller
public class Dettaglio_CarrelloController {
	@Autowired
	 private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/dettaglio_carrello")
	public String carrello(Model model) {
		   List<Dettaglio_Carrello> dettaglio_carrello = jdbcTemplate.query("SELECT * FROM dettaglio_carrello", (rs, rowNum) -> {
	           Dettaglio_Carrello dc = new Dettaglio_Carrello();
	           dc.setId(rs.getInt("idDettaglio"));
	           dc.setIdcarrello(rs.getInt("idcarrello"));
	           dc.setIdProdotto(rs.getInt("idProdotto"));
	           dc.setQuantita(rs.getInt("quantita"));
	           return dc;
	       });
	       model.addAttribute("dettaglio_carrello", dettaglio_carrello);
	       return "dettaglio_carrello"; // Questo deve corrispondere al nome del file HTML in src/main/resources/templates senza l'estensione
	   }

	@GetMapping("/dettaglio_carrello/update/{id}")
	public String FormUpdateCarrello(@PathVariable int id, @ModelAttribute("dettaglio_carrello") Dettaglio_Carrello dettaglioCarrello) {
		    return "dettaglio_carrello_edit";  // Assicurati che il nome della vista sia corretto
		}



	@PostMapping("/dettaglio_carrello/update/{id}/modifica")
	public String UpdateCarrello(@PathVariable int id,
	                             @RequestParam("idcarrello") int idcarrello,
	                             @RequestParam("idProdotto") int idProdotto,
	                             @RequestParam("Quantita") int quantita,
	                             @ModelAttribute("dettaglio_carrello") Dettaglio_Carrello dettaglioCarrello) {
	    jdbcTemplate.update(
	        "UPDATE dettaglio_carrello SET idcarrello = ?, idProdotto = ?, Quantita = ? WHERE idDettaglio = ?",
	        dettaglioCarrello.getIdcarrello(),dettaglioCarrello.getIdProdotto(),dettaglioCarrello.getQuantita(),dettaglioCarrello.getId());
	    return "redirect:/dettaglio_carrello";
	}

	@GetMapping("/dettaglio_carrello/insert")
	public String FormInserisciCarrello(Model model) {
	    model.addAttribute("dettaglio_carrello", new Dettaglio_Carrello());
	    return "dettaglio_carrello_insert";  // Assicurati che il nome della vista sia corretto
	}


	@PostMapping("/dettaglio_carrello/insert/inserisci")
	public String InserisciCarrello(@RequestParam("idcarrello") int idcarrello,
			@RequestParam("idProdotto") int idProdotto,@RequestParam("Quantita")int quantita) {
	    jdbcTemplate.update(
	        "INSERT INTO dettaglio_carrello (idcarrello, idProdotto,Quantita) VALUES(?,?,?)",
	        idcarrello,idProdotto,quantita);
	    return "redirect:/dettaglio_carrello";
	}

	@PostMapping("/dettaglio_carrello/delete/{id}")
	public String CancellaCarrello(@PathVariable int id) {
	   jdbcTemplate.update(
	         "DELETE FROM dettaglio_carrello WHERE idDettaglio = ?",
	         id);
	   return "redirect:/dettaglio_carrello";
	}
}

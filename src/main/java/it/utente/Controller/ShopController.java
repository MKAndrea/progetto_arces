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

import it.utente.tabelleDatabase.Shop;

@Controller
public class ShopController {
	@Autowired
	 private JdbcTemplate jdbcTemplate;
	@GetMapping("/shops")
	public String listaShops(Model model) {
		   List<Shop> shop = jdbcTemplate.query("SELECT * FROM shops", (rs, rowNum) -> {
	           Shop s = new Shop();
	           s.setId(rs.getInt("idshops"));
	           s.setDenominazione(rs.getString("denominazione"));
	           s.setIndirizzo(rs.getString("indirizzo"));
	           return s;
	       });
	       model.addAttribute("shops", shop);
	       return "shop"; // Questo deve corrispondere al nome del file HTML in src/main/resources/templates senza l'estensione
	   }

	@GetMapping("/shops/update/{id}")
	public String FormUpdateShops(@PathVariable int id, Model model) {
		    model.addAttribute("shops", new Shop());
		    return "shop_edit";  // Assicurati che il nome della vista sia corretto
		}



	@PostMapping("/shops/update/{id}/modifica")
	public String UpdateShops(@PathVariable int id, @RequestParam("denominazione") String denominazione,@RequestParam("indirizzo") String indirizzo) {
	   jdbcTemplate.update(
	         "UPDATE shops SET denominazione = ?, indirizzo = ? WHERE idshops = ?",
	         denominazione,indirizzo,id);
	   return "redirect:/shops";
	}

	@GetMapping("/shops/insert")
	public String FormInserisciShop(Model model) {
	    model.addAttribute("shops", new Shop());
	    return "shop_insert";  // Assicurati che il nome della vista sia corretto
	}


	@PostMapping("/shops/insert/inserisci")
	public String InserisciShops(@RequestParam("denominazione") String denominazione,
	                              @RequestParam("indirizzo") String indirizzo) {
	    jdbcTemplate.update(
	        "INSERT INTO shops(denominazione, indirizzo) VALUES(?, ?)",
	      denominazione, indirizzo);
	    return "redirect:/shops";
	}

	@PostMapping("/shops/delete/{id}")
	public String CancellaShops(@PathVariable int id) {
	   jdbcTemplate.update(
	         "DELETE FROM shops WHERE idshops = ?",
	         id);
	   return "redirect:/shops";
	}
}

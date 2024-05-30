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

import it.utente.tabelleDatabase.Product;

@Controller
public class ProductController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/products")
	public String listqProdotti(Model model) {
		List<Product> product = jdbcTemplate.query("SELECT * FROM prodotti", (rs, rowNum) -> {
			Product p = new Product();
			p.setId(rs.getInt("idProdotto"));
			p.setnome_prodotto(rs.getString("nome_prodotto"));
			p.setPrezzo(rs.getInt("prezzo"));
			p.setIdshop(rs.getInt("idshop"));
			return p;
		});
		model.addAttribute("products", product);
		return "product"; // Questo deve corrispondere al nome del file HTML in
						// src/main/resources/templates senza l'estensione
	}

	@GetMapping("/products/update/{id}")
	public String FormUpdateProdotti(@PathVariable int id, Model model) {
		model.addAttribute("products", new Product());
		return "product_edit"; // Assicurati che il nome della vista sia corretto

	}

	@PostMapping("/products/update/{id}/modifica")
	public String UpdateProdotti(@PathVariable int id, @RequestParam("nome_prodotto") String nome_prodotto,
			@RequestParam("prezzo") int prezzo, @RequestParam("idshop") int idshop) {
		jdbcTemplate.update("UPDATE prodotti SET nome_prodotto = ?, prezzo = ?, idshop=? WHERE idProdotto = ?", nome_prodotto,
				prezzo,idshop, id);
		return "redirect:/products";
	}

	@GetMapping("/products/insert")
	public String FormInserisciProdotto(Model model) {
		model.addAttribute("products", new Product());
		return "product_insert"; // Assicurati che il nome della vista sia corretto
	}

	@PostMapping("/products/insert/inserisci")
	public String InserisciProdotto( @RequestParam("nome_prodotto") String nome_prodotto,
			@RequestParam("prezzo") int prezzo, @RequestParam("idshop") int idshop) {
		jdbcTemplate.update("INSERT INTO shops(nome_prodotto,prezzo,idshop) VALUES(?, ?,?)", nome_prodotto, prezzo,idshop);
		return "redirect:/products";
	}

	@PostMapping("/products/delete/{id}")
	public String CancellaProdotto(@PathVariable int id) {
		jdbcTemplate.update("DELETE FROM prodotti WHERE idProdotto = ?", id);
		return "redirect:/products";
	}

}

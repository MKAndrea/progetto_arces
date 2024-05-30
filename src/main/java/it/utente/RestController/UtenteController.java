package it.utente.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.utente.tabelleDatabase.Utente;

@Controller
public class UtenteController {
	@Autowired
	 private JdbcTemplate jdbcTemplate;
	
@GetMapping("/utenti")
public String listaUtenti(Model model) {
	   List<Utente> utenti = jdbcTemplate.query("SELECT * FROM users", (rs, rowNum) -> {
           Utente ut = new Utente();
           ut.setId(rs.getInt("idutente"));
           ut.setNome(rs.getString("nome"));
           ut.setCognome(rs.getString("cognome"));
           ut.setUsername(rs.getString("username"));
           ut.setCellulare(rs.getString("cellulare"));
           ut.setIndirizzo(rs.getString("indirizzo"));
           ut.setEta(rs.getInt("eta"));
           return ut;
       });
       model.addAttribute("utenti", utenti);
       return "utenti"; // Questo deve corrispondere al nome del file HTML in src/main/resources/templates senza l'estensione
   }

@GetMapping("/utenti/update/{id}")
public String UtentiperID(@PathVariable int id, Model model) {
    @SuppressWarnings("deprecation")
	Utente utente = jdbcTemplate.queryForObject(
        "SELECT idutente, nome, cognome, username, cellulare, indirizzo, eta FROM users WHERE idutente = ?",
        new Object[]{id},
        (rs, rowNum) -> {
            Utente ut = new Utente();
            ut.setId(rs.getInt("idutente"));
            ut.setNome(rs.getString("nome"));
            ut.setCognome(rs.getString("cognome"));
            ut.setUsername(rs.getString("username"));
            ut.setCellulare(rs.getString("cellulare"));
            ut.setIndirizzo(rs.getString("indirizzo"));
            ut.setEta(rs.getInt("eta"));
            return ut;
        }
    );
    if (utente != null) {
        model.addAttribute("utente", utente); // Nota che sto usando "utente" invece di "utenti" per un singolo utente
        return "form_edit";  
    } else {
        // Gestisci il caso in cui l'utente non è stato trovato, ad esempio reindirizzando a una pagina di errore
        return "utente_non_trovato";
    }

}


@PostMapping("/utenti/update/{id}/modifica")
public String UpdateUtente(@PathVariable int id, @RequestParam("nome") String nome,@RequestParam("cognome") String cognome
		,@RequestParam("username") String username,@RequestParam("cellulare") String cellulare,
		@RequestParam("indirizzo") String indirizzo,@RequestParam("eta")int eta) {
   jdbcTemplate.update(
         "UPDATE users SET nome = ?, cognome = ?, username=?,cellulare=?,indirizzo=?, eta=? WHERE idutente = ?",
         nome,cognome,username,cellulare,indirizzo,eta,id);
   return "redirect:/utenti";
}

@GetMapping("/utenti/insert")
public String mostraFormInserimento(Model model) {
    model.addAttribute("utente", new Utente());
    return "form_insert";  // Assicurati che il nome della vista sia corretto
}


@PostMapping("/utenti/insert/inserisci")
public String InserisciUtente(@RequestParam("nome") String nome,
                              @RequestParam("cognome") String cognome,
                              @RequestParam("username") String username,
                              @RequestParam("cellulare") String cellulare,
                              @RequestParam("indirizzo") String indirizzo,
                              @RequestParam("eta") int eta) {
    jdbcTemplate.update(
        "INSERT INTO users(nome, cognome, username, cellulare, indirizzo, eta) VALUES(?, ?, ?, ?, ?, ?)",
       nome, cognome, username, cellulare, indirizzo, eta);
    return "redirect:/utenti";
}

@PostMapping("/utenti/delete/{id}")
public String CancellaUtente(@PathVariable int id) {
   jdbcTemplate.update(
         "DELETE FROM users WHERE idutente = ?",
         id);
   return "redirect:/utenti";
}

}


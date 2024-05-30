package it.utente.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import it.utente.tabelleDatabase.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import it.utente.database.*;
import it.utente.tabelleDatabase.Utente;
@RestController
public class UtenteRestController {
	@Autowired
	 private JdbcTemplate jdbcTemplate;
	
	
	
	@GetMapping("/home")
	public String ciaoMondo() {
		return "Ciao Mondo";
	}
	
	
//	@ResponseBody
//	private List<Utente> listaUtenti(Model model) throws Exception{
//	List<Utente> utenti;
//	utenti=jdbcTemplate.query("select * from utenti", (rs,rowId)->{
//		return new Utente(rs.getInt("id"),rs.getString("nome"),rs.getString("cognome"),rs.getString("username"),rs.getString("cellulare"),rs.getString("indirizzo"),rs.getInt("eta"));
//	});
//	return utenti;
//	}
	
	 @GetMapping("/utente")
	 @ResponseBody
	 public List<Utente> listaUtenti(Model model) {
	   return jdbcTemplate.query("SELECT * FROM users", (rs, rowNum) -> {
    Utente utente = new Utente();
    utente.setId(rs.getInt("idutente"));
    utente.setNome(rs.getString("nome"));
    utente.setCognome(rs.getString("cognome"));
    utente.setUsername(rs.getString("username"));
    utente.setCellulare(rs.getString("cellulare"));
    utente.setIndirizzo(rs.getString("indirizzo"));
    utente.setEta(rs.getInt("eta"));
    return utente;
	   });
	 }
	 
	@GetMapping("/utente/{id}")
    @ResponseBody
    public Utente UtentiperID(@PathVariable int id) {
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
        return utente;
    }
	@PostMapping("/utente/insert")
	@ResponseBody
	public List<Utente> inserisciUtente(@RequestBody Utente utente) {
	    jdbcTemplate.update("INSERT INTO users(nome, cognome, username, cellulare, indirizzo, eta) VALUES (?, ?, ?, ?, ?, ?)",
	        utente.getNome(), utente.getCognome(), utente.getUsername(), utente.getCellulare(), utente.getIndirizzo(), utente.getEta());

	    // Dopo l'inserimento, puoi semplicemente restituire l'elenco aggiornato degli utenti
	    List<Utente> utenti = jdbcTemplate.query("SELECT * FROM users", (rs, rowNum) -> {
	        Utente u = new Utente();
	        u.setId(rs.getInt("idutente"));
	        u.setNome(rs.getString("nome"));
	        u.setCognome(rs.getString("cognome"));
	        u.setUsername(rs.getString("username"));
	        u.setCellulare(rs.getString("cellulare"));
	        u.setIndirizzo(rs.getString("indirizzo"));
	        u.setEta(rs.getInt("eta"));
	        return u;
	    });

	    return utenti;
	}
	@PostMapping("/utente/update")
	@ResponseBody
	public List<Utente> UpdateUtente(@RequestBody Utente utente) {
	    jdbcTemplate.update("UPDATE users SET nome = ?, cognome = ?, username=?,cellulare=?,indirizzo=?, eta=? WHERE idutente = ?",
	        utente.getNome(), utente.getCognome(), utente.getUsername(), utente.getCellulare(), utente.getIndirizzo(), utente.getEta(),utente.getId());

	    // Dopo l'inserimento, puoi semplicemente restituire l'elenco aggiornato degli utenti
	    List<Utente> utenti = jdbcTemplate.query("SELECT * FROM users", (rs, rowNum) -> {
	        Utente u = new Utente();
	        u.setId(rs.getInt("idutente"));
	        u.setNome(rs.getString("nome"));
	        u.setCognome(rs.getString("cognome"));
	        u.setUsername(rs.getString("username"));
	        u.setCellulare(rs.getString("cellulare"));
	        u.setIndirizzo(rs.getString("indirizzo"));
	        u.setEta(rs.getInt("eta"));
	        return u;
	    });

	    return utenti;
	}

}

	 


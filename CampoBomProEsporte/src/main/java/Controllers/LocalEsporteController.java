package Controllers;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Models.Esporte;
import Models.Local;
import Models.LocalEsporte;
import Persistencia.LocalDAO;
import Persistencia.LocalEsporteDAO;
@CrossOrigin
@RestController
@RequestMapping("/localesportes/")

public class LocalEsporteController {
	
	//instancia DAO
	LocalEsporteDAO  leDAO = new LocalEsporteDAO();
	LocalDAO localDAO = new LocalDAO();
	
	//busca todos esportes daquele local
	@GetMapping("esporte/{id}")
	public ArrayList<Esporte> getEsporte(@PathVariable(name = "id") Integer id) {
		return leDAO.buscarPorLocal(localDAO.buscarPorId(id));
	}
	
	//busca todos locais com aquele esporte
	@GetMapping("local")
	public ArrayList<Local> getLocal(@RequestBody Esporte esporte) {
		return leDAO.buscarPorEsporte(esporte);
	}
	
	//registra que nesse local ha esse esporte
	@PostMapping
	public ResponseEntity<Boolean> setLocalEsporte(@RequestBody LocalEsporte le) {
		return ResponseEntity.ok(leDAO.salvar(le));
	}
	
	//registra que não ha nesse local esse esporte
	@DeleteMapping
	public ResponseEntity<Boolean> excluir(@RequestBody LocalEsporte le) {
		return ResponseEntity.ok(leDAO.excluir(le));
	}
}
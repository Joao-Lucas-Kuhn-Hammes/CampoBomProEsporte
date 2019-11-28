package Controllers;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Models.Local;
import Persistencia.LocalDAO;
@CrossOrigin
@RestController
@RequestMapping("/local/")

public class LocalControllers {
	
	//instancia DAO
	LocalDAO localDAO = new LocalDAO();
	
	//busca por id
	@GetMapping("{id}")
	public Local getLocal(@PathVariable(name = "id") Long id) {
		return localDAO.buscarPorId(id);
	}
	
	//busca todos
	@GetMapping()
	public ArrayList<Local> getLocalTodos() {
		return localDAO.buscarTodos();
	}
	
	//busca todos locais criados pelo usuario
	@GetMapping("usuario/{id}")
	public ArrayList<Local> getLocalTodosUsuario(@PathVariable(name = "id") Long id) {
		return localDAO.buscarTodosUsuario(id);
	}
	
	//cria novo local
	@PostMapping
	public ResponseEntity<Local> setEsporte(@RequestBody Local novo) {
		return ResponseEntity.ok(localDAO.salvar(novo));
	}
	
	//edita local
	@PutMapping()
	public ResponseEntity<Local> atualizar(@RequestBody Local novo) {
		return ResponseEntity.ok(localDAO.editar(novo));
	}
	
	//deleta local
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletar(@PathVariable Long id) {
		return ResponseEntity.ok(localDAO.excluir(id));
	}

}

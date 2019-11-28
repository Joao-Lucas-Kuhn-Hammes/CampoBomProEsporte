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

import Models.Esporte;
import Persistencia.EsporteDAO;
@CrossOrigin
@RestController
@RequestMapping("/esportes/")

public class EsportesControllers {
	
	//instancia DAO
	EsporteDAO esporteDAO = new EsporteDAO();
	
	//busca por id
	@GetMapping("{id}")
	public Esporte getEsporte(@PathVariable(name = "id") Long id) {
		return	 esporteDAO.buscarPorId(id);
	}
	
	//busca todos
	@GetMapping()
	public ArrayList<Esporte> getEsporteTodos() {
		return esporteDAO.buscarTodos();
	}
	
	//busca todos esportes daquele usuario
	@GetMapping("usuario/{id}")
	public ArrayList<Esporte> getEsporteTodosUsuario(@PathVariable(name = "id") Long id) {
		return esporteDAO.buscarTodosUsuario(id);
	}
	
	//cria um novo esporte
	@PostMapping
	public ResponseEntity<Esporte> setEsporte(@RequestBody Esporte novo) {
		return ResponseEntity.ok(esporteDAO.salvar(novo));
	}
	
	//edita esporte
	@PutMapping()
	public ResponseEntity<Esporte> atualizar(@RequestBody Esporte novo) {
		return ResponseEntity.ok(esporteDAO.editar(novo));
	}
	
	//deleta esporte
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletar(@PathVariable Long id) {
		return ResponseEntity.ok(esporteDAO.excluir(id));
	}

}
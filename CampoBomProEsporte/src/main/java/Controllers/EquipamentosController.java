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

import Models.Equipamento;
import Persistencia.EquipamentosDAO;
@CrossOrigin
@RestController
@RequestMapping("/equipamentos/")

public class EquipamentosController {
	
	EquipamentosDAO equipDAO = new EquipamentosDAO();
	
	@GetMapping("{id}")
	public Equipamento getEquipamento(@PathVariable(name = "id") Long id) {
		return equipDAO.buscarPorId(id);
	}
	
	@GetMapping()
	public ArrayList<Equipamento> getEquipamentoTodos() {
		return equipDAO.buscarTodos();
	}
	
	@GetMapping("usuario/{id}")
	public ArrayList<Equipamento> getEquipamentoTodosUsuario(@PathVariable(name = "id") Long id) {
		return equipDAO.buscarTodosUsuario(id);
	}
	
	@PostMapping
	public ResponseEntity<Equipamento> setEquipamentos(@RequestBody Equipamento novo) {
		return ResponseEntity.ok(equipDAO.salvar(novo));
	}
	
	@PutMapping()
	public ResponseEntity<Equipamento> atualizar(@RequestBody Equipamento novo) {
		return ResponseEntity.ok(equipDAO.editar(novo));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletar(@PathVariable Long id) {
		return ResponseEntity.ok(equipDAO.excluir(id));
	}

}

package Controllers;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Models.Equipamento;
import Models.EquipamentosLocal;
import Models.Local;
import Persistencia.EquipamentosLocalDAO;
@CrossOrigin
@RestController
@RequestMapping("/equipamentoslocal/")

public class EquipamentosLocalController {
	
	EquipamentosLocalDAO  elDAO = new EquipamentosLocalDAO();
	
	@GetMapping("local")
	public ArrayList<Local> getLocal(@RequestBody Equipamento equip) {
		return elDAO.buscarPorEquipamento(equip);
	}
	
	@GetMapping("equipamento")
	public ArrayList<Equipamento> getEquipamento(@RequestBody Local local) {
		return elDAO.buscarPorLocal(local);
	}
	
	@PostMapping
	public ResponseEntity<Boolean> setEquipamentoslocal(@RequestBody EquipamentosLocal el) {
		return ResponseEntity.ok(elDAO.salvar(el));
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> excluir(@RequestBody EquipamentosLocal el) {
		return ResponseEntity.ok(elDAO.excluir(el));
	}
}
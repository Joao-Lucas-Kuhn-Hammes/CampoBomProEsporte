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
import Models.Esporte;
import Persistencia.EquipamentosEsportesDAO;
@CrossOrigin
@RestController
@RequestMapping("/equipamentosesportes/")

public class EquipamentosEsportesController {
	
	EquipamentosEsportesDAO  eeDAO = new EquipamentosEsportesDAO();
	
	@GetMapping("esporte")
	public ArrayList<Esporte> getEsporte(@RequestBody Equipamento equipamento) {
		return eeDAO.buscarPorEquipamento(equipamento);
	}
	
	@GetMapping("equipamento")
	public ArrayList<Equipamento> getEquipamento(@RequestBody Esporte esporte) {
		return eeDAO.buscarPorEsporte(esporte);
	}
	
	@PostMapping
	public ResponseEntity<Boolean> setEquipamentoEsporte(@RequestBody Esporte esporte, Equipamento equip) {
		return ResponseEntity.ok(eeDAO.salvar(equip, esporte));
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> excluir(@RequestBody Esporte esporte, Equipamento equip) {
		return ResponseEntity.ok(eeDAO.excluir(equip, esporte));
	}
}
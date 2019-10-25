package Controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import Persistencia.PinDAO;
import Models.Pin;

@CrossOrigin
@RestController
@RequestMapping("/pin/")

public class PinController {
	
	PinDAO pinDAO = new PinDAO();
	
	@GetMapping("{id}")
	public Pin getPin(@PathVariable(name = "id") Long id) {
		return pinDAO.buscarPorId(id);
	}
	
	@GetMapping()
	public ArrayList<Pin> getPinTodos(){
		return pinDAO.buscarTodos();
	}
}

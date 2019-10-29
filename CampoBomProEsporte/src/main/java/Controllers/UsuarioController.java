package Controllers;


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

import Models.Usuario;
import Persistencia.UsuarioDAO;



@CrossOrigin
	@RestController
	@RequestMapping("/usuario/")
	public class UsuarioController {
		
	UsuarioDAO usuDAO = new UsuarioDAO();
		
	@GetMapping("{id}")
	public Usuario getUsuario(@PathVariable(name = "id") Long id) {
		return usuDAO.buscarPorId(id);
	}
	
	@GetMapping("email/{email}")
	public boolean getEmail(@PathVariable(name = "email") String email) {
		return usuDAO.buscarPorEmail(email);
	}
		
	@GetMapping("{email}/{senha}")
	public Usuario login(@PathVariable(name = "email") String email, @PathVariable(name = "senha") String senha) {
		return usuDAO.buscarLogin(email, senha);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> setUsuario(@RequestBody Usuario novo) {
		if(!usuDAO.buscarPorEmail(novo.getEmail())) {
			return ResponseEntity.ok(usuDAO.salvar(novo));
		}else {
			return ResponseEntity.ok(new Usuario());
		}
		
		
	}
	
	@PutMapping()
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario novo) {
		return ResponseEntity.ok(usuDAO.editar(novo));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deletar(@PathVariable Long id) {
		return ResponseEntity.ok(usuDAO.excluir(id));
	}
}
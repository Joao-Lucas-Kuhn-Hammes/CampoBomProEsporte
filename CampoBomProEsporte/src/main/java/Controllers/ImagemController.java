package Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/imagem/")

public class ImagemController {
	@GetMapping()
	public String getImagemURL(@RequestBody String implementar) {
		//implementar
		return null;
	}
}

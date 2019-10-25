package Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


public class ImagemController {
	@GetMapping()
	public String getImagemURL(@RequestBody String implementar) {
		//implementar
		return null;
	}
}

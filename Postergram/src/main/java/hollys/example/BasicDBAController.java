package hollys.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicDBAController {
	@RequestMapping(value = "/api/readAll")
	public String index() {
		return "index";
	}
}

package br.com.Especialista.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/especialista")
public class ControllerGen {
	
	@GetMapping("/test")
	@Operation(summary = "Consulta de teste")
	public String test() {
		return "Teste";
	}

}

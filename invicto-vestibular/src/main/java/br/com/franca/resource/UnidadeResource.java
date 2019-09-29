package br.com.franca.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.franca.domain.Unidade;
import br.com.franca.service.UnidadeService;

@RestController
@RequestMapping({ "/unidades" })
public class UnidadeResource {
	@Autowired
	private UnidadeService service;
	
	@GetMapping
	public List<Unidade> buscar() {
		return this.service.buscar();
	}

}

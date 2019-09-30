package br.com.franca.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.franca.domain.Unidade;
import br.com.franca.service.UnidadeService;
import br.com.franca.service.exception.ObjectNotFoundException;

@RestController
@RequestMapping({ "/unidades" })
public class UnidadeResource {

	@Autowired
	private UnidadeService service;

	@GetMapping
	public List<Unidade> buscar() {
		return this.service.buscar();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Unidade> buscar(@PathVariable Long id) throws ObjectNotFoundException {
		Unidade Unidade = service.buscar(id);
		return ResponseEntity.ok().body(Unidade);
	}

	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Unidade Unidade) {
		Unidade resultado = service.salvar(Unidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(resultado.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> alterar(@RequestBody Unidade Unidade, @PathVariable Integer id)
			throws ObjectNotFoundException {
		service.alterar(Unidade);
		return ResponseEntity.noContent().build();
	}

}

package br.com.franca.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import br.com.franca.domain.Unidade;
import br.com.franca.repository.UnidadeRepository;
import br.com.franca.service.exception.ObjectNotFoundException;

@Service
public class UnidadeService {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	UnidadeRepository repository;

	public List<Unidade> buscar() {
		return repository.findAll();
	}

	public Unidade buscar(Long id) throws ObjectNotFoundException {
		Optional<Unidade> rs = repository.findById(id);
		return rs.orElseThrow(() -> new ObjectNotFoundException(
				messageSource.getMessage("object.not.found", new Object[] { "Unidade" }, new Locale("br", "BR"))));
	}

	public Unidade salvar(Unidade Unidade) {
		return repository.save(Unidade);
	}

	public Unidade alterar(Unidade Unidade) throws ObjectNotFoundException {
		buscar(Unidade.getId());
		return repository.save(Unidade);
	}

}

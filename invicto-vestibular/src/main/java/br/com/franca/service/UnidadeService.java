package br.com.franca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.franca.domain.Unidade;
import br.com.franca.repository.UnidadeRepository;

@Service
public class UnidadeService {

	@Autowired
	UnidadeRepository repository;
	
	public List<Unidade> buscar() {
		return repository.findAll();
	}
}

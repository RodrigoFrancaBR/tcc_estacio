package br.com.franca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.franca.domain.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

}

package br.com.franca;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.franca.domain.Unidade;
import br.com.franca.repository.UnidadeRepository;

@SpringBootApplication
public class InvictoVestibularApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvictoVestibularApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UnidadeRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 11).mapToObj(i -> {
				Unidade u = new Unidade();
				u.setNome("usuario" + i);
				u.setEndereco("endereco" + i);
				u.setSituacao("ativa");				
				return u;
			}).map(v -> repository.save(v)).forEach(System.out::println);
		};
	}

}

package br.com.franca.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // cria automaticamente um construtor com todas os atributos da classe como argumento.
@NoArgsConstructor // cria automaticamente um construtor vazio (sem argumentos).
@Data //cria automaticamente os métodos toString, equals, hashCode, getters e setters.
@Entity
public class Unidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome {fild.not.blank}")
	private String nome;
	
	@NotBlank(message = "Endereço {fild.not.blank}")
	private String endereco;
	// @Default
	private Situacao situacao;		

}

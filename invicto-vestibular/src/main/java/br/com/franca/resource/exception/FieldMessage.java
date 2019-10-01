package br.com.franca.resource.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // cria automaticamente um construtor com todas os atributos da classe como argumento.
@NoArgsConstructor // cria automaticamente um construtor vazio (sem argumentos).
@Data //cria automaticamente os métodos toString, equals, hashCode, getters e setters.

public class FieldMessage {
	private String fieldName;
	private String message;
}

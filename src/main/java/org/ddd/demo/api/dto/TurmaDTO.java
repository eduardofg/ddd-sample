package org.ddd.demo.api.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
public class TurmaDTO implements Serializable {

	private static final long serialVersionUID = 2127450209312210568L;

	private String id;
	
	private String nome;
	
	private List<String> disciplinaId;
	
	private List<String> alunoId;
}

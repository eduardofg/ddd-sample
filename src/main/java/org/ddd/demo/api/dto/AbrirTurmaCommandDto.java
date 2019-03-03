package org.ddd.demo.api.dto;

import java.io.Serializable;
import java.util.List;

public class AbrirTurmaCommandDto implements Serializable {

    private static final long serialVersionUID = 7306498409129620432L;

    public String nome;

    public List<String> alunos;

    public List<String> disciplinas;

}

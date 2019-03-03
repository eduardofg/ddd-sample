package org.ddd.demo.domain;

import static org.ddd.demo.common.validation.ValidationUtils.assertIntegrity;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Aluno {

    @NotNull(message = "sample.aluno.id.null")
    private final AlunoId id;

    @NotNull(message = "sample.aluno.nome.null")
    private Nome nome;

    public static Aluno of(final AlunoId id, final Nome nome) {
        return new Aluno(id, nome);
    }

    public Aluno(AlunoId id, Nome nome) {

        this.id = id;
        this.nome = nome;

        assertIntegrity(this);
    }
}

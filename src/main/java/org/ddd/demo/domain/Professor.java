package org.ddd.demo.domain;

import static org.ddd.demo.common.validation.ValidationUtils.assertIntegrity;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Professor {

    @NotNull(message = "sample.professor.id.null")
    private final ProfessorId id;

    @NotNull(message = "sample.professor.nome.null")
    private Nome nome;

    public static Professor of(final ProfessorId id, final Nome nome) {
        return new Professor(id, nome);
    }

    public Professor(ProfessorId id, Nome nome) {

        this.id = id;
        this.nome = nome;

        assertIntegrity(this);
    }
}

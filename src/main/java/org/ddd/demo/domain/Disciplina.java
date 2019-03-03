package org.ddd.demo.domain;

import static org.ddd.demo.common.validation.ValidationUtils.assertIntegrity;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Disciplina {

    @NotNull(message = "sample.disciplina.id.null")
    private final DisciplinaId id;

    @NotNull(message = "sample.disciplina.nome.null")
    private Nome nome;

    @NotNull(message = "sample.disciplina.professor.null")
    private Professor professor;

    public static Disciplina of(final DisciplinaId id, final Nome nome, final Professor professor) {
        return new Disciplina(id, nome, professor);
    }

    public Disciplina(DisciplinaId id, Nome nome, Professor professor) {

        this.id = id;
        this.nome = nome;
        this.professor = professor;

        assertIntegrity(this);
    }
}

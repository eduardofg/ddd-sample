package org.ddd.demo.domain;

import static org.ddd.demo.common.validation.ValidationUtils.assertIntegrity;

import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Turma {

    @NotNull(message = "sample.turma.id.null")
    private final TurmaId id;

    @NotNull(message = "sample.turma.nome.null")
    private Nome nome;

    private List<Disciplina> disciplinas;

    private List<Aluno> alunos;

    public static Turma of(final TurmaId id, final Nome nome, final List<Disciplina> disciplinas,
            final List<Aluno> alunos) {
        return new Turma(id, nome, disciplinas, alunos);
    }

    public Turma(TurmaId id, Nome nome, List<Disciplina> disciplinas, List<Aluno> alunos) {

        this.id = id;
        this.nome = nome;
        this.disciplinas = disciplinas;
        this.alunos = alunos;

        assertIntegrity(this);
    }

    public List<Aluno> getAlunos() {
        return alunos == null ? Collections.emptyList() : Collections.unmodifiableList(alunos);
    }
}

package org.ddd.demo.app;

import java.util.List;

import org.ddd.demo.domain.AlunoId;
import org.ddd.demo.domain.DisciplinaId;
import org.ddd.demo.domain.Nome;
import org.ddd.demo.domain.TurmaId;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class AbrirTurmaCommand {

    public static AbrirTurmaCommand of(final TurmaId turmaId, final Nome nome, final List<DisciplinaId> disciplinas,
            final List<AlunoId> alunos) {
        return new AbrirTurmaCommand(turmaId, nome, disciplinas, alunos);
    }

    private TurmaId turmaId;

    private Nome nome;

    private List<DisciplinaId> disciplinas;

    private List<AlunoId> alunos;
}

package org.ddd.demo.app;

import org.ddd.demo.domain.DisciplinaId;
import org.ddd.demo.domain.Nome;
import org.ddd.demo.domain.ProfessorId;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class IncluirDisciplinaCommand {

    public static IncluirDisciplinaCommand of(final DisciplinaId disciplinaId, final Nome nome,
            final ProfessorId professorId) {
        return new IncluirDisciplinaCommand(disciplinaId, nome, professorId);
    }

    private DisciplinaId disciplinaId;

    private Nome nome;

    private ProfessorId professorId;
}

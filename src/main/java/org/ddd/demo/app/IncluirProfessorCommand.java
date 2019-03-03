package org.ddd.demo.app;

import org.ddd.demo.domain.Nome;
import org.ddd.demo.domain.ProfessorId;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class IncluirProfessorCommand {

    public static IncluirProfessorCommand of(final ProfessorId professorId, final Nome nome) {
        return new IncluirProfessorCommand(professorId, nome);
    }

    private ProfessorId professorId;

    private Nome nome;

}

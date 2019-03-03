package org.ddd.demo.app;

import org.ddd.demo.domain.AlunoId;
import org.ddd.demo.domain.Nome;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class IncluirAlunoCommand {

    public static IncluirAlunoCommand of(final AlunoId alunoId, final Nome nome) {
        return new IncluirAlunoCommand(alunoId, nome);
    }

    private AlunoId alunoId;

    private Nome nome;

}

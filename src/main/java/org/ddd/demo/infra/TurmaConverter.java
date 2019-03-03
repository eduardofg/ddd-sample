package org.ddd.demo.infra;

import java.util.stream.Collectors;

import org.ddd.demo.domain.Aluno;
import org.ddd.demo.domain.Disciplina;
import org.ddd.demo.domain.Nome;
import org.ddd.demo.domain.Turma;
import org.ddd.demo.domain.TurmaId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TurmaConverter {

    @Lazy
    @Autowired
    private ConversionService conversionService;

    @Component
    public class TurmaModelToEntity implements Converter<TurmaModel, Turma> {

        @Override
        public Turma convert(final TurmaModel source) {
            return Turma.builder().id(TurmaId.fromString(source.getId())).nome(new Nome(source.getNome()))
                    .disciplinas(source.getDisciplinas().stream()
                            .map(r -> conversionService.convert(r, Disciplina.class)).collect(Collectors.toList()))
                    .alunos(source.getAlunos().stream().map(r -> conversionService.convert(r, Aluno.class))
                            .collect(Collectors.toList()))
                    .build();
        }
    }

    @Component
    public class TurmaEntityToModel implements Converter<Turma, TurmaModel> {

        @Override
        public TurmaModel convert(final Turma source) {
            return TurmaModel.builder().id(source.getId().toString()).nome(source.getNome().toString())

                    .alunos(source.getAlunos().stream().map(r -> conversionService.convert(r, AlunoModel.class))
                            .collect(Collectors.toList()))
                    .disciplinas(source.getDisciplinas().stream()
                            .map(r -> conversionService.convert(r, DisciplinaModel.class)).collect(Collectors.toList()))
                    .build();
        }
    }

}

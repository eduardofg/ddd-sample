package org.ddd.demo.infra;

import org.ddd.demo.domain.Disciplina;
import org.ddd.demo.domain.DisciplinaId;
import org.ddd.demo.domain.Nome;
import org.ddd.demo.domain.Professor;
import org.ddd.demo.domain.ProfessorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DisciplinaConverter {

    @Lazy
    @Autowired
    private ConversionService conversionService;

    @Component
    public class DisciplinaModelToEntity implements Converter<DisciplinaModel, Disciplina> {

        @Override
        public Disciplina convert(final DisciplinaModel source) {
            return Disciplina.builder().id(DisciplinaId.fromString(source.getId())).nome(new Nome(source.getNome()))
                    .professor(Professor.of(ProfessorId.fromString(source.getProfessor().getId()),
                            new Nome(source.getProfessor().getNome())))
                    .build();
        }
    }

    @Component
    public class DisciplinaEntityToModel implements Converter<Disciplina, DisciplinaModel> {

        @Override
        public DisciplinaModel convert(final Disciplina source) {
            return DisciplinaModel.builder().id(source.getId().toString()).nome(source.getNome().toString())
                    .professor(new ProfessorModel(source.getProfessor().getId().toString(),
                            source.getProfessor().getNome().toString()))
                    .build();
        }
    }
}

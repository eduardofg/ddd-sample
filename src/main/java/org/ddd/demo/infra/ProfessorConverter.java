package org.ddd.demo.infra;

import org.ddd.demo.domain.Nome;
import org.ddd.demo.domain.Professor;
import org.ddd.demo.domain.ProfessorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfessorConverter {

    @Lazy
    @Autowired
    private ConversionService conversionService;

    @Component
    public class ProfessorModelToEntity implements Converter<ProfessorModel, Professor> {

        @Override
        public Professor convert(final ProfessorModel source) {
            return Professor.builder().id(ProfessorId.fromString(source.getId())).nome(new Nome(source.getNome()))
                    .build();
        }
    }

    @Component
    public class ProfessorEntityToModel implements Converter<Professor, ProfessorModel> {

        @Override
        public ProfessorModel convert(final Professor source) {
            return ProfessorModel.builder().id(source.getId().toString()).nome(source.getNome().toString()).build();
        }
    }

}

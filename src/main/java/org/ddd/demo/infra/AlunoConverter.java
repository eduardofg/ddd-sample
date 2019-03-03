package org.ddd.demo.infra;

import org.ddd.demo.domain.Aluno;
import org.ddd.demo.domain.AlunoId;
import org.ddd.demo.domain.Nome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AlunoConverter {

    @Lazy
    @Autowired
    private ConversionService conversionService;

    @Component
    public class AlunoModelToEntity implements Converter<AlunoModel, Aluno> {

        @Override
        public Aluno convert(final AlunoModel source) {
            return Aluno.builder().id(AlunoId.fromString(source.getId())).nome(new Nome(source.getNome())).build();
        }
    }

    @Component
    public class AlunoEntityToModel implements Converter<Aluno, AlunoModel> {

        @Override
        public AlunoModel convert(final Aluno source) {
            return AlunoModel.builder().id(source.getId().toString()).nome(source.getNome().toString()).build();
        }
    }

}

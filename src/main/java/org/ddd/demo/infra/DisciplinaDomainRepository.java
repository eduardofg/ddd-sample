package org.ddd.demo.infra;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.ddd.demo.domain.Disciplina;
import org.ddd.demo.domain.DisciplinaRepository;
import org.ddd.demo.domain.UniqueId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DisciplinaDomainRepository implements DisciplinaRepository {

    @Autowired
    private DisciplinaModelRepository repository;

    @Lazy
    @Autowired
    private ConversionService conversionService;

    @Transactional(readOnly = true)
    public Optional<Disciplina> get(final UniqueId id) {

        try {
            return id == null ? Optional.empty()
                    : Optional
                            .ofNullable(conversionService.convert(repository.getOne(id.toString()), Disciplina.class));
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    public void store(final Disciplina disciplina) {
        repository.save(conversionService.convert(disciplina, DisciplinaModel.class));
    }
    
    public List<Disciplina> findAll() {
        return repository.findAll().stream().map(disciplina -> conversionService.convert(disciplina, Disciplina.class)).collect(Collectors.toList());
    }
}

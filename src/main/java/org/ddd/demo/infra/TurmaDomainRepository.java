package org.ddd.demo.infra;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.ddd.demo.domain.Turma;
import org.ddd.demo.domain.TurmaRepository;
import org.ddd.demo.domain.UniqueId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TurmaDomainRepository implements TurmaRepository {

    @Autowired
    private TurmaModelRepository repository;

    @Lazy
    @Autowired
    private ConversionService conversionService;

    @Transactional(readOnly = true)
    public Optional<Turma> get(final UniqueId id) {
        try {
            return id == null ? Optional.empty()
                    : Optional.ofNullable(conversionService.convert(repository.getOne(id.toString()), Turma.class));
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    public void store(final Turma turma) {
        repository.save(conversionService.convert(turma, TurmaModel.class));
    }
    
    public List<Turma> findAll() {
        return repository.findAll().stream().map(turma -> conversionService.convert(turma, Turma.class)).collect(Collectors.toList());
    }
}

package org.ddd.demo.infra;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.ddd.demo.domain.Professor;
import org.ddd.demo.domain.ProfessorRepository;
import org.ddd.demo.domain.UniqueId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProfessorDomainRepository implements ProfessorRepository {

    @Autowired
    private ProfessorModelRepository repository;

    @Lazy
    @Autowired
    private ConversionService conversionService;

    @Transactional(readOnly = true)
    public Optional<Professor> get(final UniqueId id) {

        try {
            return id == null ? Optional.empty()
                    : Optional.ofNullable(conversionService.convert(repository.getOne(id.toString()), Professor.class));
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    public void store(final Professor professor) {
        repository.save(conversionService.convert(professor, ProfessorModel.class));
    }
    
    public List<Professor> findAll() {
        return repository.findAll().stream().map(professor -> conversionService.convert(professor, Professor.class)).collect(Collectors.toList());
    }
}

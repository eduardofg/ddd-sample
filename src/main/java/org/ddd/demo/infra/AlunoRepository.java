package org.ddd.demo.infra;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.ddd.demo.domain.Aluno;
import org.ddd.demo.domain.AlunoDomainRepository;
import org.ddd.demo.domain.UniqueId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AlunoRepository implements AlunoDomainRepository{

    @Autowired
    private AlunoModelRepository repository;

    @Lazy
    @Autowired
    private ConversionService conversionService;

    @Transactional(readOnly = true)
    public Optional<Aluno> get(final UniqueId id) {

        try {
            return id == null ? Optional.empty()
                    : Optional.ofNullable(conversionService.convert(repository.getOne(id.toString()), Aluno.class));
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    public void store(final Aluno aluno) {
        repository.save(conversionService.convert(aluno, AlunoModel.class));
    }
    
    public List<Aluno> findAll() {
        return repository.findAll().stream().map(aluno -> conversionService.convert(aluno, Aluno.class)).collect(Collectors.toList());
    }
}

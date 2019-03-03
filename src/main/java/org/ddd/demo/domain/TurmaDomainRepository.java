package org.ddd.demo.domain;

import java.util.List;
import java.util.Optional;

import org.ddd.demo.domain.Turma;
import org.ddd.demo.domain.UniqueId;

public interface TurmaDomainRepository {

    public Optional<Turma> get(final UniqueId id);

    public void store(final Turma turma);
    
    public List<Turma> findAll();
}

package org.ddd.demo.domain;

import java.util.List;
import java.util.Optional;

import org.ddd.demo.domain.Disciplina;
import org.ddd.demo.domain.UniqueId;

public interface DisciplinaRepository {

    public Optional<Disciplina> get(final UniqueId id);

    public void store(final Disciplina disciplina);
    
    public List<Disciplina> findAll();
}

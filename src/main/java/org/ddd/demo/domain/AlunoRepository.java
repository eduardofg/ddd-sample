package org.ddd.demo.domain;

import java.util.List;
import java.util.Optional;
import org.ddd.demo.domain.Aluno;
import org.ddd.demo.domain.UniqueId;

public interface AlunoRepository {

    public Optional<Aluno> get(final UniqueId id);

    public void store(final Aluno aluno);
    
    public List<Aluno> findAll();
}

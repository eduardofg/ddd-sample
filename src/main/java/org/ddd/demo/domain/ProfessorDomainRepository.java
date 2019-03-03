package org.ddd.demo.domain;

import java.util.List;
import java.util.Optional;

import org.ddd.demo.domain.Professor;

public interface ProfessorDomainRepository {

    public Optional<Professor> get(final UniqueId id);

    public void store(final Professor professor);
    
    public List<Professor> findAll();
}

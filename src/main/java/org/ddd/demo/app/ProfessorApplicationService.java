package org.ddd.demo.app;

import org.ddd.demo.domain.Professor;
import org.ddd.demo.infra.ProfessorDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfessorApplicationService {

    @Autowired
    private ProfessorDomainRepository professorRepository;

    public void handle(final IncluirProfessorCommand cmd) {

        Professor professor = Professor.of(cmd.getProfessorId(), cmd.getNome());

        professorRepository.store(professor);
    }
}

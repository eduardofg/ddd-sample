package org.ddd.demo.app;

import org.ddd.demo.domain.Disciplina;
import org.ddd.demo.domain.Professor;
import org.ddd.demo.infra.DisciplinaRepository;
import org.ddd.demo.infra.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DisciplinaApplicationService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public void handle(final IncluirDisciplinaCommand cmd) {

        Professor professor = professorRepository.get(cmd.getProfessorId()).orElse(null);

        Disciplina disciplina = Disciplina.of(cmd.getDisciplinaId(), cmd.getNome(), professor);

        disciplinaRepository.store(disciplina);
    }
}

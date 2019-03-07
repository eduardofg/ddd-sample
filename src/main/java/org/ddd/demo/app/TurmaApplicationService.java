package org.ddd.demo.app;

import java.util.List;
import java.util.stream.Collectors;

import org.ddd.demo.domain.Aluno;
import org.ddd.demo.domain.Disciplina;
import org.ddd.demo.domain.Turma;
import org.ddd.demo.infra.AlunoDomainRepository;
import org.ddd.demo.infra.DisciplinaDomainRepository;
import org.ddd.demo.infra.TurmaDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TurmaApplicationService {

    @Autowired
    private TurmaDomainRepository turmaRepository;

    @Autowired
    private AlunoDomainRepository alunoRepository;

    @Autowired
    private DisciplinaDomainRepository disciplinaRepository;

    public void handle(final AbrirTurmaCommand cmd) {

        List<Disciplina> disciplinas = cmd.getDisciplinas().stream()
                .map(disciplinaId -> disciplinaRepository.get(disciplinaId).orElse(null)).collect(Collectors.toList());
        List<Aluno> alunos = cmd.getAlunos().stream().map(alunoId -> alunoRepository.get(alunoId).orElse(null))
                .collect(Collectors.toList());

        Turma turma = Turma.of(cmd.getTurmaId(), cmd.getNome(), disciplinas, alunos);

        turmaRepository.store(turma);
    }
}

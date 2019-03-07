package org.ddd.demo.app;

import org.ddd.demo.domain.Aluno;
import org.ddd.demo.infra.AlunoDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AlunoApplicationService {

    @Autowired
    private AlunoDomainRepository alunoRepository;

    public void handle(final IncluirAlunoCommand cmd) {

        Aluno aluno = Aluno.of(cmd.getAlunoId(), cmd.getNome());

        alunoRepository.store(aluno);
    }
}

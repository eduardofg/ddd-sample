package org.ddd.demo.api;

import java.util.List;
import java.util.stream.Collectors;

import org.ddd.demo.api.dto.AbrirTurmaCommandDto;
import org.ddd.demo.api.dto.TurmaDTO;
import org.ddd.demo.app.AbrirTurmaCommand;
import org.ddd.demo.app.TurmaApplicationService;
import org.ddd.demo.domain.AlunoId;
import org.ddd.demo.domain.DisciplinaId;
import org.ddd.demo.domain.Nome;
import org.ddd.demo.domain.TurmaId;
import org.ddd.demo.infra.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/turmas", produces = { MediaType.APPLICATION_JSON_VALUE })
public class TurmaController {

    @Autowired
    private TurmaApplicationService turmaService;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping(path = "/")
    public List<TurmaDTO> findAll() {
        return turmaRepository.findAll().stream()
                .map(turma -> new TurmaDTO(turma.getId().toString(), turma.getNome().toString(),
                        turma.getDisciplinas().stream().map(disciplina -> disciplina.getId().toString())
                                .collect(Collectors.toList()),
                        turma.getAlunos().stream().map(aluno -> aluno.getId().toString()).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/abrir", consumes = { MediaType.APPLICATION_JSON_VALUE })
    void regristrarTurma(@RequestBody AbrirTurmaCommandDto dto) {

        turmaService.handle(AbrirTurmaCommand.of(TurmaId.generate(), new Nome(dto.nome),
                dto.disciplinas.stream().map(disciplina -> DisciplinaId.fromString(disciplina))
                        .collect(Collectors.toList()),
                dto.alunos.stream().map(aluno -> AlunoId.fromString(aluno)).collect(Collectors.toList())));
    }
}
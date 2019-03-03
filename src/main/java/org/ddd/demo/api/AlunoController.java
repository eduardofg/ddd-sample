package org.ddd.demo.api;

import java.util.List;
import java.util.stream.Collectors;

import org.ddd.demo.api.dto.AlunoDTO;
import org.ddd.demo.api.dto.IncluirAlunoCommandDto;
import org.ddd.demo.app.AlunoApplicationService;
import org.ddd.demo.app.IncluirAlunoCommand;
import org.ddd.demo.domain.AlunoId;
import org.ddd.demo.domain.Nome;
import org.ddd.demo.infra.AlunoRepository;
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
@RequestMapping(path = "/api/v1/alunos", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AlunoController {

    @Autowired
    private AlunoApplicationService alunoService;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping(path = "/")
    public List<AlunoDTO> findAll() {
        return alunoRepository.findAll()
                .stream()
                .map(aluno -> new AlunoDTO(aluno.getId().toString(), aluno.getNome().toString()))
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/incluir", consumes = { MediaType.APPLICATION_JSON_VALUE })
    void incluirAluno(@RequestBody IncluirAlunoCommandDto dto) {

        alunoService.handle(IncluirAlunoCommand.of(AlunoId.generate(), new Nome(dto.nome)));
    }
}
package org.ddd.demo.api;

import java.util.List;
import java.util.stream.Collectors;

import org.ddd.demo.api.dto.DisciplinaDTO;
import org.ddd.demo.api.dto.IncluirDisciplinaCommandDto;
import org.ddd.demo.app.DisciplinaApplicationService;
import org.ddd.demo.app.IncluirDisciplinaCommand;
import org.ddd.demo.domain.DisciplinaId;
import org.ddd.demo.domain.Nome;
import org.ddd.demo.domain.ProfessorId;
import org.ddd.demo.infra.DisciplinaRepository;
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
@RequestMapping(path = "/api/v1/disciplinas", produces = { MediaType.APPLICATION_JSON_VALUE })
public class DisciplinaController {

    @Autowired
    private DisciplinaApplicationService disciplinaService;

    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    @GetMapping(path = "/")
    public List<DisciplinaDTO> findAll() {
        return disciplinaRepository.findAll()
                .stream()
                .map(disciplina -> new DisciplinaDTO(disciplina.getId().toString(), disciplina.getNome().toString(), disciplina.getProfessor().getId().toString()))
                .collect(Collectors.toList());
    }
    
    @PostMapping(path = "/incluir", consumes = { MediaType.APPLICATION_JSON_VALUE })
    void incluirDisciplina(@RequestBody IncluirDisciplinaCommandDto dto) {

        disciplinaService.handle(IncluirDisciplinaCommand.of(DisciplinaId.generate(), new Nome(dto.nome),
                ProfessorId.fromString(dto.professorId)));
    }
}
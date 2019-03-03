package org.ddd.demo.api;

import java.util.List;
import java.util.stream.Collectors;

import org.ddd.demo.api.dto.IncluirProfessorCommandDto;
import org.ddd.demo.api.dto.ProfessorDTO;
import org.ddd.demo.app.IncluirProfessorCommand;
import org.ddd.demo.app.ProfessorApplicationService;
import org.ddd.demo.domain.Nome;
import org.ddd.demo.domain.ProfessorId;
import org.ddd.demo.infra.ProfessorRepository;
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
@RequestMapping(path = "/api/v1/professores", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProfessorController {

    @Autowired
    private ProfessorApplicationService professorService;

    @Autowired
    private ProfessorRepository professorRepository;
    
    @GetMapping(path = "/")
    public List<ProfessorDTO> findAll() {
        return professorRepository.findAll()
                .stream()
                .map(professor -> new ProfessorDTO(professor.getId().toString(), professor.getNome().toString()))
                .collect(Collectors.toList());
    }
    
    @PostMapping(path = "/incluir", consumes = { MediaType.APPLICATION_JSON_VALUE })
    void incluirProfessor(@RequestBody IncluirProfessorCommandDto dto) {

        professorService.handle(IncluirProfessorCommand.of(ProfessorId.generate(), new Nome(dto.nome)));
    }
}
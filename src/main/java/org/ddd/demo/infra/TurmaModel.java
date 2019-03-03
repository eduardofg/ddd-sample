package org.ddd.demo.infra;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "turma")
public class TurmaModel {

    @Id
    @NotNull
    private String id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "disciplinas_turma")
    private List<DisciplinaModel> disciplinas;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "alunos_turma")
    private List<AlunoModel> alunos;

    public List<DisciplinaModel> getDisciplinas() {
        return disciplinas == null ? Collections.emptyList() : Collections.unmodifiableList(disciplinas);
    }

    public List<AlunoModel> getAlunos() {
        return alunos == null ? Collections.emptyList() : Collections.unmodifiableList(alunos);
    }
}

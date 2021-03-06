package org.ddd.demo.infra;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Embeddable
@Entity
@Table(name = "disciplina")
public class DisciplinaModel {

    @Id
    @NotNull
    private String id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorModel professor;
}

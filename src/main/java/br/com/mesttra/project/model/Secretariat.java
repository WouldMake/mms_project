package br.com.mesttra.project.model;

import br.com.mesttra.project.enums.Folder;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Secretariat {

    private Long id;

    @Enumerated(EnumType.STRING)
    private Folder folder;

    private String secretary;

    private Integer populationGrade;

    private Boolean underInvestigation;
}
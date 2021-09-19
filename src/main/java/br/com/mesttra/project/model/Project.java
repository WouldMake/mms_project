package br.com.mesttra.project.model;

import br.com.mesttra.project.enums.Folder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Double cost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private Folder folder;

    @Column(nullable = false)
    @NotNull
    private Long secretariatId;

    private Long budgetId;
}

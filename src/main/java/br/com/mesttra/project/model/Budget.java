package br.com.mesttra.project.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Budget {
    private Long id;

    private Double totalAmount;

    private Double spentAmount;

    private String possibleDestinations;

    private String origin;
}
package br.com.mesttra.project.request;

import br.com.mesttra.project.enums.Folder;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebitExpenseRequest {
    private Double amount;
    @Enumerated(EnumType.STRING)
    private Folder folder;
}

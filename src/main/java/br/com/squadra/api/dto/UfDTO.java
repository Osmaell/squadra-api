package br.com.squadra.api.dto;

import br.com.squadra.api.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UfDTO {

    private Long codigoUF;

    private String sigla;

    private String nome;

    private int status;

}
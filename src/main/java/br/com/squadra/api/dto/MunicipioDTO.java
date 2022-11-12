package br.com.squadra.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioDTO {

    private Long codigoMunicipio;

    private Long codigoUF;

    private String nome;

    private Integer status;

}

package br.com.squadra.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BairroDTO {

    private Long codigoBairro;

    private Long codigoMunicipio;

    private String nome;

    private Integer status;

}
package br.com.squadra.api.service;

import br.com.squadra.api.dto.MunicipioDTO;
import br.com.squadra.api.model.Municipio;
import br.com.squadra.api.repository.MunicipioRepository;
import br.com.squadra.api.repository.specs.MunicipioSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    public ResponseEntity<?> buscar(MunicipioDTO dto) {

        Specification<Municipio> specs = Specification.where( ((root, query, cb) -> cb.conjunction()));

        if ( dto.getCodigoMunicipio() != null ) {
            specs = specs.and(MunicipioSpecs.codigoEqual(dto.getCodigoMunicipio()));
        }

        if ( dto.getCodigoUF() != null ) {
            specs = specs.and(MunicipioSpecs.ufEqual(dto.getCodigoUF()));
        }

        if ( dto.getStatus() != null ) {
            specs = specs.and(MunicipioSpecs.statusEqual(dto.getStatus()));
        }

        if (StringUtils.hasText(dto.getNome())) {
            specs = specs.and(MunicipioSpecs.nomeEqual(dto.getNome()));
        }

        List<Municipio> municipios = municipioRepository.findAll(specs);
        List<MunicipioDTO> municipioDTOs = municipios.stream().map(this::converter).collect(Collectors.toList());

        if ( dto.getCodigoMunicipio() != null) {

            return municipioDTOs.stream().findFirst().isPresent() ?
                    ResponseEntity.ok(municipioDTOs.stream().findFirst()) :
                    ResponseEntity.ok(municipioDTOs.toArray());
        }

        return ResponseEntity.ok(municipioDTOs);
    }

    private MunicipioDTO converter(Municipio municipio) {
        return MunicipioDTO.builder()
                .codigoMunicipio(municipio.getCodigo())
                .codigoUF(municipio.getUf().getCodigo())
                .status(municipio.getStatus())
                .nome(municipio.getNome())
                .build();
    }

}

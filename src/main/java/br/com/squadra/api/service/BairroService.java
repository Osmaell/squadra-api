package br.com.squadra.api.service;

import br.com.squadra.api.dto.BairroDTO;
import br.com.squadra.api.exception.RegraNegocioException;
import br.com.squadra.api.model.Bairro;
import br.com.squadra.api.model.Municipio;
import br.com.squadra.api.repository.BairroRepository;
import br.com.squadra.api.repository.specs.BairroSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BairroService {

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private MunicipioService municipioService;

    public ResponseEntity<?> buscar(BairroDTO bairroDTO) {

        Specification<Bairro> specs = Specification.where( (root, query, cb) -> cb.conjunction());

        if (bairroDTO.getCodigoBairro() != null) {
            specs = specs.and(BairroSpecs.codigoBairroEqual(bairroDTO.getCodigoBairro()));
        }

        if (bairroDTO.getCodigoMunicipio() != null) {
            specs = specs.and(BairroSpecs.codigoMunicipioEqual(bairroDTO.getCodigoMunicipio()));
        }

        if (bairroDTO.getStatus() != null) {
            specs = specs.and(BairroSpecs.statusEqual(bairroDTO.getStatus()));
        }

        if (StringUtils.hasText(bairroDTO.getNome())) {
            specs = specs.and(BairroSpecs.nomeEqual(bairroDTO.getNome()));
        }

        List<Bairro> bairros = bairroRepository.findAll(specs);
        List<BairroDTO> dtos = bairros.stream().map(this::converter).collect(Collectors.toList());

        if ( bairroDTO.getCodigoBairro() != null ) {
            return dtos.stream().findFirst().isPresent() ?
                    ResponseEntity.ok(dtos.stream().findFirst()) :
                    ResponseEntity.ok(dtos.toArray());
        }

        return ResponseEntity.ok(dtos);
    }

    public BairroDTO buscarPorCodigo(Long codigo) {

        Bairro bairro = bairroRepository.findById(codigo)
                .orElseThrow(() -> new RegraNegocioException("msg.bairro-inexistente"));

        return converter(bairro);
    }

    public List<BairroDTO> salvar(BairroDTO bairroDTO) {

        Optional<Bairro> optionalBairro = bairroRepository
                .buscarPorNomeEMunicipio(bairroDTO.getNome(), bairroDTO.getCodigoMunicipio());

        if (optionalBairro.isPresent()) {
            throw new RegraNegocioException("msg.bairro-existente");
        }

        bairroRepository.saveAndFlush( converter(bairroDTO) );

        return bairroRepository.findAll()
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());
    }

    private BairroDTO converter(Bairro bairro) {
        return BairroDTO.builder()
                .codigoBairro(bairro.getCodigo())
                .codigoMunicipio(bairro.getMunicipio().getCodigo())
                .nome(bairro.getNome())
                .status(bairro.getStatus())
                .build();
    }

    private Bairro converter(BairroDTO dto) {

        Municipio municipio = municipioService
                .buscarPorCodigoSemResponseEntity(dto.getCodigoMunicipio());

        return Bairro.builder()
                .municipio(municipio)
                .status(dto.getStatus())
                .nome(dto.getNome())
                .build();
    }

}
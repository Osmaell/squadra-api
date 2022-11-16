package br.com.squadra.api.service;

import br.com.squadra.api.dto.MunicipioDTO;
import br.com.squadra.api.dto.MunicipioUpdateDTO;
import br.com.squadra.api.exception.RegraNegocioException;
import br.com.squadra.api.model.Municipio;
import br.com.squadra.api.model.Uf;
import br.com.squadra.api.repository.MunicipioRepository;
import br.com.squadra.api.repository.specs.MunicipioSpecs;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private UfService ufService;

    public List<MunicipioDTO> salvar(MunicipioDTO dto) {

        Optional<Municipio> optionalMunicipio = municipioRepository
                .buscarPorNomeEUf(dto.getNome(), dto.getCodigoUF());

        if (optionalMunicipio.isPresent()) {
            throw new RegraNegocioException("msg.municipio-existente");
        }

        municipioRepository.saveAndFlush(converter(dto));

        return municipioRepository
                .findAll()
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());
    }

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

    public ResponseEntity<?> buscarPorCodigo(Long codigo) {

        Optional<Municipio> optionalMunicipio = municipioRepository.findById(codigo);

        if (optionalMunicipio.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        MunicipioDTO dto = converter(optionalMunicipio.get());
        return ResponseEntity.ok(dto);
    }

    public List<MunicipioDTO> atualizar(MunicipioUpdateDTO municipioDTO) {

        Municipio municipioSalvo = municipioRepository.findById(municipioDTO.getCodigoMunicipio())
                .orElseThrow(() -> new RegraNegocioException("msg.municipio-inexistente"));

        BeanUtils.copyProperties(municipioDTO, municipioSalvo, "codigoMunicipio");
        municipioRepository.saveAndFlush(municipioSalvo);

        return buscarTodos();
    }

    public List<MunicipioDTO> deletar(Long codigoMunicipio) {

        try {
            municipioRepository.deleteById(codigoMunicipio);
            return buscarTodos();
        } catch (Exception ex) {
            throw new RegraNegocioException("msg.municipio_erro_ao_deletar");
        }

    }

    private List<MunicipioDTO> buscarTodos() {
        return municipioRepository.findAll()
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());
    }

    private MunicipioDTO converter(Municipio municipio) {
        return MunicipioDTO.builder()
                .codigoMunicipio(municipio.getCodigo())
                .codigoUF(municipio.getUf().getCodigo())
                .status(municipio.getStatus())
                .nome(municipio.getNome())
                .build();
    }

    private Municipio converter(MunicipioDTO dto) {

        Uf uf = ufService.buscarPorCodigo(dto.getCodigoUF());

        return Municipio.builder()
                .nome(dto.getNome())
                .status(dto.getStatus())
                .uf(uf)
                .build();
    }

}
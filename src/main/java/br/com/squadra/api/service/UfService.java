package br.com.squadra.api.service;

import br.com.squadra.api.dto.UfDTO;
import br.com.squadra.api.exception.RegraNegocioException;
import br.com.squadra.api.model.Uf;
import br.com.squadra.api.repository.UfRepository;
import br.com.squadra.api.repository.specs.UfSpecs;
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
public class UfService {

    @Autowired
    private UfRepository ufRepository;

    public List<UfDTO> salvar(Uf uf) {

        if ( !ufRepository.buscarPorSigla(uf.getSigla()).isEmpty() ) {
            throw new RegraNegocioException("msg.uf_sigla_existente");
        } else if ( !ufRepository.buscarPorNome(uf.getNome()).isEmpty() ) {
            throw new RegraNegocioException("msg.uf_nome_existente");
        }

        ufRepository.save(uf);

        return ufRepository
                .findAll()
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());
    }

    public List<UfDTO> atualizar(UfDTO ufDTO) {

        Optional<Uf> ufSalva = ufRepository.findById(ufDTO.getCodigoUF());

        if (ufSalva.isEmpty()) {
            throw new RegraNegocioException("msg.uf_nao_existente");
        }

        BeanUtils.copyProperties(ufDTO, ufSalva.get(), "codigoUF");
        ufRepository.save(ufSalva.get());

        return buscarTodas();
    }

    public ResponseEntity<?> buscar(UfDTO dto) {

        Specification<Uf> specs = Specification.where( ((root, query, cb) -> cb.conjunction()) );

        if (dto.getCodigoUF() != null) {
            specs = specs.and(UfSpecs.codigoEqual(dto.getCodigoUF()));
        }

        if (StringUtils.hasText(dto.getNome())) {
            specs = specs.and(UfSpecs.nomeEqual(dto.getNome()));
        }

        if (StringUtils.hasText(dto.getSigla())) {
            specs = specs.and(UfSpecs.siglaEqual(dto.getSigla()));
        }

        if (dto.getStatus() != null) {
            specs = specs.and(UfSpecs.statusEqual(dto.getStatus()));
        }

        List<Uf> ufs = ufRepository.findAll(specs);
        List<UfDTO> ufDTOS = ufs.stream().map(this::converter).collect(Collectors.toList());

        if (ufs.size() == 1) {
            return ResponseEntity.ok(ufDTOS.get(0));
        } else {
            return ResponseEntity.ok(ufDTOS);
        }

    }

    public Uf buscarPorCodigo(Long codigo) {
        return ufRepository.findById(codigo)
                .orElseThrow(() -> new RegraNegocioException("msg.municipio-uf-ausente"));
    }

    public ResponseEntity<?> obterUf(Long codigo) {

        Optional<Uf> optional = ufRepository.findById(codigo);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.map(this::converter));
        }

        return ResponseEntity.ok(new ArrayList<>());
    }

    public List<UfDTO> deletar(Long codigo) {

        try {

            ufRepository.deleteById(codigo);
            return buscarTodas();

        } catch (Exception ex) {
            throw new RegraNegocioException("msg.uf_erro_ao_deletar");
        }

    }

    private List<UfDTO> buscarTodas() {
        return ufRepository.findAll()
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());
    }

    private UfDTO converter(Uf uf) {
        return UfDTO.builder()
                .codigoUF(uf.getCodigo())
                .sigla(uf.getSigla())
                .nome(uf.getNome())
                .status(uf.getStatus())
                .build();
    }

}
package br.com.squadra.api.service;

import br.com.squadra.api.dto.UfDTO;
import br.com.squadra.api.exception.RegraNegocioException;
import br.com.squadra.api.model.Uf;
import br.com.squadra.api.repository.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UfService {

    @Autowired
    private UfRepository ufRepository;

    public ResponseEntity<?> obterUf(Long codigo) {

        Optional<Uf> optional = ufRepository.findById(codigo);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.map(this::converter));
        }

        return ResponseEntity.ok(new ArrayList<>());
    }

    private UfDTO converter(Uf uf) {
        return UfDTO.builder()
                .codigoUF(uf.getCodigo())
                .sigla(uf.getSigla())
                .nome(uf.getNome())
                .status(uf.getStatus().getValor())
                .build();
    }

}

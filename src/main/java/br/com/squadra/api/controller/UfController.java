package br.com.squadra.api.controller;

import br.com.squadra.api.dto.UfDTO;
import br.com.squadra.api.exception.RegraNegocioException;
import br.com.squadra.api.model.Uf;
import br.com.squadra.api.service.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/uf")
public class UfController {

    @Autowired
    private UfService ufService;

    @PostMapping
    public ResponseEntity<?> salvar( @RequestBody @Valid Uf uf) {
        List<UfDTO> ufs = ufService.salvar(uf);
        return ResponseEntity.ok(ufs);
    }

    @GetMapping
    public ResponseEntity<?> buscar(UfDTO ufDTO) {
        return ufService.buscar(ufDTO);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo( @PathVariable Long codigo ) {
        return ufService.obterUf(codigo);
    }

}
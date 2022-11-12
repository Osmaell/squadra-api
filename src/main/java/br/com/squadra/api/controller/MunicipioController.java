package br.com.squadra.api.controller;

import br.com.squadra.api.dto.MunicipioDTO;
import br.com.squadra.api.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/municipio")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    @GetMapping
    public ResponseEntity<?> buscar( MunicipioDTO dto) {
        return municipioService.buscar(dto);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo( @PathVariable Long codigo ) {
        return municipioService.buscarPorCodigo(codigo);
    }

}
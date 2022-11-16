package br.com.squadra.api.controller;

import br.com.squadra.api.dto.MunicipioDTO;
import br.com.squadra.api.dto.MunicipioUpdateDTO;
import br.com.squadra.api.model.Municipio;
import br.com.squadra.api.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/municipio")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    @PostMapping
    public ResponseEntity<?> salvar( @RequestBody @Valid MunicipioDTO dto ) {
        List<MunicipioDTO> municipios = municipioService.salvar(dto);
        return ResponseEntity.ok(municipios);
    }

    @GetMapping
    public ResponseEntity<?> buscar( MunicipioDTO dto) {
        return municipioService.buscar(dto);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo( @PathVariable Long codigo ) {
        return municipioService.buscarPorCodigo(codigo);
    }

    @PutMapping
    public ResponseEntity<?> atualizar( @RequestBody @Valid MunicipioUpdateDTO municipioDTO ) {
        List<MunicipioDTO> municipios = municipioService.atualizar(municipioDTO);
        return ResponseEntity.ok(municipios);
    }

    @DeleteMapping("/{codigoMunicipio}")
    public ResponseEntity<?> deletar( @PathVariable Long codigoMunicipio) {
        List<MunicipioDTO> municipios = municipioService.deletar(codigoMunicipio);
        return ResponseEntity.ok(municipios);
    }

}
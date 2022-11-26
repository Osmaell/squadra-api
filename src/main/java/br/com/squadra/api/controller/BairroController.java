package br.com.squadra.api.controller;

import br.com.squadra.api.dto.BairroDTO;
import br.com.squadra.api.dto.BairroUpdateDTO;
import br.com.squadra.api.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bairro")
public class BairroController {

    @Autowired
    private BairroService bairroService;

    @GetMapping
    public ResponseEntity<?> buscar(BairroDTO bairroDTO) {
        return bairroService.buscar(bairroDTO);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable Long codigo) {
        BairroDTO bairroDTO = bairroService.buscarPorCodigo(codigo);
        return ResponseEntity.ok(bairroDTO);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid BairroDTO bairroDTO) {
        List<BairroDTO> bairros = bairroService.salvar(bairroDTO);
        return ResponseEntity.ok(bairros);
    }

    @PutMapping
    public ResponseEntity<?> atualizar( @RequestBody @Valid BairroUpdateDTO bairroDTO ) {
        List<BairroDTO> bairros = bairroService.atualizar(bairroDTO);
        return ResponseEntity.ok(bairros);
    }

}
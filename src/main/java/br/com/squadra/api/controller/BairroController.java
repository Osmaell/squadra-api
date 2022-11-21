package br.com.squadra.api.controller;

import br.com.squadra.api.dto.BairroDTO;
import br.com.squadra.api.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bairro")
public class BairroController {

    @Autowired
    private BairroService bairroService;

    @GetMapping
    public ResponseEntity<?> buscar(BairroDTO bairroDTO) {
        return bairroService.buscar(bairroDTO);
    }

}
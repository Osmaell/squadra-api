package br.com.squadra.api.controller;

import br.com.squadra.api.exception.RegraNegocioException;
import br.com.squadra.api.service.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/uf")
public class UfController {

    @Autowired
    private UfService ufService;

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo( @PathVariable Long codigo ) {
        return ufService.obterUf(codigo);
    }

}
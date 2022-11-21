package br.com.squadra.api.repository.specs;

import br.com.squadra.api.model.Bairro;
import br.com.squadra.api.model.Uf;
import org.springframework.data.jpa.domain.Specification;

public abstract class BairroSpecs {

    public static Specification<Bairro> codigoBairroEqual(Long codigoBairro) {
        return (root, query, cb) -> cb.equal( root.get("codigo"), codigoBairro );
    }

    public static Specification<Bairro> codigoMunicipioEqual(Long codigoMunicipio) {
        return (root, query, cb) -> cb.equal( root.get("municipio").get("codigo"), codigoMunicipio );
    }

    public static Specification<Bairro> nomeEqual(String nome) {
        return (root, query, cb) -> cb.equal( cb.upper(root.get("nome")), nome.toUpperCase() );
    }

    public static Specification<Bairro> statusEqual(Integer status) {
        return (root, query, cb) -> cb.equal( root.get("status"), status );
    }

}
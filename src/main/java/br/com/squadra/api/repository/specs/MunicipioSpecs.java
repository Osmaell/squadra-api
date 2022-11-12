package br.com.squadra.api.repository.specs;

import br.com.squadra.api.model.Municipio;
import org.springframework.data.jpa.domain.Specification;

public abstract class MunicipioSpecs {

    public static Specification<Municipio> codigoEqual(Long codigo) {
        return (root, query, cb) -> cb.equal( root.get("codigo"), codigo );
    }

    public static Specification<Municipio> nomeEqual(String nome) {
        return (root, query, cb) -> cb.equal( cb.upper(root.get("nome")), nome.toUpperCase() );
    }

    public static Specification<Municipio> statusEqual(Integer status) {
        return (root, query, cb) -> cb.equal( root.get("status"), status );
    }

    public static Specification<Municipio> ufEqual(Long codigoUF) {
        return ((root, query, cb) -> cb.equal( root.get("uf").get("codigo"), codigoUF ));
    }

}
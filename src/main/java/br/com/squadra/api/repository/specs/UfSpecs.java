package br.com.squadra.api.repository.specs;

import br.com.squadra.api.model.Uf;
import org.springframework.data.jpa.domain.Specification;

public abstract class UfSpecs {

    public static Specification<Uf> codigoEqual(Long codigo) {
        return (root, query, cb) -> cb.equal( root.get("codigo"), codigo );
    }

    public static Specification<Uf> nomeEqual(String nome) {
        return (root, query, cb) -> cb.equal( cb.upper(root.get("nome")), nome.toUpperCase() );
    }

    public static Specification<Uf> siglaEqual(String sigla) {
        return (root, query, cb) -> cb.equal( cb.upper(root.get("sigla")), sigla.toUpperCase() );
    }

    public static Specification<Uf> statusEqual(Integer status) {
        return (root, query, cb) -> cb.equal( root.get("status"), status );
    }

}
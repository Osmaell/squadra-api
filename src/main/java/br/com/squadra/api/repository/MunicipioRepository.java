package br.com.squadra.api.repository;

import br.com.squadra.api.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long>, JpaSpecificationExecutor<Municipio> {

    @Query("SELECT m FROM Municipio m WHERE LOWER(m.nome) = LOWER(:nome) AND m.uf.codigo = :codigoUF ")
    Optional<Municipio> buscarPorNomeEUf(String nome, Long codigoUF);

}
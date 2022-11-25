package br.com.squadra.api.repository;

import br.com.squadra.api.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long>, JpaSpecificationExecutor<Bairro> {

    @Query("SELECT b FROM Bairro b WHERE LOWER(b.nome) = LOWER(:nome) AND b.municipio.codigo = :codigoMunicipio")
    public Optional<Bairro> buscarPorNomeEMunicipio(String nome, Long codigoMunicipio);

}
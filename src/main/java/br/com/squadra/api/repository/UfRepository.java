package br.com.squadra.api.repository;

import br.com.squadra.api.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long>, JpaSpecificationExecutor<Uf> {

    @Query("SELECT u FROM Uf u WHERE LOWER(u.nome) = LOWER(:nome)")
    public List<Uf> buscarPorNome( @Param("nome") String nome );

    @Query("SELECT u FROM Uf u WHERE LOWER(u.sigla) = LOWER(:sigla)")
    public List<Uf> buscarPorSigla( @Param("sigla") String sigla );

}
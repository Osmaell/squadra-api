package br.com.squadra.api.repository;

import br.com.squadra.api.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long>, JpaSpecificationExecutor<Bairro> {

}
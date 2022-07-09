package br.com.viniciuscavalheiro.springbootcommysql.repository;

import br.com.viniciuscavalheiro.springbootcommysql.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}

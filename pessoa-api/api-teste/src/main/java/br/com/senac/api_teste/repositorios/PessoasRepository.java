package br.com.senac.api_teste.repositorios;

import br.com.senac.api_teste.entidades.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, Long> {

}

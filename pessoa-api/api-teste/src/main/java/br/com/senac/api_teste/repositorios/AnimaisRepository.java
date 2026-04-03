package br.com.senac.api_teste.repositorios;

import br.com.senac.api_teste.entidades.Animais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimaisRepository extends JpaRepository<Animais, Long> {

}

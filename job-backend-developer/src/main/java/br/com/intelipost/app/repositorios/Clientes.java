package br.com.intelipost.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.intelipost.app.entity.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>{
}

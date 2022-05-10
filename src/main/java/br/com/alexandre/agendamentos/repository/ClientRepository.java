package br.com.alexandre.agendamentos.repository;

import br.com.alexandre.agendamentos.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

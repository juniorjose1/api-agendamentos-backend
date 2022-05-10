package br.com.alexandre.agendamentos.repository;

import br.com.alexandre.agendamentos.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services, Long> {

    List<Services> findByCategory(String category);
}

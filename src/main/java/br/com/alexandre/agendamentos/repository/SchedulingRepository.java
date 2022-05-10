package br.com.alexandre.agendamentos.repository;

import br.com.alexandre.agendamentos.model.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

    //@Query(value = "select *.s, *.c from scheduling s, client c where s.id = ")
    //List<Scheduling> getSchedulingByIdClient(Long id);
}

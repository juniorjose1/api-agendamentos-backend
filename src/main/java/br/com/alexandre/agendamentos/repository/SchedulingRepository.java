package br.com.alexandre.agendamentos.repository;

import br.com.alexandre.agendamentos.model.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

    @Query(value = "select s.* from scheduling s, client c where s.client_id = c.id and c.cellphone = :cellPhone", nativeQuery = true)
    List<Scheduling> getSchedulingByCellPhoneClient(String cellPhone);
}

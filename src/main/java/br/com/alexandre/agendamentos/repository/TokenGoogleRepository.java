package br.com.alexandre.agendamentos.repository;

import br.com.alexandre.agendamentos.model.TokenGoogle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenGoogleRepository extends JpaRepository<TokenGoogle, Long> {

    @Query(value = "select * from ( select * from TOKEN_GOOGLE tg order by tg.creation_date desc) t limit 1", nativeQuery = true)
    Optional<TokenGoogle> getLastToken();

}

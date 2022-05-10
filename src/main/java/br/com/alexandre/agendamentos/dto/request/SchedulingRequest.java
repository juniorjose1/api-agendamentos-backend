package br.com.alexandre.agendamentos.dto.request;

import br.com.alexandre.agendamentos.model.Client;
import br.com.alexandre.agendamentos.model.Services;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class SchedulingRequest implements Serializable {

    private static final long serialVersionUID = 3658628669525520056L;

    private Long idClient;
    private Long idServices;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateTime;
}

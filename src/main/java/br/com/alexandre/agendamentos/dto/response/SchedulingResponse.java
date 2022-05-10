package br.com.alexandre.agendamentos.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class SchedulingResponse implements Serializable {

    private static final long serialVersionUID = 3658628669525520056L;

    private LocalDateTime dateTime;

}

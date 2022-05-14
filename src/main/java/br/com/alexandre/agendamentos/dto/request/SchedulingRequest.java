package br.com.alexandre.agendamentos.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Builder
public class SchedulingRequest implements Serializable {

    private static final long serialVersionUID = 3658628669525520056L;

    private Long idClient;
    private Long idServices;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'-03:00'")
    private OffsetDateTime startDateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'-03:00'")
    private OffsetDateTime endDateTime;
}

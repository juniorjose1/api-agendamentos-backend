package br.com.alexandre.agendamentos.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Builder
public class SchedulingResponse implements Serializable {

    private static final long serialVersionUID = 3658628669525520056L;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime startDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime endDateTime;
}

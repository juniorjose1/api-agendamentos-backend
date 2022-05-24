package br.com.alexandre.agendamentos.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class EventCalendarRequest {

    private Start start;

    private End end;

    private String summary;

    private String description;

    @Data
    @Builder
    public static class Start{
        private OffsetDateTime dateTime;
    }

    @Data
    @Builder
    public static class End{
        private OffsetDateTime dateTime;
    }
}

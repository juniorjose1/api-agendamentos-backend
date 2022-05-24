package br.com.alexandre.agendamentos.client;

import br.com.alexandre.agendamentos.dto.request.EventCalendarRequest;
import br.com.alexandre.agendamentos.dto.response.EventResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "https://www.googleapis.com/calendar/v3/calendars", name = "calendarGoogle")
public interface CalendarGoogleClient {

    @PostMapping("/primary/events")
    EventResponse insertEvent(@RequestHeader("Authorization") String token, EventCalendarRequest eventCalendarRequest);

    @DeleteMapping("primary/events/{eventId}")
    void deleteEvent(@RequestHeader("Authorization") String token, @PathVariable String eventId);
}

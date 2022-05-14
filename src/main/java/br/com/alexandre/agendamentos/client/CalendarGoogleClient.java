package br.com.alexandre.agendamentos.client;

import br.com.alexandre.agendamentos.dto.request.EventCalendarRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://www.googleapis.com/calendar/v3/calendars", name = "calendarGoogle")
public interface CalendarGoogleClient {

    @PostMapping("/primary/events")
    void insertEvent(@RequestHeader("Authorization") String token, EventCalendarRequest eventCalendarRequest);

}

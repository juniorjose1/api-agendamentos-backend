package br.com.alexandre.agendamentos.service;

import br.com.alexandre.agendamentos.client.CalendarGoogleClient;
import br.com.alexandre.agendamentos.controller.GoogleCalendarController;
import br.com.alexandre.agendamentos.dto.request.EventCalendarRequest;
import br.com.alexandre.agendamentos.dto.request.SchedulingRequest;
import br.com.alexandre.agendamentos.dto.response.EventResponse;
import br.com.alexandre.agendamentos.dto.response.SchedulingResponse;
import br.com.alexandre.agendamentos.dto.response.TokenGoogleResponse;
import br.com.alexandre.agendamentos.model.Client;
import br.com.alexandre.agendamentos.model.Scheduling;
import br.com.alexandre.agendamentos.model.Services;
import br.com.alexandre.agendamentos.model.TokenGoogle;
import br.com.alexandre.agendamentos.repository.SchedulingRepository;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SchedulingService {

    @Autowired
    private SchedulingRepository repository;

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private CalendarGoogleClient calendarGoogleClient;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TokenGoogleService tokenGoogleService;

    @Autowired
    private GoogleCalendarController googleCalendarController;

    public List<SchedulingResponse> getAll(){
        List<Scheduling> schedules = repository.findAll();
        List<SchedulingResponse> scheResponses = new ArrayList<>();
        schedules.forEach(s -> {
            SchedulingResponse scheResponse = SchedulingResponse.builder().build();
            BeanUtils.copyProperties(s, scheResponse);
            scheResponses.add(scheResponse);
        });
        return scheResponses;
    }

    public void post(SchedulingRequest request) {
        Client client = clientService.findById(request.getIdClient());
        Services services= servicesService.findById(request.getIdServices());

        EventCalendarRequest eventCalendarRequest =
                EventCalendarRequest.builder().summary(client.getName() + " - " + services.getName())
                        .description(client.getCellphone())
                        .start(EventCalendarRequest.Start.builder().dateTime(request.getStartDateTime()).build())
                        .end(EventCalendarRequest.End.builder().dateTime(request.getEndDateTime()).build())
                        .build();

        String accessToken = googleCalendarController.getAccessToken();

        Scheduling sche = Scheduling.builder()
                .client(client)
                .services(services)
                .startDateTime(request.getStartDateTime())
                .endDateTime(request.getEndDateTime())
                .build();
        try {
            EventResponse eventResponse = calendarGoogleClient.insertEvent("Bearer " + accessToken, eventCalendarRequest);
            sche.setIdEvent(eventResponse.getId());
            repository.save(sche);
        } catch(FeignException.Unauthorized e){
            TokenGoogleResponse accessTokenUpdated = googleCalendarController.refreshToken();
            EventResponse eventResponse = calendarGoogleClient.insertEvent("Bearer " + accessTokenUpdated.getAccessToken(), eventCalendarRequest);
            sche.setIdEvent(eventResponse.getId());
            try{
                repository.save(sche);
            } catch(Exception e2){
                calendarGoogleClient.deleteEvent("Bearer " + accessTokenUpdated.getAccessToken(), sche.getIdEvent());
            }
        } catch(Exception e){
            calendarGoogleClient.deleteEvent("Bearer " + accessToken, sche.getIdEvent());
        }

    }

    public void put(Long id, SchedulingRequest request) {
        Optional<Scheduling> scheOptional = repository.findById(id);
        Scheduling scheFounded = scheOptional.get();
        BeanUtils.copyProperties(request, scheFounded);
        repository.save(scheFounded);
    }

    public void delete(Long id) {
        Optional<Scheduling> scheOptional = repository.findById(id);
        Scheduling scheFounded = scheOptional.get();
        repository.delete(scheFounded);
    }

    public List<SchedulingResponse> getByCellPhone(String cellPhone) {
        List<Scheduling> schedules = repository.getSchedulingByCellPhoneClient(cellPhone);
        List<SchedulingResponse> scheResponses = new ArrayList<>();
        schedules.forEach(s -> {
            SchedulingResponse scheResponse = SchedulingResponse.builder().build();
            BeanUtils.copyProperties(s, scheResponse);
            scheResponses.add(scheResponse);
        });
        return scheResponses;
    }
}

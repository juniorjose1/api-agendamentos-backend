package br.com.alexandre.agendamentos.service;

import br.com.alexandre.agendamentos.dto.request.SchedulingRequest;
import br.com.alexandre.agendamentos.dto.response.SchedulingResponse;
import br.com.alexandre.agendamentos.model.Client;
import br.com.alexandre.agendamentos.model.Scheduling;
import br.com.alexandre.agendamentos.model.Services;
import br.com.alexandre.agendamentos.repository.SchedulingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchedulingService {

    @Autowired
    private SchedulingRepository repository;

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private ClientService clientService;

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
        Scheduling sche = Scheduling.builder()
                .client(client)
                .services(services)
                .dateTime(request.getDateTime())
                .build();

        repository.save(sche);
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

   /* public Object getByIdClient(Long idClient) {
        Scheduling sche =
        Optional<Scheduling> scheOptional = repository.findById(id);
        Scheduling scheFounded = scheOptional.get();
    }*/
}
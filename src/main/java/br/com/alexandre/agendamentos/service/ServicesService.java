package br.com.alexandre.agendamentos.service;

import br.com.alexandre.agendamentos.dto.ServicesDto;
import br.com.alexandre.agendamentos.model.Services;
import br.com.alexandre.agendamentos.repository.ServicesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository repository;

    public List<ServicesDto> findAll(){
        List<Services> services = repository.findAll();
        List<ServicesDto> servicesRespons = new ArrayList<>();
        services.forEach(s -> {
            ServicesDto servicesDto = ServicesDto.builder().build();
            BeanUtils.copyProperties(s, servicesDto);
            servicesRespons.add(servicesDto);
        });
        return servicesRespons;
    }

    public Services findById(Long id){
        Optional<Services> services = repository.findById(id);
        return services.get();
    }

    public List<ServicesDto> findByCategory(String category){
        List<Services> services = repository.findByCategory(category);
        List<ServicesDto> servicesRespons = new ArrayList<>();
        services.forEach(s -> {
            ServicesDto servicesDto = ServicesDto.builder().build();
            BeanUtils.copyProperties(s, servicesDto);
            servicesRespons.add(servicesDto);
        });
        return servicesRespons;
    }

    public void post(ServicesDto servicesDto) {
        Services services = Services.builder().build();
        BeanUtils.copyProperties(servicesDto, services);
        repository.save(services);
    }
}

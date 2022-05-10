package br.com.alexandre.agendamentos.service;

import br.com.alexandre.agendamentos.dto.request.ClientRequest;
import br.com.alexandre.agendamentos.model.Client;
import br.com.alexandre.agendamentos.repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public void post(ClientRequest request){
        Client client = Client.builder().build();
        BeanUtils.copyProperties(request, client);
        repository.save(client);
    }

    public Client findById(Long id){
        Optional<Client> client = repository.findById(id);
        return client.get();
    }
}

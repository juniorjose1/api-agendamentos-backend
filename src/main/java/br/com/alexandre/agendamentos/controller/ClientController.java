package br.com.alexandre.agendamentos.controller;

import br.com.alexandre.agendamentos.dto.request.ClientRequest;
import br.com.alexandre.agendamentos.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ClientRequest request){
        service.post(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

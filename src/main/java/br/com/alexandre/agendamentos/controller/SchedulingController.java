package br.com.alexandre.agendamentos.controller;

import br.com.alexandre.agendamentos.dto.request.SchedulingRequest;
import br.com.alexandre.agendamentos.dto.response.SchedulingResponse;
import br.com.alexandre.agendamentos.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingService service;

    @GetMapping
    public ResponseEntity<List<SchedulingResponse>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    /*@GetMapping("/{idCLient}")
    public ResponseEntity<List<SchedulingResponse>> getByIdClient(@PathVariable Long idClient){
        return new ResponseEntity<>(service.getByIdClient(idClient), HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<?> post(@RequestBody SchedulingRequest request){
        service.post(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, SchedulingRequest request){
        service.put(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

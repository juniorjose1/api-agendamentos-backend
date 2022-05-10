package br.com.alexandre.agendamentos.controller;

import br.com.alexandre.agendamentos.dto.ServicesDto;
import br.com.alexandre.agendamentos.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController {

    @Autowired
    private ServicesService service;

    @GetMapping
    public ResponseEntity<List<ServicesDto>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<ServicesDto>> getByCategory(@PathVariable String category){
        return new ResponseEntity<>(service.findByCategory(category), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ServicesDto servicesDto){
        service.post(servicesDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

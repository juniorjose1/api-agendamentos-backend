package br.com.alexandre.agendamentos.service;

import br.com.alexandre.agendamentos.client.TokenGoogleClient;
import br.com.alexandre.agendamentos.dto.response.TokenGoogleResponse;
import br.com.alexandre.agendamentos.model.TokenGoogle;
import br.com.alexandre.agendamentos.repository.TokenGoogleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenGoogleService {

    @Autowired
    private TokenGoogleRepository repository;

    @Autowired
    private TokenGoogleClient tokenGoogleClient;

    public void post(TokenGoogle token){
        repository.save(token);
    }

    public TokenGoogle get(){
        return repository.getLastToken().get();
    }

}

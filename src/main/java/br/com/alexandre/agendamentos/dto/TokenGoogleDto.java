package br.com.alexandre.agendamentos.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class TokenGoogleDto implements Serializable {

    private static final long serialVersionUID = 3658628669525520056L;

    private String token;
}

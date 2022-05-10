package br.com.alexandre.agendamentos.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ServicesDto implements Serializable {

    private static final long serialVersionUID = 3658628669525520056L;

    private String name;
    private String category;
    private Long price;
}

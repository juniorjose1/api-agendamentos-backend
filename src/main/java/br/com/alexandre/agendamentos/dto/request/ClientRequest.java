package br.com.alexandre.agendamentos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
public class ClientRequest implements Serializable {

    private static final long serialVersionUID = 3658628669525520056L;

    private String name;
    private String cellphone;

}

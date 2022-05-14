package br.com.alexandre.agendamentos.dto.request;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TokenGoogleRequest implements Serializable {

    public interface TokenGoogleRequestView {
        interface getToken{};
        interface refreshToken{};
    }

    private static final long serialVersionUID = 3658628669525520056L;

    @JsonView({TokenGoogleRequestView.getToken.class, TokenGoogleRequestView.refreshToken.class})
    private String clientId;

    @JsonView({TokenGoogleRequestView.getToken.class, TokenGoogleRequestView.refreshToken.class})
    private String clientSecret;

    @JsonView(TokenGoogleRequestView.getToken.class)
    private String code;

    @JsonView({TokenGoogleRequestView.getToken.class, TokenGoogleRequestView.refreshToken.class})
    private String grantType;

    @JsonView(TokenGoogleRequestView.getToken.class)
    private String redirectUri;

    @JsonView(TokenGoogleRequestView.refreshToken.class)
    private String refreshToken;



}

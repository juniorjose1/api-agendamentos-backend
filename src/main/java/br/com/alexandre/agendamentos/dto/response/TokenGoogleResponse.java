package br.com.alexandre.agendamentos.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TokenGoogleResponse implements Serializable {

    public interface TokenGoogleResponseView {
        interface getToken{};
        interface refreshToken{};
    }

    private static final long serialVersionUID = 3658628669525520056L;

    @JsonProperty("access_token")
    @JsonView({TokenGoogleResponseView.getToken.class, TokenGoogleResponseView.refreshToken.class})
    private String accessToken;

    @JsonProperty("expires_in")
    @JsonView({TokenGoogleResponseView.getToken.class, TokenGoogleResponseView.refreshToken.class})
    private Long expiresIn;

    @JsonProperty("refresh_token")
    @JsonView(TokenGoogleResponseView.getToken.class)
    private String refreshToken;

    @JsonView({TokenGoogleResponseView.getToken.class, TokenGoogleResponseView.refreshToken.class})
    private String scope;

    @JsonProperty("token_type")
    @JsonView({TokenGoogleResponseView.getToken.class, TokenGoogleResponseView.refreshToken.class})
    private String tokenType;

}

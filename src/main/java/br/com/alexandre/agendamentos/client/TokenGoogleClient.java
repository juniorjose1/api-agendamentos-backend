package br.com.alexandre.agendamentos.client;

import br.com.alexandre.agendamentos.dto.response.TokenGoogleResponse;
import com.fasterxml.jackson.annotation.JsonView;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://oauth2.googleapis.com/token", name = "tokenGoogle")
public interface TokenGoogleClient {

    @PostMapping
    @JsonView(TokenGoogleResponse.TokenGoogleResponseView.getToken.class)
    TokenGoogleResponse getToken(@RequestParam("client_id") String clientId,
                                 @RequestParam("client_secret") String clientSecret,
                                 @RequestParam("code") String code,
                                 @RequestParam("grant_type") String grantType,
                                 @RequestParam("redirect_uri") String redirectUri,
                                 @RequestBody String json);

    @PostMapping
    @JsonView(TokenGoogleResponse.TokenGoogleResponseView.refreshToken.class)
    TokenGoogleResponse refreshToken(@RequestParam("client_id") String clientId,
                                 @RequestParam("client_secret") String clientSecret,
                                 @RequestParam("grant_type") String grantType,
                                 @RequestParam("refresh_token") String refreshToken,
                                 @RequestBody String json);



}

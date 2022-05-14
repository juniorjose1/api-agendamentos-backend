package br.com.alexandre.agendamentos.controller;

import br.com.alexandre.agendamentos.client.TokenGoogleClient;
import br.com.alexandre.agendamentos.dto.response.TokenGoogleResponse;
import br.com.alexandre.agendamentos.model.TokenGoogle;
import br.com.alexandre.agendamentos.service.TokenGoogleService;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@Slf4j
public class GoogleCalendarController {

    private static final String APPLICATION_NAME = "";
    private static HttpTransport httpTransport;
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static com.google.api.services.calendar.Calendar client;

    GoogleClientSecrets clientSecrets;
    GoogleAuthorizationCodeFlow flow;
    Credential credential;

    @Value("${google.client.client-id}")
    private String clientId;
    @Value("${google.client.client-secret}")
    private String clientSecret;
    @Value("${google.client.redirectUri}")
    private String redirectURI;

    @Autowired
    private TokenGoogleClient tokenGoogleClient;

    @Autowired
    private TokenGoogleService tokenGoogleService;

    private Set<Event> events = new HashSet<>();

    final DateTime date1 = new DateTime("2022-05-05T16:30:00.000+05:30");
    final DateTime date2 = new DateTime(new Date());

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception {
        return new RedirectView(authorize());
    }

    @RequestMapping(value = "/login/google", method = RequestMethod.GET, params = "code")
    public ResponseEntity<?> oauth2Callback(@RequestParam(value = "code") String code) {
        log.info("asdasd");

        TokenGoogleResponse tokenGoogleResponse = tokenGoogleClient.getToken(clientId, clientSecret,
                code, "authorization_code", redirectURI, "{}");

        String accessToken = tokenGoogleResponse.getAccessToken();
        String refreshToken = tokenGoogleResponse.getRefreshToken();

        TokenGoogle token = new TokenGoogle();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setCreationDate(LocalDateTime.now());

        tokenGoogleService.post(token);

        return new ResponseEntity<>(HttpStatus.OK);





       /* com.google.api.services.calendar.model.Events eventList;
        String message;
        try {
            TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();
            credential = flow.createAndStoreCredential(response, "userID");
            client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME).build();
            Event eventTest = new Event();
            EventDateTime eventDateTimeStart = new EventDateTime();
            DateTime timeStart = new DateTime("2022-05-12T10:00:00-03:00");
            eventDateTimeStart.setDateTime(timeStart).setTimeZone("America/Sao_Paulo");
            EventDateTime eventDateTimeEnd = new EventDateTime();
            DateTime timeEnd = new DateTime("2022-05-12T11:00:00-03:00");
            eventDateTimeEnd.setDateTime(timeEnd).setTimeZone("America/Sao_Paulo");
            eventTest.setSummary("Evento de Teste Inserido");
            eventTest.setDescription("Descrição do Evento de Teste");
            eventTest.setStart(eventDateTimeStart);
            eventTest.setEnd(eventDateTimeEnd);
            client.events().insert("primary", eventTest).execute();
            Events events = client.events();
            eventList = events.list("primary").setTimeMin(date1).setTimeMax(date2).execute();
            message = eventList.getItems().toString();
            System.out.println("My:" + eventList.getItems());
        } catch (Exception e) {
            log.warn("Exception while handling OAuth2 callback (" + e.getMessage() + ")."
                    + " Redirecting to google connection status page.");
            message = "Exception while handling OAuth2 callback (" + e.getMessage() + ")."
                    + " Redirecting to google connection status page.";
        }

        System.out.println("cal message:" + message);
        return new ResponseEntity<>(message, HttpStatus.OK);*/
    }

    public Set<Event> getEvents() throws IOException {
        return this.events;
    }

    private String authorize() throws Exception {
        AuthorizationCodeRequestUrl authorizationUrl;
        if (flow == null) {
            Details web = new Details();
            web.setClientId(clientId);
            web.setClientSecret(clientSecret);
            clientSecrets = new GoogleClientSecrets().setWeb(web);
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
                    Collections.singleton(CalendarScopes.CALENDAR)).build();
        }
        authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectURI).setAccessType("offline");
        System.out.println("cal authorizationUrl->" + authorizationUrl);
        return authorizationUrl.build();
    }
    public String getAccessToken(){
        TokenGoogle tokenGoogle = tokenGoogleService.get();
        return tokenGoogle.getAccessToken();
    }

    public TokenGoogleResponse refreshToken(){
        TokenGoogle tokenGoogle = tokenGoogleService.get();
        TokenGoogleResponse tokenGoogleResponse = tokenGoogleClient.refreshToken(clientId, clientSecret,
                "refresh_token", tokenGoogle.getRefreshToken(), "{}");
        TokenGoogle tokenGoogleRefresh = new TokenGoogle();
        tokenGoogleRefresh.setRefreshToken(tokenGoogle.getRefreshToken());
        tokenGoogleRefresh.setAccessToken(tokenGoogleResponse.getAccessToken());
        tokenGoogleRefresh.setCreationDate(LocalDateTime.now());
        tokenGoogleService.post(tokenGoogleRefresh);
        return tokenGoogleResponse;
    }
}
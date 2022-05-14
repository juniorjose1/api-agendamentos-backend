package br.com.alexandre.agendamentos.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TokenGoogle implements Serializable {

    private static final long serialVersionUID = -8120852403051272222L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accessToken;
    private String refreshToken;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;

    public TokenGoogle() {
    }

    public TokenGoogle(Long id, String accessToken, String refreshToken, LocalDateTime creationDate) {
        this.id = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenGoogle)) return false;
        TokenGoogle that = (TokenGoogle) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

package br.com.alexandre.agendamentos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

@Builder
@Entity
public class Scheduling implements Serializable {

    private static final long serialVersionUID = -8120852403051272222L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime startDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime endDateTime;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Services services;

    public Scheduling() {
    }

    public Scheduling(Long id, OffsetDateTime startDateTime, OffsetDateTime endDateTime, Client client, Services services) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.client = client;
        this.services = services;
    }

    public Long getId() {
        return id;
    }

    public OffsetDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(OffsetDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public OffsetDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(OffsetDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scheduling)) return false;
        Scheduling that = (Scheduling) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

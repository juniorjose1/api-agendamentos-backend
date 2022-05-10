package br.com.alexandre.agendamentos.model;

import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Builder
@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = -8120852403051272222L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String cellphone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Scheduling> schedules;

    public Client() {
    }

    public Client(Long id, String name, String cellphone, List<Scheduling> schedules) {
        this.id = id;
        this.name = name;
        this.cellphone = cellphone;
        this.schedules = schedules;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public List<Scheduling> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Scheduling> schedules) {
        this.schedules = schedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getId().equals(client.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

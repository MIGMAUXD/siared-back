package BackendSiadseUfps.siadse.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "estados_pqrs")
public class EstadosPQRS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

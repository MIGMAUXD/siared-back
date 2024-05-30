package BackendSiadseUfps.siadse.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "cambio_estado_radicado")
public class CambioEstRad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_pqrs", nullable = false)
    private PQRS pqrs;

    @ManyToOne
    @JoinColumn(name = "id_estados", nullable = false)
    private EstadosPQRS estado;

    private Date fecha_cambio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PQRS getPqrs() {
        return pqrs;
    }

    public void setPqrs(PQRS pqrs) {
        this.pqrs = pqrs;
    }

    public EstadosPQRS getEstado() {
        return estado;
    }

    public void setEstado(EstadosPQRS estado) {
        this.estado = estado;
    }

    public Date getFecha_cambio() {
        return fecha_cambio;
    }

    public void setFecha_cambio(Date fecha_cambio) {
        this.fecha_cambio = fecha_cambio;
    }
}

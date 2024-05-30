package BackendSiadseUfps.siadse.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


import java.util.Date;

@Data
@Entity
@Table(name = "pqrs")
public class PQRS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 30, message = "La descripción debe tener como máximo 300 caracteres")
    @NotNull(message = "Titulo del PQRS no puede ser nulo")
    private String titulo;

    @Size(max = 100, message = "La descripción debe tener como máximo 300 caracteres")
    @NotNull(message = "Descripcion del PQRS no puede ser nulo")
    private String descripcion;

    private Date fechaRadicado;

    @ManyToOne
    @JoinColumn(name = "id_estados", nullable = false)
    private EstadosPQRS estadoRadicado;

    @Size(max = 30, message = "La descripción debe tener como máximo 300 caracteres")
    @NotNull(message = "Correo del radicado no puede ser nulo")
    private String correo;

    @ManyToOne
    @JoinColumn(name = "id_tipos_pqrs", nullable = false)
    private TiposPQRS tipoPqrs;

    @NotNull(message = "Anonimo no puede quedar sin eleccion")
    private Boolean anonimo;

    @Size(max = 30, message = "La nombre PQRS debe tener como máximo 300 caracteres")
    private String nombre;
    
    @Size(max = 30, message = "El apellido debe tener como máximo 300 caracteres")
    private String apellido;

    @Size(max = 30, message = "La CEDULA PQRS debe tener como máximo 300 caracteres")
    private String cedula;

    @Size(max = 50, message = "El semillero PQRS debe tener como máximo 300 caracteres")
    private String semillero;

    @Size(max = 100, message = "El codigo radica PQRS debe tener como máximo 300 caracteres")
    private String codigoRadicado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRadicado() {
        return fechaRadicado;
    }

    public void setFechaRadicado(Date fechaRadicado) {
        this.fechaRadicado = fechaRadicado;
    }

    public EstadosPQRS getEstadoRadicado() {
        return estadoRadicado;
    }

    public void setEstadoRadicado(EstadosPQRS estadoRadicado) {
        this.estadoRadicado = estadoRadicado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public TiposPQRS getTipoPqrs() {
        return tipoPqrs;
    }

    public void setTipoPqrs(TiposPQRS tipoPqrs) {
        this.tipoPqrs = tipoPqrs;
    }

    public Boolean getAnonimo() {
        return anonimo;
    }

    public void setAnonimo(Boolean anonimo) {
        this.anonimo = anonimo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getSemillero() {
        return semillero;
    }

    public void setSemillero(String semillero) {
        this.semillero = semillero;
    }

    public String getCodigoRadicado() {
        return codigoRadicado;
    }

    public void setCodigoRadicado(String codigoRadicado) {
        this.codigoRadicado = codigoRadicado;
    }
}

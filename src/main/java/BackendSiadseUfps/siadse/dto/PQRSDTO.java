package BackendSiadseUfps.siadse.dto;


import BackendSiadseUfps.siadse.entity.EstadosPQRS;
import BackendSiadseUfps.siadse.entity.TiposPQRS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PQRSDTO {

    private Integer id;
    private String titulo;
    private String descripcion;
    private Date fechaRadicado;
    private EstadosPQRS estadoRadicado;
    private String correo;
    private TiposPQRS tipoPqrs;
    private Boolean anonimo;
    private String nombre;
    private String apellido;
    private String cedula;
    private String semillero;
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

    public void setTipoPqrs(TiposPQRS tiposPqrs) {
        this.tipoPqrs = tiposPqrs;
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

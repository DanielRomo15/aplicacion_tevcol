package com.utc.aplicacion_tevcol.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "acta_brigada")
public class ActaBrigada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_acta_brigada")
    private Long codigoActaBrigada;

    @Column(name = "fecha_acta_brigada", nullable = false)
    private LocalDate fechaActaBrigada;

    
    @Column(name = "estado_acta_brigada", nullable = false, length = 30)
    private String estadoActaBrigada;

    
    @Column(name = "link_archivo_acta_brigada")
    private String linkArchivoActaBrigada;

    @Column(name = "fecha_creado_acta_brigada")
    private LocalDateTime fechaCreadoActaBrigada;

    @Column(name = "fecha_editado_acta_brigada")
    private LocalDateTime fechaEditadoActaBrigada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_tipo_brigada")
    private TipoBrigada tipoBrigada;

    @Column(name = "fk_cod_establecimiento")
    private Long fkCodEstablecimiento;

    @PrePersist
    public void prePersist() {
        if (fechaCreadoActaBrigada == null) fechaCreadoActaBrigada = LocalDateTime.now();
        if (fechaEditadoActaBrigada == null) fechaEditadoActaBrigada = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        fechaEditadoActaBrigada = LocalDateTime.now();
    }

    public Long getCodigoActaBrigada() { return codigoActaBrigada; }
    public void setCodigoActaBrigada(Long codigoActaBrigada) { this.codigoActaBrigada = codigoActaBrigada; }

    public LocalDate getFechaActaBrigada() { return fechaActaBrigada; }
    public void setFechaActaBrigada(LocalDate fechaActaBrigada) { this.fechaActaBrigada = fechaActaBrigada; }

    public String getEstadoActaBrigada() { return estadoActaBrigada; }
    public void setEstadoActaBrigada(String estadoActaBrigada) { this.estadoActaBrigada = estadoActaBrigada; }

    public String getLinkArchivoActaBrigada() { return linkArchivoActaBrigada; }
    public void setLinkArchivoActaBrigada(String linkArchivoActaBrigada) { this.linkArchivoActaBrigada = linkArchivoActaBrigada; }

    public LocalDateTime getFechaCreadoActaBrigada() { return fechaCreadoActaBrigada; }
    public void setFechaCreadoActaBrigada(LocalDateTime fechaCreadoActaBrigada) { this.fechaCreadoActaBrigada = fechaCreadoActaBrigada; }

    public LocalDateTime getFechaEditadoActaBrigada() { return fechaEditadoActaBrigada; }
    public void setFechaEditadoActaBrigada(LocalDateTime fechaEditadoActaBrigada) { this.fechaEditadoActaBrigada = fechaEditadoActaBrigada; }

    public TipoBrigada getTipoBrigada() { return tipoBrigada; }
    public void setTipoBrigada(TipoBrigada tipoBrigada) { this.tipoBrigada = tipoBrigada; }

    public Long getFkCodEstablecimiento() { return fkCodEstablecimiento; }
    public void setFkCodEstablecimiento(Long fkCodEstablecimiento) { this.fkCodEstablecimiento = fkCodEstablecimiento; }
}
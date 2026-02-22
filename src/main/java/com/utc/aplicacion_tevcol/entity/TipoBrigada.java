package com.utc.aplicacion_tevcol.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipo_brigada")
public class TipoBrigada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_brigada")
    private Long codigoBrigada;

    @Column(name = "descripcion_brigada", nullable = false)
    private String descripcionBrigada;

    @Column(name = "link_brigada", nullable = false)
    private String linkBrigada;

    @Column(name = "fecha_creado_brigada")
    private LocalDateTime fechaCreadoBrigada;

    @Column(name = "fecha_editado_brigada")
    private LocalDateTime fechaEditadoBrigada;

    @Column(name = "fk_cod_establecimiento", nullable = false)
    private Long fkCodEstablecimiento;

    @OneToMany(mappedBy = "tipoBrigada", cascade = CascadeType.ALL)
    private List<ActaBrigada> actas = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (fechaCreadoBrigada == null) fechaCreadoBrigada = LocalDateTime.now();
        if (fechaEditadoBrigada == null) fechaEditadoBrigada = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        fechaEditadoBrigada = LocalDateTime.now();
    }

    public Long getCodigoBrigada() { return codigoBrigada; }
    public void setCodigoBrigada(Long codigoBrigada) { this.codigoBrigada = codigoBrigada; }

    public String getDescripcionBrigada() { return descripcionBrigada; }
    public void setDescripcionBrigada(String descripcionBrigada) { this.descripcionBrigada = descripcionBrigada; }

    public String getLinkBrigada() { return linkBrigada; }
    public void setLinkBrigada(String linkBrigada) { this.linkBrigada = linkBrigada; }

    public LocalDateTime getFechaCreadoBrigada() { return fechaCreadoBrigada; }
    public void setFechaCreadoBrigada(LocalDateTime fechaCreadoBrigada) { this.fechaCreadoBrigada = fechaCreadoBrigada; }

    public LocalDateTime getFechaEditadoBrigada() { return fechaEditadoBrigada; }
    public void setFechaEditadoBrigada(LocalDateTime fechaEditadoBrigada) { this.fechaEditadoBrigada = fechaEditadoBrigada; }

    public Long getFkCodEstablecimiento() { return fkCodEstablecimiento; }
    public void setFkCodEstablecimiento(Long fkCodEstablecimiento) { this.fkCodEstablecimiento = fkCodEstablecimiento; }

    public List<ActaBrigada> getActas() { return actas; }
    public void setActas(List<ActaBrigada> actas) { this.actas = actas; }
}
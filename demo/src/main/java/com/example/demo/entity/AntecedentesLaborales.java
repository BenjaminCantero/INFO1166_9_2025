package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "antecedentes_laborales")
public class AntecedentesLaborales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String puesto;
    private int aniosExperiencia;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresas empresa;

    @ManyToOne
    @JoinColumn(name = "tipo_contrato_id")
    private TiposContratos tipoContrato;

    @ManyToOne
    @JoinColumn(name = "datos_personales_id")
    private DatosPersonales datosPersonales;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public Empresas getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }

    public TiposContratos getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TiposContratos tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public DatosPersonales getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(DatosPersonales datosPersonales) {
        this.datosPersonales = datosPersonales;
    }
}

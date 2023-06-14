package ec.edu.espe.arquitectura.facturacion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Embeddable
public class ImpuestoPorcentajePK implements Serializable {
    @Column(name = "PORCENTAJE", precision = 4, scale = 1, nullable = false)
    private BigDecimal porcentaje;
    @Column(name = "COD_IMPUESTO", length = 3, nullable = false)
    private String codigoImpuesto;
    @Column(name = "FECHA_INICIO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    public ImpuestoPorcentajePK() {
    }

    public ImpuestoPorcentajePK(BigDecimal porcentaje, String codigoImpuesto, Date fechaInicio) {
        this.porcentaje = porcentaje;
        this.codigoImpuesto = codigoImpuesto;
        this.fechaInicio = fechaInicio;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getCodigoImpuesto() {
        return codigoImpuesto;
    }

    public void setCodigoImpuesto(String codigoImpuesto) {
        this.codigoImpuesto = codigoImpuesto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public String toString() {
        return "ImpuestoPorcentajePK [porcentaje=" + porcentaje + ", codigoImpuesto=" + codigoImpuesto
                + ", fechaInicio=" + fechaInicio + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((porcentaje == null) ? 0 : porcentaje.hashCode());
        result = prime * result + ((codigoImpuesto == null) ? 0 : codigoImpuesto.hashCode());
        result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ImpuestoPorcentajePK other = (ImpuestoPorcentajePK) obj;
        if (porcentaje == null) {
            if (other.porcentaje != null)
                return false;
        } else if (!porcentaje.equals(other.porcentaje))
            return false;
        if (codigoImpuesto == null) {
            if (other.codigoImpuesto != null)
                return false;
        } else if (!codigoImpuesto.equals(other.codigoImpuesto))
            return false;
        if (fechaInicio == null) {
            if (other.fechaInicio != null)
                return false;
        } else if (!fechaInicio.equals(other.fechaInicio))
            return false;
        return true;
    }

}
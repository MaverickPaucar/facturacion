package ec.edu.espe.arquitectura.facturacion.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

@Entity
@Table(name = "IMPUESTO_PORCENTAJE")
public class ImpuestoPorcentaje {

    @EmbeddedId
    private ImpuestoPorcentajePK pk;

    @Column(name = "ESTADO", length = 3, nullable = false)
    private String estado;
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_INICIO", nullable = false)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    private Date fechaFin;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;

    @ManyToOne
    @JoinColumn(name = "COD_IMPUESTO", referencedColumnName = "COD_IMPUESTO", insertable = false, updatable = false)
    private Impuesto impuesto;

    public ImpuestoPorcentaje() {
    }

    public ImpuestoPorcentaje(ImpuestoPorcentajePK pk) {
        this.pk = pk;
    }

    public ImpuestoPorcentajePK getPk() {
        return pk;
    }

    public void setPk(ImpuestoPorcentajePK pk) {
        this.pk = pk;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
        ImpuestoPorcentaje other = (ImpuestoPorcentaje) obj;
        if (pk == null) {
            if (other.pk != null)
                return false;
        } else if (!pk.equals(other.pk))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ImpuestoPorcentaje [pk=" + pk + ", estado=" + estado + ", fechaInicio=" + fechaInicio + ", fechaFin="
                + fechaFin + "]";
    }

}

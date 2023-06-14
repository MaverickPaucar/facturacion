package ec.edu.espe.arquitectura.facturacion.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

@Entity
@Table(name = "FACTURA_IMPUESTO")
public class FacturaImpuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FACTURA_IMPUESTO_ID", nullable = false)
    private Integer codigo;
    @Column(name = "COD_FACTURA", nullable = false)
    private Integer codigoFactura;
    @Column(name = "COD_IMPUESTO", length = 3, nullable = false)
    private String codigoImpuesto;
    @Column(name = "PORCENTAJE", precision = 4, scale = 1, nullable = false)
    private BigDecimal porcentaje;
    @Column(name = "FECHA_INICIO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "VALOR", precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;
    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;

    @ManyToOne
    @JoinColumn(name = "COD_FACTURA", referencedColumnName = "COD_FACTURA", insertable = false, updatable = false)
    private Factura factura;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "COD_IMPUESTO", referencedColumnName = "COD_IMPUESTO", insertable = false, updatable = false),
            @JoinColumn(name = "PORCENTAJE", referencedColumnName = "PORCENTAJE", insertable = false, updatable = false),
            @JoinColumn(name = "FECHA_INICIO", referencedColumnName = "FECHA_INICIO", insertable = false, updatable = false)
    })
    private ImpuestoPorcentaje impuestoPorcentaje;

    public FacturaImpuesto() {
    }

    public FacturaImpuesto(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(Integer codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getCodigoImpuesto() {
        return codigoImpuesto;
    }

    public void setCodigoImpuesto(String codigoImpuesto) {
        this.codigoImpuesto = codigoImpuesto;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public ImpuestoPorcentaje getImpuestoPorcentaje() {
        return impuestoPorcentaje;
    }

    public void setImpuestoPorcentaje(ImpuestoPorcentaje impuestoPorcentaje) {
        this.impuestoPorcentaje = impuestoPorcentaje;
    }

    @Override
    public String toString() {
        return "FacturaImpuesto [codigo=" + codigo + ", codigoFactura=" + codigoFactura + ", codigoImpuesto="
                + codigoImpuesto + ", porcentaje=" + porcentaje + ", valor=" + valor + ", version=" + version
                + ", factura=" + factura + ", impuestoPorcentaje=" + impuestoPorcentaje + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
        FacturaImpuesto other = (FacturaImpuesto) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

}
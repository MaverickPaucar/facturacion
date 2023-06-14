package ec.edu.espe.arquitectura.facturacion.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

@Entity
@Table(name = "FACTURA")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COD_FACTURA", nullable = false)
    private Integer codigo;
    @Column(name = "COD_CLIENTE", nullable = false)
    private Integer codigoCliente;
    @Column(name = "COD_ESTABLECIMIENTO", length = 3, nullable = false)
    private String codigoEstablecimiento;
    @Column(name = "PUNTO_EMISION", length = 3, nullable = false)
    private String puntoEmision;
    @Column(name = "SECUENCIAL", precision = 9, scale = 0, nullable = false)
    private Integer secuencial;
    @Column(name = "NUMERO_AUTORIZACION", length = 40)
    private String autorizacion;
    @Column(name = "FECHA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "SUBTOTAL", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;
    @Column(name = "TOTAL", precision = 10, scale = 2, nullable = false)
    private BigDecimal total;
    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;

    @ManyToOne
    @JoinColumn(name = "COD_CLIENTE", referencedColumnName = "COD_CLIENTE", insertable = false, updatable = false)
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura", fetch = FetchType.LAZY)
    private List<FacturaDetalle> facturaDetalles;

    public Factura() {
    }

    public Factura(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getCodigoEstablecimiento() {
        return codigoEstablecimiento;
    }

    public void setCodigoEstablecimiento(String codigoEstablecimiento) {
        this.codigoEstablecimiento = codigoEstablecimiento;
    }

    public String getPuntoEmision() {
        return puntoEmision;
    }

    public void setPuntoEmision(String puntoEmision) {
        this.puntoEmision = puntoEmision;
    }

    public Integer getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(Integer secuencial) {
        this.secuencial = secuencial;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Factura [codigo=" + codigo + ", codigoCliente=" + codigoCliente + ", codigoEstablecimiento="
                + codigoEstablecimiento + ", puntoEmision=" + puntoEmision + ", secuencial=" + secuencial
                + ", autorizacion=" + autorizacion + ", fecha=" + fecha + ", subtotal=" + subtotal + ", total=" + total
                + ", version=" + version + ", cliente=" + cliente + "]";
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
        Factura other = (Factura) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

}
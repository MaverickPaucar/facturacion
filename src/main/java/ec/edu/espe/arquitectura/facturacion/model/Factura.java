package ec.edu.espe.arquitectura.facturacion.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "FACTURA")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COD_FACTURA", nullable = false)
    private Long codigo;
    @Column(name = "COD_CLIENTE", nullable = false)
    private Integer codigoCliente;
    @Column(name = "COD_ESTABLECIMIENTO", nullable = false, length = 3)
    private String establecimiento;
    @Column(name = "PUNTO_EMISION", nullable = false, length = 3)
    private String puntoEmision;
    @Column(name = "SECUENCIAL", precision = 9, scale = 0, nullable = false)
    private Integer secuencial;
    @Column(name = "NUMERO_AUTORIZACION", nullable = false, length = 40)
    private String numeroAutorizacion;
    @Column(name = "FECHA", nullable = false)
    private Date fecha;
    @Column(name = "SUBTOTAL", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;
    @Column(name = "TOTAL", precision = 10, scale = 2, nullable = false)
    private BigDecimal total;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;

    @ManyToOne
    @JoinColumn(name = "COD_CLIENTE", referencedColumnName = "COD_CLIENTE", insertable = false, updatable = false, nullable = false)
    private Cliente cliente;

    public Factura() {
    }

    public Factura(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
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

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
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

    @Override
    public String toString() {
        return "Factura [codigo=" + codigo + ", codigoCliente=" + codigoCliente + ", establecimiento=" + establecimiento
                + ", puntoEmision=" + puntoEmision + ", secuencial=" + secuencial + ", numeroAutorizacion="
                + numeroAutorizacion + ", fecha=" + fecha + ", subtotal=" + subtotal + ", total=" + total + ", version="
                + version + ", cliente=" + cliente + "]";
    }

}

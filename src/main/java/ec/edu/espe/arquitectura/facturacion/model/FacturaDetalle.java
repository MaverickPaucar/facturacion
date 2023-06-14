package ec.edu.espe.arquitectura.facturacion.model;

import java.math.BigDecimal;

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
@Table(name = "FACTURA_DETALLE")
public class FacturaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COD_FACTURA_DETALLE", nullable = false, length = 5)
    private Long codigo;
    @Column(name = "COD_FACTURA", nullable = false, length = 15)
    private Long codigoFactura;
    @Column(name = "COD_PRODUCTO", nullable = false, length = 20)
    private String codigoProducto;
    @Column(name = "CANTIDAD", precision = 10, scale = 2, nullable = false)
    private BigDecimal existencia;
    @Column(name = "NOMBRE_PRODUCTO", length = 100, nullable = false)
    private String nombre;
    @Column(name = "PRECIO_UNITARIO", precision = 10, scale = 2, nullable = false)
    private BigDecimal unitario;
    @Column(name = "SUBTOTAL", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;

    @ManyToOne
    @JoinColumn(name = "COD_FACTURA", referencedColumnName = "COD_FACTURA", insertable = false, updatable = false, nullable = false)
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "COD_PRODUCTO", referencedColumnName = "COD_PRODUCTO", insertable = false, updatable = false, nullable = false)
    private Producto producto;

    public FacturaDetalle() {

    }

    public FacturaDetalle(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(Long codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public BigDecimal getExistencia() {
        return existencia;
    }

    public void setExistencia(BigDecimal existencia) {
        this.existencia = existencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getUnitario() {
        return unitario;
    }

    public void setUnitario(BigDecimal unitario) {
        this.unitario = unitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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
        FacturaDetalle other = (FacturaDetalle) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FacturaDetalle [codigo=" + codigo + ", codigoFactura=" + codigoFactura + ", codigoProducto="
                + codigoProducto + ", existencia=" + existencia + ", nombre=" + nombre + ", unitario=" + unitario
                + ", subtotal=" + subtotal + ", version=" + version + ", factura=" + factura + ", producto=" + producto
                + "]";
    }

}
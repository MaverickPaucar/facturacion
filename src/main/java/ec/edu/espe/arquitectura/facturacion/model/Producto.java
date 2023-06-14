package ec.edu.espe.arquitectura.facturacion.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTO")
public class Producto {
    @Id
    @Column(name = "COD_PRODUCTO", length = 20, nullable = false)
    private String codigo;
    @Column(name = "NOMBRE", length = 100, nullable = false)
    private String nombre;
    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;
    @Column(name = "PRECIO", precision = 10, scale = 2, nullable = false)
    private BigDecimal precio;
    @Column(name = "EXISTENCIA", precision = 10, scale = 2, nullable = false)
    private BigDecimal existencia;
    @Column(name = "ESTADO", length = 3, nullable = false)
    private String estado;
    @Column(name = "IVA", length = 1, nullable = false)
    private String iva;
    @Column(name = "IVA", length = 1, nullable = false)
    private String ice;

    public Producto() {
    }

    public Producto(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getExistencia() {
        return existencia;
    }

    public void setExistencia(BigDecimal existencia) {
        this.existencia = existencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getIce() {
        return ice;
    }

    public void setIce(String ice) {
        this.ice = ice;
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
        Producto other = (Producto) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio="
                + precio + ", existencia=" + existencia + ", estado=" + estado + ", iva=" + iva + ", ice=" + ice + "]";
    }

}

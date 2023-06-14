package ec.edu.espe.arquitectura.facturacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "IMPUESTO")

public class Impuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COD_IMPUESTO", nullable = false, length = 3)
    private String codigo;
    @Column(name = "NOMBRE", length = 100, nullable = false)
    private String nombre;
    @Column(name = "SIGLAS", length = 10, nullable = false)
    private String siglas;

    public Impuesto() {
    }

    public Impuesto(String codigo) {
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

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    @Override
    public String toString() {
        return "Impuesto [codigo=" + codigo + ", nombre=" + nombre + ", siglas=" + siglas + "]";
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
        Impuesto other = (Impuesto) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

}

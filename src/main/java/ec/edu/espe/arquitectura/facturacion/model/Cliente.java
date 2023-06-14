package ec.edu.espe.arquitectura.facturacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COD_CLIENTE", nullable = false)
    private Integer codigo;
    @Column(name = "TIPO_IDENTIFICACION", length = 3, nullable = false)
    private String tipoIdentificacion;
    @Column(name = "IDENTIFICACION", length = 20, nullable = false)
    private String identificacion;
    @Column(name = "APELLIDO", length = 50)
    private String apellido;
    @Column(name = "NOMBRE", length = 50)
    private String nombre;
    @Column(name = "RAZON_SOCIAL", length = 100)
    private String razonSocial;
    @Column(name = "DIRECCION", length = 100, nullable = false)
    private String direccion;
    @Column(name = "TELEFONO", length = 15)
    private String telefono;
    @Column(name = "CORREO_ELECTRONICO", length = 100, nullable = false)
    private String email;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;

    public Cliente() {
    }

    public Cliente(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
        Cliente other = (Cliente) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cliente [codigo=" + codigo + ", tipoIdentificacion=" + tipoIdentificacion + ", identificacion="
                + identificacion + ", apellido="
                + apellido + ", nombre=" + nombre + ", razonSocial=" + razonSocial + ", direccion=" + direccion
                + ", telefono=" + telefono + ", email=" + email + ", version=" + version + "]";
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

}

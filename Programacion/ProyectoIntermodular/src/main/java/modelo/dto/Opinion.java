package modelo.dto;

import java.time.LocalDateTime;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Opinion {
    
    private final int codigo;   //  Clave primaria
    private final String mensaje;
    private final LocalDateTime fechaPublicacion;
    private final Usuario cliente;
    private final Producto producto;

    public Opinion(int codigo, String mensaje, LocalDateTime fechaPublicacion, Usuario cliente, Producto producto) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.fechaPublicacion = fechaPublicacion;
        this.cliente = cliente;
        this.producto = producto;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public Usuario getCliente() {
        return cliente;
    }
    
    public Producto getProducto() {
        return producto;
    }

    @Override
    public String toString() {
        return "Opinion{" + "codigo=" + codigo + ", mensaje=" + mensaje + ", fechaPublicacion=" + fechaPublicacion + ", cliente=" + cliente + ", producto=" + producto + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Opinion other = (Opinion) obj;
        return this.codigo == other.codigo;
    }
}

package dto;

import java.time.LocalDate;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Opinion {
    
    private final int codigo;   //  Clave primaria
    private final String mensaje;
    private final LocalDate fechaPublicacion;
    private final Usuario cliente;

    public Opinion(int codigo, String mensaje, LocalDate fechaPublicacion, Usuario cliente) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.fechaPublicacion = fechaPublicacion;
        this.cliente = cliente;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public Usuario getCliente() {
        return cliente;
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

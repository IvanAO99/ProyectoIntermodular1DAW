package modelo.dto;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Ticket {

    private final int codigo;   //  Clave primaria
    private final String mensaje;
    private final String asunto;
    private final EstadoTicket estado;
    private final Usuario cliente;

    public Ticket(int codigo, String mensaje, String asunto, EstadoTicket estado, Usuario cliente) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.asunto = asunto;
        this.estado = estado;
        this.cliente = cliente;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public Usuario getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Ticket{" + "codigo=" + codigo + ", mensaje=" + mensaje + ", asunto=" + asunto + ", estado=" + estado + ", cliente=" + cliente + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.codigo;
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
        final Ticket other = (Ticket) obj;
        return this.codigo == other.codigo;
    }
}

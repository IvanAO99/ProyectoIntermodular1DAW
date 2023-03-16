package dto;

import java.time.LocalDate;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Factura {
    
    private final int codigo;   //  Clave primaria
    private final LocalDate fechaFactura;
    private final Usuario cliente;
    private final Direccion direccion;
    private final Pedido pedido;

    public Factura(int codigo, LocalDate fechaFactura, Usuario cliente, Direccion direccion, Pedido pedido) {
        this.codigo = codigo;
        this.fechaFactura = fechaFactura;
        this.cliente = cliente;
        this.direccion = direccion;
        this.pedido = pedido;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDate getFechaFactura() {
        return fechaFactura;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public Pedido getPedido() {
        return pedido;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.codigo;
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
        final Factura other = (Factura) obj;
        return this.codigo == other.codigo;
    }
}

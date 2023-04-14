package dto;

import java.time.LocalDateTime;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Factura {
    
    private final int codigo;   //  Clave primaria
    private final LocalDateTime fechaFactura;
    private final Usuario cliente;
    private final Direccion direccion;
    private final Pedido pedido;

    public Factura(int codigo, LocalDateTime fechaFactura, Usuario cliente, Direccion direccion, Pedido pedido) {
        this.codigo = codigo;
        this.fechaFactura = fechaFactura;
        this.cliente = cliente;
        this.direccion = direccion;
        this.pedido = pedido;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDateTime getFechaFactura() {
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
    public String toString() {
        return "Factura{" + "codigo=" + codigo + ", fechaFactura=" + fechaFactura + ", cliente=" + cliente + ", direccion=" + direccion + ", pedido=" + pedido + '}';
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

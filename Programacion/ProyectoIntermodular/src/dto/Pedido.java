package dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Pedido {
    
    private final int codigo;   //  Clave primaria
    private final LocalDate fechaPedido;
    private final double precioTotal;
    private final EstadoFacturado facturado;
    private final Usuario cliente;
    private final Direccion direccion;
    private final HashMap<Producto, Entry<Integer,Double>> lineasPedido;

    public Pedido(int codigo, LocalDate fechaPedido, double precioTotal, EstadoFacturado facturado, Usuario cliente, Direccion direccion, HashMap<Producto, Entry<Integer, Double>> lineasPedido) {
        this.codigo = codigo;
        this.fechaPedido = fechaPedido;
        this.precioTotal = precioTotal;
        this.facturado = facturado;
        this.cliente = cliente;
        this.direccion = direccion;
        this.lineasPedido = lineasPedido;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public EstadoFacturado getFacturado() {
        return facturado;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public HashMap<Producto, Entry<Integer, Double>> getLineasPedido() {
        return lineasPedido;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.codigo;
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
        final Pedido other = (Pedido) obj;
        return this.codigo == other.codigo;
    }
}

package modelo.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 *
 */
public class Pedido {

    private final int codigo;   //  Clave primaria
    private final LocalDateTime fechaPedido;
    private final double precioTotal;
    private final boolean facturado;
    private final Usuario cliente;
    private final Direccion direccion;
    private final HashMap<Producto, Entry<Integer, Double>> lineasPedido;

    public Pedido(int codigo, LocalDateTime fechaPedido, double precioTotal, boolean facturado, Usuario cliente, Direccion direccion, HashMap<Producto, Entry<Integer, Double>> lineasPedido) {
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

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public boolean isFacturado() {
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

    public String getCodigosProductos() {
        Set<Integer> codigos = new TreeSet<>();

        for (Producto producto : this.lineasPedido.keySet()) {
            codigos.add(producto.getCodigo());
        }
        return codigos.toString().replace("[", "").replace("]", "");
    }

    public boolean hayStock() {
//        HashMap<Producto, Entry<Integer, Double>> lineasPedido = this.getLineasPedido();
//        for (Producto p : lineasPedido.keySet()) {
//            int cantidadPedido = lineasPedido.get(p).getKey();
//            int stockDisponible = p.getStock();
//            if (cantidadPedido > stockDisponible) {
//                return false;
//            }
//        }

        for (Producto p : lineasPedido.keySet()) {
            int cantidadPedido = lineasPedido.get(p).getKey();
            int stockDisponible = p.getStock();
            if (cantidadPedido > stockDisponible) {
                System.out.println("No hay stock");
                return false;
            }
        }
        System.out.println("Hay stock");
        return true;
    }

    public int cantidadStock(Producto p) {
        HashMap<Producto, Entry<Integer, Double>> lineasPedido = this.getLineasPedido();
        for (Producto pr : lineasPedido.keySet()) {
            int cantidadPedido = lineasPedido.get(pr).getKey();
            int stockDisponible = pr.getStock();
            return stockDisponible - cantidadPedido;
        }
        return 0;
    }

    public static double calcularPrecioTotal(HashMap<Producto, Entry<Integer, Double>> lineas) {
        double precio = 0;

        for (Entry<Producto, Entry<Integer, Double>> linea : lineas.entrySet()) {
            precio += (linea.getValue().getKey() * linea.getValue().getValue());
        }

        return precio;
    }

    @Override
    public String toString() {
        return "Pedido{" + "codigo=" + codigo + ", fechaPedido=" + fechaPedido + ", precioTotal=" + precioTotal + ", facturado=" + facturado + ", cliente=" + cliente + ", direccion=" + direccion + ", lineasPedido=" + lineasPedido + '}';
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

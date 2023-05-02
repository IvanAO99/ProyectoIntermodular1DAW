package modelo.dto;

import java.util.ArrayList;
import java.util.Objects;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class ListaDeseos {
    
    //  Clave primaria triple (nombre + cliente + productos)

    private final String nombre;
    private final Usuario cliente;
    private final ArrayList<Producto> productos;

    public ListaDeseos(String nombre, Usuario cliente, ArrayList<Producto> productos) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.productos = productos;
    }

    public String getNombre() {
        return nombre;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    @Override
    public String toString() {
        return "ListaDeseos{" + "nombre=" + nombre + ", cliente=" + cliente + ", productos=" + productos + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.cliente);
        hash = 59 * hash + Objects.hashCode(this.productos);
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
        final ListaDeseos other = (ListaDeseos) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return Objects.equals(this.productos, other.productos);
    }
}

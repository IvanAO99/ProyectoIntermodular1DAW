package modelo.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
*
 */
public class Producto {

    private final int codigo;   //  Clave primaria
    private final int iva;
    private final int stock;
    private final int stockMinimo;
    private final String unidadDeMedida;
    private final double precio;
    private final String nombre;
    private final String descripcion;
    private final String foto;
    private final LocalDateTime fechaCreacion;  //  Opcional
    private final LocalDateTime fechaUltimaModificacion;    //  Opcional
    private final Proveedor proveedor;
    private final Usuario creador;
    private final Usuario modificador;  //  Opcional
    private final ArrayList<Categoria> categorias;

    public Producto(int codigo, int iva, int stock, int stockMinimo, String unidadDeMedida, double precio, String nombre, String descripcion, String foto, LocalDateTime fechaCreacion, LocalDateTime fechaUltimaModificacion, Proveedor proveedor, Usuario creador, Usuario modificador, ArrayList<Categoria> categorias) {
        this.codigo = codigo;
        this.iva = iva;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.unidadDeMedida = unidadDeMedida;
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.fechaCreacion = fechaCreacion;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        this.proveedor = proveedor;
        this.creador = creador;
        this.modificador = modificador;
        this.categorias = categorias;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getIva() {
        return iva;
    }

    public int getStock() {
        return stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public Usuario getCreador() {
        return creador;
    }

    public Usuario getModificador() {
        return modificador;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public double getPrecioCompleto() {
        return this.precio + ((this.iva / 100) * this.precio);
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", iva=" + iva + ", stock=" + stock + ", stockMinimo=" + stockMinimo + ", unidadDeMedida=" + unidadDeMedida + ", precio=" + precio + ", nombre=" + nombre + ", descripcion=" + descripcion + ", foto=" + foto + ", fechaCreacion=" + fechaCreacion + ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", proveedor=" + proveedor + ", creador=" + creador + ", modificador=" + modificador + ", categorias=" + categorias + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.codigo;
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
        final Producto other = (Producto) obj;
        return this.codigo == other.codigo;
    }
}

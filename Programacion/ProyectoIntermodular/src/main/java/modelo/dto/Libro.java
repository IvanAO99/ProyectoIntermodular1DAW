package modelo.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Libro extends Producto {

    private final String formato;
    private final String editorial;
    private final String autor;
    private final long isbn;
    private final int nPaginas;

    public Libro(String formato, String editorial, String autor, long isbn, int nPaginas, int codigo, int iva, int stock, int stockMinimo, String unidadDeMedida, double precio, String nombre, String descripcion, String foto, LocalDateTime fechaCreacion, LocalDateTime fechaUltimaModificacion, Proveedor proveedor, Usuario creador, Usuario modificador, ArrayList<Categoria> categorias) {
        super(codigo, iva, stock, stockMinimo, unidadDeMedida, precio, nombre, descripcion, foto, fechaCreacion, fechaUltimaModificacion, proveedor, creador, modificador, categorias);
        this.formato = formato;
        this.editorial = editorial;
        this.autor = autor;
        this.isbn = isbn;
        this.nPaginas = nPaginas;
    }
    
    public Libro(Producto p, String formato, String editorial, String autor, long isbn, int nPaginas) {
        this(formato, editorial, autor, isbn, nPaginas, p.getCodigo(), p.getIva(), p.getStock(), p.getStockMinimo(), p.getUnidadDeMedida(), p.getPrecio(), p.getNombre(), p.getDescripcion(), p.getFoto(), p.getFechaCreacion(), p.getFechaUltimaModificacion(), p.getProveedor(), p.getCreador(), p.getModificador(), p.getCategorias());
    }

    public String getFormato() {
        return formato;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getAutor() {
        return autor;
    }

    public long getIsbn() {
        return isbn;
    }

    public int getnPaginas() {
        return nPaginas;
    }

    @Override
    public String toString() {
        return super.toString() + "Libro{" + "formato=" + formato + ", editorial=" + editorial + ", autor=" + autor + ", isbn=" + isbn + ", nPaginas=" + nPaginas + '}';
    }
}

package dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Libro extends Producto {

    private final String formato;
    private final String editorial;
    private final String autor;
    private final String genero;
    private final int isbn;
    private final int nPaginas;

    public Libro(String formato, String editorial, String autor, String genero, int isbn, int nPaginas, int codigo, int iva, int stock, int stockMinimo, String unidadDeMedida, double precio, String nombre, String descripcion, String foto, LocalDateTime fechaCreacion, LocalDateTime fechaUltimaModificacion, Proveedor proveedor, Usuario creador, Usuario modificador, ArrayList<Categoria> categorias) {
        super(codigo, iva, stock, stockMinimo, unidadDeMedida, precio, nombre, descripcion, foto, fechaCreacion, fechaUltimaModificacion, proveedor, creador, modificador, categorias);
        this.formato = formato;
        this.editorial = editorial;
        this.autor = autor;
        this.genero = genero;
        this.isbn = isbn;
        this.nPaginas = nPaginas;
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

    public String getGenero() {
        return genero;
    }

    public int getIsbn() {
        return isbn;
    }

    public int getnPaginas() {
        return nPaginas;
    }
}

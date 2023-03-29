package dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Disco extends Producto {

    private final String canciones;
    private final String sello;
    private final String genero;
    private final String artista;
    private final String tipo;
    private final int asin;

    public Disco(String canciones, String sello, String genero, String artista, String tipo, int asin, int codigo, int iva, int stock, int stockMinimo, String unidadDeMedida, double precio, String nombre, String descripcion, String foto, LocalDateTime fechaCreacion, LocalDateTime fechaUltimaModificacion, Proveedor proveedor, Usuario creador, Usuario modificador, ArrayList<Categoria> categorias) {
        super(codigo, iva, stock, stockMinimo, unidadDeMedida, precio, nombre, descripcion, foto, fechaCreacion, fechaUltimaModificacion, proveedor, creador, modificador, categorias);
        this.canciones = canciones;
        this.sello = sello;
        this.genero = genero;
        this.artista = artista;
        this.tipo = tipo;
        this.asin = asin;
    }

    public String getCanciones() {
        return canciones;
    }

    public String getSello() {
        return sello;
    }

    public String getGenero() {
        return genero;
    }

    public String getArtista() {
        return artista;
    }

    public String getTipo() {
        return tipo;
    }

    public int getAsin() {
        return asin;
    }
}

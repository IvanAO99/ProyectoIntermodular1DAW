package modelo.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Disco extends Producto {

    private final String canciones;
    private final String sello;
    private final String artista;
    private final String tipo;
    private final long asin;

    public Disco(String canciones, String sello, String artista, String tipo, long asin, int codigo, int iva, int stock, int stockMinimo, String unidadDeMedida, double precio, String nombre, String descripcion, String foto, LocalDateTime fechaCreacion, LocalDateTime fechaUltimaModificacion, Proveedor proveedor, Usuario creador, Usuario modificador, ArrayList<Categoria> categorias) {
        super(codigo, iva, stock, stockMinimo, unidadDeMedida, precio, nombre, descripcion, foto, fechaCreacion, fechaUltimaModificacion, proveedor, creador, modificador, categorias);
        this.canciones = canciones;
        this.sello = sello;
        this.artista = artista;
        this.tipo = tipo;
        this.asin = asin;
    }
    
    public Disco(Producto p, String canciones, String sello, String artista, String tipo, long asin) {
        this(canciones, sello, artista, tipo, asin, p.getCodigo(), p.getIva(), p.getStock(), p.getStockMinimo(), p.getUnidadDeMedida(), p.getPrecio(), p.getNombre(), p.getDescripcion(), p.getFoto(), p.getFechaCreacion(), p.getFechaUltimaModificacion(), p.getProveedor(), p.getCreador(), p.getModificador(), p.getCategorias());
    }

    public String getCanciones() {
        return canciones;
    }

    public String getSello() {
        return sello;
    }

    public String getArtista() {
        return artista;
    }

    public String getTipo() {
        return tipo;
    }

    public long getAsin() {
        return asin;
    }
}

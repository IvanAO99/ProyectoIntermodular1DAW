package modelo.dto;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public enum TipoProveedor {
    
    LIBROS, DISCOS;
    
    @Override    
    public String toString() {
        switch (this) {
            case LIBROS:
                return "Libros";
            case DISCOS:
                return "Discos";
        }
        
        return null;
    }
}

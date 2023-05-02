package modelo.dto;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Categoria {
    
    private final int codigo;   //  Clave primaria
    private final String nombre;

    public Categoria(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Categoria {" + "codigo = " + codigo + ", nombre = " + nombre + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.codigo;
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
        final Categoria other = (Categoria) obj;
        return this.codigo == other.codigo;
    }
}

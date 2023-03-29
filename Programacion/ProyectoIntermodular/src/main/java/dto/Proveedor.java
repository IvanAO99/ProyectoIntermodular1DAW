package dto;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Proveedor {

    private final int codigo;   //  Clave primaria
    private final String nombre;
    private final int telefono;
    private final String direccion;
    private final TipoProveedor tipo;

    public Proveedor(int codigo, String nombre, int telefono, String direccion, TipoProveedor tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public TipoProveedor getTipo() {
        return tipo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.codigo;
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
        final Proveedor other = (Proveedor) obj;
        return this.codigo == other.codigo;
    }
}

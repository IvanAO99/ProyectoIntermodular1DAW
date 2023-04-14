package dto;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Direccion {
    
    private final int codigo;   //  Clave primaria
    private final String tipo;
    private final int cp;
    private final String localidad;
    private final String provincia;
    private final String direccionCompleta;
    private final Usuario cliente;

    public Direccion(int codigo, String tipo, int cp, String localidad, String provincia, String direccionCompleta, Usuario cliente) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.cp = cp;
        this.localidad = localidad;
        this.provincia = provincia;
        this.direccionCompleta = direccionCompleta;
        this.cliente = cliente;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCp() {
        return cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public Usuario getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Direccion{" + "codigo=" + codigo + ", tipo=" + tipo + ", cp=" + cp + ", localidad=" + localidad + ", provincia=" + provincia + ", direccionCompleta=" + direccionCompleta + ", cliente=" + cliente + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.codigo;
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
        final Direccion other = (Direccion) obj;
        return this.codigo == other.codigo;
    }
}

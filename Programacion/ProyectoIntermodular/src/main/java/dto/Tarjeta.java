package dto;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Tarjeta {

    private final int numero;   //  Clave primaria

    public Tarjeta(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.numero;
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
        final Tarjeta other = (Tarjeta) obj;
        return this.numero == other.numero;
    }
}

package modelo.dto;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public enum EstadoTicket {
    
    PENDIENTE, RESUELTO;
    
    @Override    
    public String toString() {
        switch (this) {
            case PENDIENTE:
                return "Pendiente";
            case RESUELTO:
                return "Resuelto";
        }
        return null;
    }
}

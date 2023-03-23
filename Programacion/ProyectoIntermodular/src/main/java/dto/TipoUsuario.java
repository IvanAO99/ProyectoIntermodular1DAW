package dto;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public enum TipoUsuario {
    
    ADMINISTRADOR, CLIENTE;
    
    @Override    
    public String toString() {
        switch (this) {
            case ADMINISTRADOR:
                return "Administrador";
            case CLIENTE:
                return "Cliente";
        }
        return null;
    }
}

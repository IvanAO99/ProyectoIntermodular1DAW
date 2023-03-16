package dto;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public enum EstadoFacturado {
    
    SI, NO;
    
    @Override    
    public String toString() {
        switch (this) {
            case SI:
                return "Si";
            case NO:
                return "No";
        }
        
        return null;
    }
}

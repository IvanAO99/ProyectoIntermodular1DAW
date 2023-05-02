package modelo.dao;

import modelo.dto.Disco;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class TestDAO {
    public static void main(String[] args) {
        DiscoDAO dao = new DiscoDAO();
        
        ArrayList<Disco> array;
        
        
        try {
            array = dao.getAll();
            
            for (Disco x : array) {
                System.out.println(x);
            }
            
            //System.out.println(dao.getByCodigo(2));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
    }
}

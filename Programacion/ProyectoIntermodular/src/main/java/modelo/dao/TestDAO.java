package modelo.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelo.dto.Direccion;
import modelo.dto.Pedido;
import modelo.dto.Usuario;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class TestDAO {

    public static void main(String[] args) throws SQLException {
        PedidoDAO dao = new PedidoDAO();
        Usuario u = new UsuarioDAO().getByCodigo(1);
        ArrayList<Direccion> direcciones = new DireccionDAO().getDireccionesDe(u);
        
       
//        try {
//            dao.anyadir(new Pedido(dao.siguienteCodigo(), LocalDateTime.now(), 39.4, false, u, direcciones.get(0), ));
//
//            //System.out.println(dao.getByCodigo(2));
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }

    }
}

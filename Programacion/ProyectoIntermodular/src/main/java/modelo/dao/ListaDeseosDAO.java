package modelo.dao;

import modelo.dto.ListaDeseos;
import modelo.dto.Producto;
import modelo.dto.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class ListaDeseosDAO extends TablaDAO<ListaDeseos> {
    
    public ListaDeseosDAO() {
        this.tabla = "ARTESDORADAS_listas_deseos";
    }

    @Override
    public int actualizar(ListaDeseos ld) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int anyadir(ListaDeseos ld) throws SQLException {
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, ld.getCliente().getCodigo());
        prepared.setString(2, ld.getNombre());
        return prepared.executeUpdate();
    }

    @Override
    public ListaDeseos eliminar(ListaDeseos objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existe(ListaDeseos objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ListaDeseos> getAll() throws SQLException {
        ArrayList<ListaDeseos> listasDeseos = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY cliente";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            Usuario cliente = new UsuarioDAO().getByCodigo(resultSet.getInt("cliente"));
            String nombre = resultSet.getString("nombre");
            ArrayList<Producto> productos = getProductos(cliente.getCodigo(), nombre);
            listasDeseos.add(new ListaDeseos(nombre, cliente, productos));
        }

        return listasDeseos;
    }

    @Override
    public ListaDeseos getByCodigo(int codigo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public ArrayList<Producto> getProductos(int codCliente, String nombreLista) throws SQLException {
        ProductoDAO productoDAO = new ProductoDAO();
        ArrayList<Producto> productos = new ArrayList<>();
        
        String sentenciaSQL = "SELECT * FROM ARTESDORADAS_listas_deseos_productos WHERE cliente=? AND nombre_lista=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codCliente);
        prepared.setString(2, nombreLista);
        ResultSet resultSet = prepared.executeQuery();
        
        while (resultSet.next()) {
            Producto producto = productoDAO.getByCodigo(resultSet.getInt("producto"));
            
            productos.add(producto);
        }

        return productos;
    }
    
    public void anyadirProductos(ListaDeseos ld) throws SQLException {
        for (Producto p : ld.getProductos()) {
            String sentenciaSQL = "INSERT INTO ARTESDORADAS_listas_deseos_productos VALUES(?,?,?)";
            PreparedStatement prepared = getPrepared(sentenciaSQL);
            prepared.setInt(1, ld.getCliente().getCodigo());
            prepared.setString(2, ld.getNombre());
            prepared.setInt(3, p.getCodigo());

            prepared.executeUpdate();
        }
    }
}

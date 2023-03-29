package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.*;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class ProveedorDAO extends TablaDAO<Proveedor> {

    public ProveedorDAO() {
        this.tabla = "ARTESDORADAS_proveedores";
    }

    @Override
    public int actualizar(Proveedor pr) {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int anyadir(Proveedor pr) throws SQLException {
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, pr.getCodigo());
        prepared.setInt(2, pr.getTelefono());
        prepared.setString(3, pr.getDireccion());
        prepared.setString(4, pr.getNombre());
        prepared.setString(5, pr.getTipo().toString());
        return prepared.executeUpdate();

    }

    @Override
    public Proveedor eliminar(Proveedor pr) throws SQLException {
        if (pr == null) {
            return null;
        } else {
            return eliminar(pr.getCodigo()) != null ? pr : null;
        }
    }

    @Override
    public boolean existe(Proveedor pr) throws SQLException {
        return existe(pr.getCodigo());
    }


    @Override
    public ArrayList<Proveedor> getAll() throws SQLException {
        ArrayList<Proveedor> lista = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY codigo";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");
            String nombre = resultSet.getString("nombre");
            String direccion = resultSet.getString("direccion");
            int telefono = resultSet.getInt("telefono");
            TipoProveedor tipo = TipoProveedor.valueOf(resultSet.getString("tipo"));
            lista.add(new Proveedor(codigo, nombre, telefono, direccion, tipo));
        }

        return lista;
    }
    @Override
    public Proveedor getByCodigo(int codigo) throws SQLException {
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE codigo=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codigo);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            String nombre = resultSet.getString("nombre");
            String direccion = resultSet.getString("direccion");
            int telefono = resultSet.getInt("telefono");
            TipoProveedor tipo = TipoProveedor.valueOf(resultSet.getString("tipo"));
            return new Proveedor(codigo, nombre, telefono, direccion, tipo);
        }
        
        return null;
    }

}

package modelo.dao;

import modelo.dto.Proveedor;
import modelo.dto.TipoProveedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Proveedor eliminar(Proveedor pr) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
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
            TipoProveedor tipo = TipoProveedor.valueOf(resultSet.getString("tipo").toUpperCase());
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
            TipoProveedor tipo = TipoProveedor.valueOf(resultSet.getString("tipo").toUpperCase());
            return new Proveedor(codigo, nombre, telefono, direccion, tipo);
        }
        
        return null;
    }

}

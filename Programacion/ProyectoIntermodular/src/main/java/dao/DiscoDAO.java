
package dao;

import dto.Disco;
import dto.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class DiscoDAO extends TablaDAO<Disco> {

    public DiscoDAO() {
        this.tabla = "ARTESDORADAS_discos";
    }

    @Override
    public int actualizar(Disco d) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int anyadir(Disco d) throws SQLException {
        ProductoDAO producto = new ProductoDAO();
        producto.anyadir(d);
        
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, d.getCodigo());
        prepared.setString(2, d.getCanciones());
        prepared.setString(3, d.getSello());
        prepared.setString(4, d.getArtista());
        prepared.setString(5, d.getTipo());
        prepared.setLong(6, d.getAsin());
        return prepared.executeUpdate();
    }

    @Override
    public Disco eliminar(Disco d) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existe(Disco d) throws SQLException {
        return existe(d.getCodigo());
    }

    @Override
    public ArrayList<Disco> getAll() throws SQLException {
        ArrayList<Disco> lista = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY codigo";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            Producto producto = new ProductoDAO().getByCodigo(resultSet.getInt("codigo"));
            String canciones = resultSet.getString("canciones");
            String sello = resultSet.getString("sello");
            long asin = resultSet.getLong("asin");
            String artista = resultSet.getString("artista");
            String tipo = resultSet.getString("tipo");

            lista.add(new Disco(producto, canciones, sello, artista, tipo, asin));
        }

        return lista;
    }

    @Override
    public Disco getByCodigo(int codigo) throws SQLException {
        Producto producto = new ProductoDAO().getByCodigo(codigo);
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE codigo=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codigo);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            String canciones = resultSet.getString("canciones");
            String sello = resultSet.getString("sello");
            long asin = resultSet.getLong("asin");
            String artista = resultSet.getString("artista");
            String tipo = resultSet.getString("tipo");

            return new Disco(producto, canciones, sello, artista, tipo, asin);
        }

        return null;
    }
}

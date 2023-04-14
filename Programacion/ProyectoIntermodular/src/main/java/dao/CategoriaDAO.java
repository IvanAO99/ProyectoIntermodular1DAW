package dao;

import dto.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.OptionalInt;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class CategoriaDAO extends TablaDAO<Categoria> {

    public CategoriaDAO() {
        this.tabla = "ARTESDORADAS_categorias";
    }

    @Override
    public int actualizar(Categoria c) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int anyadir(Categoria c) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Categoria eliminar(Categoria c) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existe(Categoria c) throws SQLException {
        return existe(c.getCodigo());
    }

    public boolean existe(String nombre) throws SQLException {
        return this.getCodigoDe(nombre).isPresent();
    }

    @Override
    public ArrayList<Categoria> getAll() throws SQLException {
        ArrayList<Categoria> lista = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY codigo";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");
            String nombre = resultSet.getString("nombre");
            lista.add(new Categoria(codigo, nombre));
        }

        return lista;
    }

    @Override
    public Categoria getByCodigo(int codigo) throws SQLException {
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE codigo=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codigo);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            String nombre = resultSet.getString("nombre");
            return new Categoria(codigo, nombre);
        }

        return null;

    }

    public OptionalInt getCodigoDe(String nombre) throws SQLException {
        String sentenciaSQL = "SELECT codigo FROM " + tabla + " WHERE nombre=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setString(1, nombre);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            return OptionalInt.of(resultSet.getInt("codigo"));
        }

        return OptionalInt.empty();
    }

}

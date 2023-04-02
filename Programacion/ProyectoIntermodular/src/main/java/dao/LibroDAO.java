package dao;

import dto.Libro;
import dto.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class LibroDAO extends TablaDAO<Libro> {
    
    public LibroDAO() {
        this.tabla = "ARTESDORADAS_libros";
    }

    @Override
    public int actualizar(Libro l) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int anyadir(Libro l) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Libro eliminar(Libro l) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existe(Libro l) throws SQLException {
        return existe(l.getCodigo());
    }

    @Override
    public ArrayList<Libro> getAll() throws SQLException {
        ArrayList<Libro> libros = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY codigo";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            Producto producto = new ProductoDAO().getByCodigo(resultSet.getInt("codigo"));
            String formato = resultSet.getString("formato");
            String editorial = resultSet.getString("editorial");
            int nPaginas = resultSet.getInt("numero_paginas");
            int isbn = resultSet.getInt("isbn");
            String autor = resultSet.getString("autor");

            libros.add(new Libro(producto, formato, editorial, autor, isbn, nPaginas));
        }

        return libros;
    }

    @Override
    public Libro getByCodigo(int codigo) throws SQLException {
        Producto producto = new ProductoDAO().getByCodigo(codigo);
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE codigo=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codigo);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            String formato = resultSet.getString("formato");
            String editorial = resultSet.getString("editorial");
            int nPaginas = resultSet.getInt("numero_paginas");
            int isbn = resultSet.getInt("isbn");
            String autor = resultSet.getString("autor");

            return new Libro(producto, formato, editorial, autor, isbn, nPaginas);
        }

        return null;
    }
}

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
            System.out.println(producto);
            String formato = resultSet.getString("formato");
            System.out.println(formato);
            String editorial = resultSet.getString("editorial");
            System.out.println(editorial);
            int nPaginas = resultSet.getInt("numero_paginas");
            System.out.println(nPaginas);
            long isbn = resultSet.getLong("isbn");
            System.out.println(isbn);
            String autor = resultSet.getString("autor");
            System.out.println(autor);

            libros.add(new Libro(producto, formato, editorial, autor, isbn, nPaginas));
            System.out.println(libros);
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
            long isbn = resultSet.getLong("isbn");
            String autor = resultSet.getString("autor");

            return new Libro(producto, formato, editorial, autor, isbn, nPaginas);
        }

        return null;
    }
}

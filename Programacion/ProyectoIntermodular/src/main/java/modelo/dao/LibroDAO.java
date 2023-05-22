package modelo.dao;

import modelo.dto.Libro;
import modelo.dto.Producto;
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
        ProductoDAO producto = new ProductoDAO();
        producto.anyadir(l);

        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, l.getCodigo());
        prepared.setString(2, l.getFormato());
        prepared.setString(3, l.getEditorial());
        prepared.setString(4, l.getAutor());
        prepared.setLong(5, l.getIsbn());
        prepared.setInt(6, l.getnPaginas());
        return prepared.executeUpdate();
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

    public ArrayList<Libro> getByCategorias(String filtro) throws SQLException {
        ArrayList<Libro> libros = new ArrayList<>();

        String sentenciaSQL = "SELECT * FROM " + tabla + " pro"
                + " LEFT JOIN artesdoradas_categorias_productos catpro ON catpro.producto = pro.codigo"
                + " LEFT JOIN artesdoradas_categorias cat ON cat.codigo = catpro.categoria"
                + " WHERE " + filtro;

        System.out.println("Consutla: " + sentenciaSQL);
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        System.out.println(prepared);
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
}

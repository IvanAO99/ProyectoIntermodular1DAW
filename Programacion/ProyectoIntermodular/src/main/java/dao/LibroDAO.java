package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import dto.*;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */

/*

public class LibroDAO extends TablaDAO<Producto> {

    public LibroDAO() {
        this.tabla = "producto";
    }

    @Override
    public int actualizar(Producto p) throws SQLException {
        String sentenciaSQL = "UPDATE " + tabla + " SET nombre=?, descripcion=?, precio=?, um=?, iva=?, stock=?, stockmin=?, foto=?, fcreacion=?, usucrea=?, fmodif=?, usumodif=? WHERE codigo=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setString(1, p.getNombre());
        prepared.setString(2, p.getDescripcion());
        prepared.setDouble(3, p.getPrecio());
        prepared.setString(4, p.getUnidadDeMedida());
        prepared.setInt(5, p.getIva());
        prepared.setInt(6, p.getStock());
        prepared.setInt(7, p.getStockMinimo());
        if (p.getFoto() == null) {
            prepared.setNull(8, java.sql.Types.VARCHAR);
        } else {
            prepared.setString(8, p.getFoto());
        }
        LocalDateTime fechaCreacion = p.getFechaCreacion();
        if (fechaCreacion == null) {
            prepared.setNull(9, java.sql.Types.TIMESTAMP);
        } else {
            prepared.setTimestamp(9, Timestamp.valueOf(fechaCreacion));
        }
        Usuario usuCrea = p.getCreador();
        if (usuCrea == null) {
            prepared.setNull(10, java.sql.Types.INTEGER);
        } else {
            prepared.setInt(10, usuCrea.getCodigo());
        }
        LocalDateTime fechaModificacion = p.getFechaUltimaModificacion();
        if (fechaModificacion == null) {
            prepared.setNull(11, java.sql.Types.TIMESTAMP);
        } else {
            prepared.setTimestamp(11, Timestamp.valueOf(fechaModificacion));
        }
        Usuario usuModifica = p.getModificador();
        if (usuModifica == null) {
            prepared.setNull(12, java.sql.Types.INTEGER);
        } else {
            prepared.setInt(12, usuModifica.getCodigo());
        }
        prepared.setInt(13, p.getCodigo());
        int resultado = prepared.executeUpdate();
        if (resultado > 0) {
            eliminarCategorias(p);
            anyadirCategorias(p);
        }
        return resultado;
    }

    @Override
    public int anyadir(Producto p) throws SQLException {
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, p.getCodigo());
        prepared.setString(2, p.getNombre());
        prepared.setString(3, p.getDescripcion());
        prepared.setDouble(4, p.getPrecio());
        prepared.setString(5, p.getUnidadDeMedida());
        prepared.setInt(6, p.getIva());
        prepared.setInt(7, p.getStock());
        prepared.setInt(8, p.getStockMinimo());
        if (p.getFoto() == null) {
            prepared.setNull(9, java.sql.Types.VARCHAR);
        } else {
            prepared.setString(9, p.getFoto());
        }
        LocalDateTime fechaCreacion = p.getFechaCreacion();
        if (fechaCreacion == null) {
            prepared.setNull(10, java.sql.Types.TIMESTAMP);
        } else {
            prepared.setTimestamp(10, Timestamp.valueOf(fechaCreacion));
        }
        Usuario usuCrea = p.getCreador();
        if (usuCrea == null) {
            prepared.setNull(11, java.sql.Types.INTEGER);
        } else {
            prepared.setInt(11, usuCrea.getCodigo());
        }
        LocalDateTime fechaModificacion = p.getFechaUltimaModificacion();
        if (fechaModificacion == null) {
            prepared.setNull(12, java.sql.Types.TIMESTAMP);
        } else {
            prepared.setTimestamp(12, Timestamp.valueOf(fechaModificacion));
        }
        Usuario usuModifica = p.getModificador();
        if (usuModifica == null) {
            prepared.setNull(13, java.sql.Types.INTEGER);
        } else {
            prepared.setInt(13, usuModifica.getCodigo());
        }
        int resultado = prepared.executeUpdate();
        anyadirCategorias(p);
        return resultado;
    }

    private void anyadirCategorias(Producto p) throws SQLException {
        for (Categoria c : p.getCategoria()) {
            String sentenciaSQL = "INSERT INTO ARTESDORADAS_categorias_productos VALUES(?, ?)";
            PreparedStatement prepared = getPrepared(sentenciaSQL);
            prepared.setInt(1, p.getCodigo());
            prepared.setInt(2, c.getCodigo());
            prepared.executeUpdate();
        }
    }

    private void eliminarCategorias(Producto p) throws SQLException {
        String sentenciaSQL = "DELETE FROM ARTESDORADAS_categorias_productos WHERE producto=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, p.getCodigo());
        prepared.executeUpdate();

    }

    @Override
    public Producto eliminar(Producto p) throws SQLException {
        if (p == null) {
            return null;
        } else {
            return eliminar(p.getCodigo()) != null ? p : null;
        }
    }

    @Override
    public boolean existe(Producto p) throws SQLException {
        return existe(p.getCodigo());
    }

    @Override
    public ArrayList<Producto> getAll() throws SQLException {
        ArrayList<Producto> lista = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY codigo";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");
            String nombre = resultSet.getString("nombre");
            String descripcion = resultSet.getString("descripcion");
            double precio = resultSet.getDouble("precio");
            String unidadDeMedida = resultSet.getString("um");
            int iva = resultSet.getInt("iva");
            int stock = resultSet.getInt("stock");
            int stockMinimo = resultSet.getInt("stockmin");
            String foto = resultSet.getString("foto");
            Usuario usuarioCrea = new UsuarioDAO().getByCodigo(resultSet.getInt("usucrea"));
            LocalDateTime fechaCreacion = resultSet.getTimestamp("fcreacion").toLocalDateTime();
            Usuario usuarioModifica = new UsuarioDAO().getByCodigo(resultSet.getInt("usumodif"));
            Timestamp fechaModificacionTS = resultSet.getTimestamp("fmodif");
            LocalDateTime fechaModificacion = (fechaModificacionTS == null) ? null : fechaModificacionTS.toLocalDateTime();
            LinkedHashSet<Categoria> categorias = getCategoriasByCodProducto(codigo);

            lista.add(new Libro(codigo, nombre, descripcion, precio, unidadDeMedida, iva, stock, stockMinimo, foto, usuarioCrea, fechaCreacion, usuarioModifica, fechaModificacion, categorias));
        }

        return lista;
    }

    @Override
    public Libro getByCodigo(int codigo) throws SQLException {
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE codigo=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codigo);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            String nombre = resultSet.getString("nombre");
            String descripcion = resultSet.getString("descripcion");
            double precio = resultSet.getDouble("precio");
            String unidadDeMedida = resultSet.getString("um");
            int iva = resultSet.getInt("iva");
            int stock = resultSet.getInt("stock");
            int stockMinimo = resultSet.getInt("stockmin");
            String foto = resultSet.getString("foto");
            Usuario usuarioCrea = new UsuarioDAO().getByCodigo(resultSet.getInt("usucrea"));
            LocalDateTime fechaCreacion = resultSet.getTimestamp("fcreacion").toLocalDateTime();
            Usuario usuarioModifica = new UsuarioDAO().getByCodigo(resultSet.getInt("usumodif"));
            Timestamp fechaModificacionTS = resultSet.getTimestamp("fmodif");
            LocalDateTime fechaModificacion = (fechaModificacionTS == null) ? null : fechaModificacionTS.toLocalDateTime();
            LinkedHashSet<Categoria> categorias = getCategoriasByCodProducto(codigo);

            return new Libro(codigo, nombre, descripcion, precio, unidadDeMedida, iva, stock, stockMinimo, foto, usuarioCrea, fechaCreacion, usuarioModifica, fechaModificacion, categorias);
        }

        return null;
    }

    public LinkedHashSet<Categoria> getCategoriasByCodProducto(int codProducto) throws SQLException {
        LinkedHashSet<Categoria> categorias = new LinkedHashSet<>();
        String sentenciaSQL = "SELECT ca.codigo, ca.nombre FROM ARTESDORADAS_categorias_productos cp, ARTESDORADAS_categorias ca WHERE ca.codigo = cp.categoria AND cp.producto = ?  ORDER BY ca.codigo";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codProducto);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");
            String nombre = resultSet.getString("nombre");
            categorias.add(new Categoria(codigo, nombre));
        }
        return categorias;
    }

}

*/

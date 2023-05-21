package modelo.dao;

import modelo.dto.Categoria;
import modelo.dto.Producto;
import modelo.dto.Proveedor;
import modelo.dto.Usuario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class ProductoDAO extends TablaDAO<Producto> {

    public ProductoDAO() {
        this.tabla = "ARTESDORADAS_productos";
    }

    @Override
    public int actualizar(Producto p) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int anyadir(Producto p) throws SQLException {
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, p.getCodigo());
        prepared.setString(2, p.getFoto());
        prepared.setString(3, p.getNombre());
        prepared.setString(4, p.getDescripcion());
        prepared.setDouble(5, p.getPrecio());
        prepared.setString(6, p.getUnidadDeMedida());
        prepared.setInt(7, p.getStock());
        prepared.setInt(8, p.getStockMinimo());
        prepared.setInt(9, p.getIva());
        prepared.setInt(10, p.getProveedor().getCodigo());
        prepared.setInt(11, p.getCreador().getCodigo());
        Usuario modificador = p.getModificador();
        if (modificador == null) {
            prepared.setNull(12, java.sql.Types.INTEGER);
        } else {
            prepared.setInt(12, p.getModificador().getCodigo());
        }
        prepared.setTimestamp(13, Timestamp.valueOf(p.getFechaCreacion()));
        LocalDateTime fechaModificacion = p.getFechaUltimaModificacion();
        if (fechaModificacion == null) {
            prepared.setNull(14, java.sql.Types.TIMESTAMP);
        } else {
            prepared.setTimestamp(14, Timestamp.valueOf(fechaModificacion));
        }

        return prepared.executeUpdate();
    }

    public void anyadirCategorias(Producto p) throws SQLException {
        for (Categoria c : p.getCategorias()) {
            String sentenciaSQL = "INSERT INTO ARTESDORADAS_categorias_productos VALUES(?, ?)";
            PreparedStatement prepared = getPrepared(sentenciaSQL);
            prepared.setInt(1, p.getCodigo());
            prepared.setInt(2, c.getCodigo());

            prepared.executeUpdate();
        }
    }

    public void eliminarCategorias(Producto p) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public Producto eliminar(Producto p) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
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
            String foto = resultSet.getString("foto");
            String nombre = resultSet.getString("nombre");
            String descripcion = resultSet.getString("descripcion");
            double precio = resultSet.getDouble("precio");
            String unidadDeMedida = resultSet.getString("unidad_medida");
            int stock = resultSet.getInt("stock");
            LocalDateTime fechaCreacion = resultSet.getTimestamp("fecha_creacion").toLocalDateTime();
            int stockMinimo = resultSet.getInt("stock_minimo");
            int iva = resultSet.getInt("iva");
            Proveedor proveedor = new ProveedorDAO().getByCodigo(resultSet.getInt("proveedor"));
            Usuario creador = new UsuarioDAO().getByCodigo(resultSet.getInt("creador"));
            int modificadorCod = resultSet.getInt("modificador");
            Usuario modificador = (modificadorCod == 0) ? null : new UsuarioDAO().getByCodigo(modificadorCod);
            Timestamp fechaUltimaModificacionTS = resultSet.getTimestamp("fecha_ultima_modificacion");
            LocalDateTime fechaUltimaModificacion = (fechaUltimaModificacionTS == null) ? null : fechaUltimaModificacionTS.toLocalDateTime();
            ArrayList<Categoria> categorias = getCategoriasByCodProducto(codigo);

            lista.add(new Producto(codigo, iva, stock, stockMinimo, unidadDeMedida, precio, nombre, descripcion, foto, fechaCreacion, fechaUltimaModificacion, proveedor, creador, modificador, categorias));
        }

        return lista;
    }

    @Override
    public Producto getByCodigo(int codigo) throws SQLException {
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE codigo=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codigo);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            String foto = resultSet.getString("foto");
            String nombre = resultSet.getString("nombre");
            String descripcion = resultSet.getString("descripcion");
            double precio = resultSet.getDouble("precio");
            String unidadDeMedida = resultSet.getString("unidad_medida");
            int stock = resultSet.getInt("stock");
            LocalDateTime fechaCreacion = resultSet.getTimestamp("fecha_creacion").toLocalDateTime();
            int stockMinimo = resultSet.getInt("stock_minimo");
            int iva = resultSet.getInt("iva");
            Proveedor proveedor = new ProveedorDAO().getByCodigo(resultSet.getInt("proveedor"));
            Usuario creador = new UsuarioDAO().getByCodigo(resultSet.getInt("creador"));
            int modificadorCod = resultSet.getInt("modificador");
            Usuario modificador = (modificadorCod == 0) ? null : new UsuarioDAO().getByCodigo(modificadorCod);
            Timestamp fechaUltimaModificacionTS = resultSet.getTimestamp("fecha_ultima_modificacion");
            LocalDateTime fechaUltimaModificacion = (fechaUltimaModificacionTS == null) ? null : fechaUltimaModificacionTS.toLocalDateTime();
            ArrayList<Categoria> categorias = getCategoriasByCodProducto(codigo);

            return new Producto(codigo, iva, stock, stockMinimo, unidadDeMedida, precio, nombre, descripcion, foto, fechaCreacion, fechaUltimaModificacion, proveedor, creador, modificador, categorias);
        }

        return null;
    }

    public ArrayList<Categoria> getCategoriasByCodProducto(int codProducto) throws SQLException {
        ArrayList<Categoria> categorias = new ArrayList<>();
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

    public int descontarStock(Producto p, int cantidad) throws SQLException {
        //String sentenciaSQL = "UPDATE " + tabla + " SET nombre=? WHERE codigo=?";
        String sentenciaSQL = "UPDATE " + this.tabla
                + " SET stock = stock - " + cantidad
                + "WHERE codigo = " + p.getCodigo();

        PreparedStatement prepared = getPrepared(sentenciaSQL);

        return prepared.executeUpdate();
    }

}

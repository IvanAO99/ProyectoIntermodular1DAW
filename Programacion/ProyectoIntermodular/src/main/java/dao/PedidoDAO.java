package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import dto.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class PedidoDAO extends TablaDAO<Pedido> {

    public PedidoDAO() {
        this.tabla = "pedido";
    }

    @Override
    public int actualizar(Pedido p) throws SQLException {
        //No necesario para el proyecto
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int anyadir(Pedido p) throws SQLException {
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, p.getCodigo());
        prepared.setTimestamp(2, Timestamp.valueOf(p.getFechaPedido()));
        prepared.setInt(3, p.getCliente().getCodigo());
        Direccion direccion = p.getDireccion();
        if (direccion == null) {
            prepared.setNull(4, java.sql.Types.INTEGER);
        } else {
            prepared.setInt(4, direccion.getCodigo());
        }
        //prepared.setString(5, p.getMetodoPago().toString());
        int resultado = prepared.executeUpdate();
        anyadirLineas(p);
        return resultado;

    }

    @Override
    public Pedido eliminar(Pedido p) throws SQLException {
        if (p == null) {
            return null;
        } else {
            return eliminar(p.getCodigo()) != null ? p : null;
        }
    }

    @Override
    public boolean existe(Pedido p) throws SQLException {
        return existe(p.getCodigo());
    }

    @Override
    public ArrayList<Pedido> getAll() throws SQLException {
        ArrayList<Pedido> lista = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY codigo";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");
            LocalDateTime fecha = resultSet.getTimestamp("fecha").toLocalDateTime();
            double precioTotal = resultSet.getDouble("precio_total");
            Usuario usuario = new UsuarioDAO().getByCodigo(resultSet.getInt("usuario"));
            Direccion direccion = new DireccionDAO().getByCodigo(resultSet.getInt("codigo"));
            //MetodoPago metodoPago = MetodoPago.valueOf(resultSet.getString("metodopago"));
            boolean facturado = estaFacturado(codigo);
            HashMap<Producto, Entry<Integer, Double>> lineasPedido = getLineas(codigo);
            lista.add(new Pedido(codigo, fecha, precioTotal, facturado, usuario, direccion, lineasPedido));
        }

        return lista;
    }

    @Override
    public Pedido getByCodigo(int codigo) throws SQLException {
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE codigo=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codigo);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            LocalDateTime fecha = resultSet.getTimestamp("fecha").toLocalDateTime();
            double precioTotal = resultSet.getDouble("precio_total");
            Usuario usuario = new UsuarioDAO().getByCodigo(resultSet.getInt("usuario"));
            Direccion direccion = new DireccionDAO().getByCodigo(resultSet.getInt("codigo"));
            //MetodoPago metodoPago = MetodoPago.valueOf(resultSet.getString("metodopago"));
            boolean facturado = estaFacturado(codigo);
            HashMap<Producto, Entry<Integer, Double>> lineasPedido = getLineas(codigo);
            return new Pedido(codigo, fecha, precioTotal, facturado, usuario, direccion, lineasPedido);
        }

        return null;
    }

    public HashMap<Producto, Entry<Integer, Double>> getLineas(int codPedido) throws SQLException {
        ProductoDAO productoDAO = new ProductoDAO();
        HashMap<Producto, Entry<Integer, Double>> lineas = new HashMap<>();
        String sentenciaSQL = "SELECT producto, cantidad, precio FROM ARTESDORADAS_pedidos_productos WHERE pedido = ?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codPedido);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            Producto producto = productoDAO.getByCodigo(resultSet.getInt("producto"));
            int cantidad = resultSet.getInt("cantidad");
            double precio = resultSet.getDouble("precio");
            lineas.put(producto, Map.entry(cantidad, precio));
        }

        return lineas;

    }

    private void anyadirLineas(Pedido p) throws SQLException {
        for (Entry<Producto, Entry<Integer, Double>> linea : p.getLineasPedido().entrySet()) {
            String sentenciaSQL = "INSERT INTO ARTESDORADAS_pedidos_productos VALUES(?, ?, ?, ?)";
            PreparedStatement prepared = getPrepared(sentenciaSQL);
            prepared.setInt(1, linea.getKey().getCodigo());
            prepared.setInt(2, p.getCodigo());
            prepared.setInt(3, linea.getValue().getKey());
            prepared.setDouble(4, linea.getValue().getValue());

            prepared.executeUpdate();
        }
    }

    private void eliminarLineas(Pedido p) throws SQLException {
        String sentenciaSQL = "DELETE FROM ARTESDORADAS_pedidos_productos WHERE pedido=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, p.getCodigo());
        prepared.executeUpdate();

    }

    public boolean estaFacturado(int codpedido) throws SQLException {
        String sentenciaSQL = "SELECT * FROM ARTESDORADAS_facturas WHERE pedido=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codpedido);
        return prepared.executeUpdate() != 0;

    }

}
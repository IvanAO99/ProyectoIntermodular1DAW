package modelo.dao;

import modelo.dto.Usuario;
import modelo.dto.Pedido;
import modelo.dto.Producto;
import modelo.dto.Direccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class PedidoDAO extends TablaDAO<Pedido> {

    public PedidoDAO() {
        this.tabla = "ARTESDORADAS_pedidos";
    }

    @Override
    public int actualizar(Pedido p) throws SQLException {
        //No necesario para el proyecto
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int anyadir(Pedido p) throws SQLException {
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, p.getCodigo());
        prepared.setTimestamp(2, Timestamp.valueOf(p.getFechaPedido()));
        prepared.setDouble(3, p.getPrecioTotal());
        prepared.setString(4, p.isFacturado() ? "Sí" : "No");
        prepared.setInt(5, p.getCliente().getCodigo());
        prepared.setInt(6, p.getCliente().getCodigo());
        prepared.setInt(7, p.getDireccion().getCodigo());
        int resultado = prepared.executeUpdate();
        anyadirLineas(p);
        return resultado;

    }

    @Override
    public Pedido eliminar(Pedido p) throws SQLException {
        //No necesario para el proyecto
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            Usuario usuario = new UsuarioDAO().getByCodigo(resultSet.getInt("cliente"));
            Direccion direccion = new DireccionDAO().getByCodigo(resultSet.getInt("direccion_envio"));
            boolean facturado = (resultSet.getString("facturado").equals("Sí"));
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
            Usuario usuario = new UsuarioDAO().getByCodigo(resultSet.getInt("cliente"));
            Direccion direccion = new DireccionDAO().getByCodigo(resultSet.getInt("direccion_envio"));
            boolean facturado = (resultSet.getString("facturado").equals("Sí"));
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

    public void descontarStock(Pedido p) throws SQLException {
        for (Entry<Producto, Entry<Integer, Double>> linea : p.getLineasPedido().entrySet()) {
            Producto pr = linea.getKey();
            String sentenciaSQL = "UPDATE stock SET " + (p.cantidadStock(pr) - 1) + " WHERE pedido = " + p;
            PreparedStatement prepared = getPrepared(sentenciaSQL);
            prepared.executeUpdate();
        }
    }
}

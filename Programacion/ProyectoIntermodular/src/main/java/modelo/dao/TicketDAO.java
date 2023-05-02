package modelo.dao;

import modelo.dto.Usuario;
import modelo.dto.Ticket;
import modelo.dto.EstadoTicket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class TicketDAO extends TablaDAO<Ticket> {

    public TicketDAO() {
        this.tabla = "ARTESDORADAS_tickets";
    }

    @Override
    public int actualizar(Ticket t) throws SQLException {
        //No necesario para el proyecto
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int anyadir(Ticket t) throws SQLException {
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, t.getCodigo());
        prepared.setString(2, t.getMensaje());
        prepared.setString(3, t.getAsunto());
        prepared.setString(4, t.getEstado().toString());
        prepared.setInt(5, t.getCliente().getCodigo());
        return prepared.executeUpdate();
    }

    @Override
    public Ticket eliminar(Ticket t) throws SQLException {
        //No necesario para el proyecto
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existe(Ticket t) throws SQLException {
        return existe(t.getCodigo());
    }

    @Override
    public ArrayList<Ticket> getAll() throws SQLException {
        ArrayList<Ticket> lista = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY codigo";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");
            String mensaje = resultSet.getString("mensaje");
            String asunto = resultSet.getString("asunto");
            EstadoTicket estado = EstadoTicket.valueOf(resultSet.getString("estado").toUpperCase());
            Usuario cliente = new UsuarioDAO().getByCodigo(resultSet.getInt("codigo"));
            lista.add(new Ticket(codigo, mensaje, asunto, estado, cliente));
        }

        return lista;
    }

    @Override
    public Ticket getByCodigo(int codigo) throws SQLException {
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE codigo=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codigo);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            String mensaje = resultSet.getString("mensaje");
            String asunto = resultSet.getString("asunto");
            EstadoTicket estado = EstadoTicket.valueOf(resultSet.getString("estado").toUpperCase());
            Usuario cliente = new UsuarioDAO().getByCodigo(resultSet.getInt("codigo"));
            return new Ticket(codigo, mensaje, asunto, estado, cliente);
        }

        return null;
    }

}

package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.PedidoDAO;
import modelo.dto.Pedido;
import modelo.dto.Usuario;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class ConfirmarPedido extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try ( PrintWriter out = response.getWriter()) {
            Usuario usuarioSesion = (session != null && session.getAttribute("usuario") != null) ? (Usuario) session.getAttribute("usuario") : null;
            if (usuarioSesion == null) {
                out.println("<h2>No tienes permiso para acceder a esta sección</h2><p><a href=\"javascript: history.go(-1)\">Volver atrás</a></p>");

            } else if (session.getAttribute("carrito") == null) {
                response.sendRedirect("carrito.jsp");
            } else {
                PedidoDAO pedidoDAO = new PedidoDAO();
                Pedido carrito = (Pedido) session.getAttribute("carrito");
                Pedido pedido = new Pedido(pedidoDAO.siguienteCodigo(), LocalDateTime.now(), carrito.getPrecioTotal(), false, usuarioSesion, carrito.getDireccion(), carrito.getLineasPedido());
                if (pedido.hayStock()) {
                    pedidoDAO.descontarStock(pedido);
                    pedidoDAO.anyadir(pedido);
                    session.removeAttribute("carrito");
                    response.sendRedirect("index.html");
                } else {
                    out.println("<h2>No tenemos suficiente stock para confirmar el pedido</h2><p><a href=\"carrito.jsp\">Volver atrás</a></p>");
                }

            }
        } catch (SQLException ex) {
            System.out.println("Error SQL");
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

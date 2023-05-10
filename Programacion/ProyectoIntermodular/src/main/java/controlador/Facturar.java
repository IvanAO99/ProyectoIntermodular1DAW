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
import modelo.dao.DireccionDAO;
import modelo.dao.FacturaDAO;
import modelo.dao.PedidoDAO;
import modelo.dto.Direccion;
import modelo.dto.Factura;
import modelo.dto.Pedido;
import modelo.dto.Usuario;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class Facturar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
        String strId = request.getParameter("id");
        PedidoDAO pedidoDAO = new PedidoDAO();
        FacturaDAO facturaDAO = new FacturaDAO();

        Usuario usuarioSesion = (session != null && session.getAttribute("usuario") != null) ? (Usuario) session.getAttribute("usuario") : null;

        try ( PrintWriter out = response.getWriter()) {
            // Primero validamos que haya usuario logueado y no sea anónimo
            if (usuarioSesion == null) {
                out.println("<h2>No tienes permiso para acceder a esta sección</h2><p><a href=\"javascript: history.go(-1)\">Volver atrás</a></p>");
                // Después validamos que el código de pedido que nos hay llegado sea un valor entero    
            } else if (!strId.chars().allMatch(Character::isDigit)) {
                out.println("<h2>Código incorrecto.</h2><p><a href=\"javascript: history.go(-1)\">Volver atrás</a></p>");
            } else {
                Pedido pedidoAFacturar = pedidoDAO.getByCodigo(Integer.parseInt(strId));
                // Si el código es numérico pero no existe en la base de datos, o bien no pertenece al usuario conectado (excepto si es admin) mostraremos error.
                if (pedidoAFacturar == null || (!pedidoAFacturar.getCliente().equals(usuarioSesion) && !usuarioSesion.getTipo().toString().equals("Administrador"))) {
                    out.println("<h2>Pedido no encontrado.</h2><p><a href=\"javascript: history.go(-1)\">Volver atrás</a></p>");
                } else {
                    String strDir = request.getParameter("direccion");
                    Direccion dirEnvio = new DireccionDAO().getByCodigo(Integer.parseInt(strDir));
                    if (dirEnvio == null || (!dirEnvio.getCliente().equals(usuarioSesion) && !usuarioSesion.getTipo().toString().equals("Administrador"))) {
                        out.println("<h2>Dirección no encontrada</h2><p><a href=\"javascript: history.go(-1)\">Volver atrás</a></p>");
                    } else {
                        // Si la factura pertenece al usuario en sesión (o es admin) entonces creamos una nueva factura con los datos del formulario y actualizamos el pedido (facturado = true)
                        Pedido pedidoFacturado = new Pedido(pedidoAFacturar.getCodigo(), pedidoAFacturar.getFechaPedido(), pedidoAFacturar.getPrecioTotal(), true, pedidoAFacturar.getCliente(), pedidoAFacturar.getDireccion(), pedidoAFacturar.getLineasPedido());
                        Factura factura = new Factura(facturaDAO.siguienteCodigo(), LocalDateTime.now(), pedidoAFacturar.getCliente(), dirEnvio, pedidoFacturado);
                        facturaDAO.anyadir(factura);
                        //pedidoDAO.actualizar(pedidoFacturado);
                        response.sendRedirect("index.html");
                    }

                }
            }

        } catch (SQLException ex) {
            System.out.println("Error SQL");
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

package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.DireccionDAO;
import modelo.dao.ProductoDAO;
import modelo.dto.Direccion;
import modelo.dto.Pedido;
import modelo.dto.Producto;
import modelo.dto.Usuario;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class AnyadirAlCarrito extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            Usuario usuarioSesion = (session != null && session.getAttribute("usuario") != null) ? (Usuario) session.getAttribute("usuario") : null;
            if (usuarioSesion == null || usuarioSesion.isAnonimo()) {
                out.println("<h2>No tienes permiso para acceder a esta sección</h2><p><a href=\"javascript: history.go(-1)\">Volver atrás</a></p>");

            } else {

                // Validamos los datos del formulario
                String strId = request.getParameter("id");
                String strCantidad = request.getParameter("cantidad");
                if (strId != null && strCantidad != null && strId.chars().allMatch(Character::isDigit) && strCantidad.chars().allMatch(Character::isDigit)) {
                    Producto pro = new ProductoDAO().getByCodigo(Integer.parseInt(strId));

                    int cantidad = Integer.parseInt(strCantidad);
                    //Si existe el carrito en la sesión obtenemos las líneas y añadimos la del formulario
                    LinkedHashMap<Producto, Integer> lineas = new LinkedHashMap<>();
                    //MetodoPago mp = MetodoPago.paypal;
                    Direccion dir = null;
                    if (session.getAttribute("carrito") != null) {
                        Pedido carrito = (Pedido) session.getAttribute("carrito");
                        lineas = new LinkedHashMap<>(carrito.getLineasPedido());
                        //mp = carrito.getMetodoPago();
                        dir = carrito.getDireccion();

                    } //Si no existe el carrito añadimos las líneas desde cero
                    else {
                        dir = usuarioSesion.getListaDirecciones(new DireccionDAO().getAll()).get(0);
                    }
                    lineas.putIfAbsent(pro, cantidad);
                    Pedido ped = new Pedido(1, LocalDateTime.now(), usuarioSesion, dir, lineas, false);
                    session.setAttribute("carrito", ped);
                    response.sendRedirect("carrito.jsp");
                } else {
                    out.println("<h2>Datos incorrectos. Revisa el formulario</h2><p><a href=\"javascript: history.go(-1)\">Volver atrás</a></p>");
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

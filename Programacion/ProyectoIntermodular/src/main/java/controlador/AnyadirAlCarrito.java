/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.DireccionDAO;
import modelo.dao.PedidoDAO;
import modelo.dao.ProductoDAO;
import modelo.dto.Direccion;
import modelo.dto.Pedido;
import modelo.dto.Producto;
import modelo.dto.Usuario;

/**
 *
 * @author ivan
 */
public class AnyadirAlCarrito extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        try {
            Usuario usuarioSesion = (session != null && session.getAttribute("usuario") != null) ? (Usuario) session.getAttribute("usuario") : null;

            if (usuarioSesion == null) {
                request.setAttribute("errorAnyadirCarrito", true);
                request.getRequestDispatcher("producto.jsp?codProducto=" + request.getParameter("codProducto")).include(request, response);
            } else {

                // Validamos los datos del formulario
                String strId = request.getParameter("codProducto");
                String strCantidad = request.getParameter("cantidad");
                if (strId != null && strCantidad != null && strId.chars().allMatch(Character::isDigit) && strCantidad.chars().allMatch(Character::isDigit)) {
                    Producto pro = new ProductoDAO().getByCodigo(Integer.parseInt(strId));
                    int cantidad = Integer.parseInt(strCantidad);

                    //Si existe el carrito en la sesión obtenemos las líneas y añadimos la del formulario
                    HashMap<Producto, Entry<Integer, Double>> lineas = new HashMap<>();
                    Direccion dir = null;

                    if (session.getAttribute("carrito") != null) {
                        Pedido carrito = (Pedido) session.getAttribute("carrito");
                        lineas = new HashMap<>(carrito.getLineasPedido());
                        dir = carrito.getDireccion();

                    } //Si no existe el carrito añadimos las líneas desde cero
                    else {
                        dir = new DireccionDAO().getDireccionesDe(usuarioSesion).get(0);
                    }

                    lineas.putIfAbsent(pro, Map.entry(cantidad, pro.getPrecioCompleto()));
                    Pedido ped = new Pedido(new PedidoDAO().siguienteCodigo(), LocalDateTime.now(), Pedido.calcularPrecioTotal(lineas), false, usuarioSesion, dir, lineas);
                    session.setAttribute("carrito", ped);
                    response.sendRedirect("carrito.jsp");
                } else {
                    request.setAttribute("errorFormCarrito", true);
                    request.getRequestDispatcher("producto.jsp?codProducto=" + request.getParameter("codProducto")).include(request, response);
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

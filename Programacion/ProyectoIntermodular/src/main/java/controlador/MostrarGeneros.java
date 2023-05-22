/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.CategoriaDAO;
import modelo.dao.LibroDAO;
import modelo.dto.Categoria;
import modelo.dto.Libro;

/**
 *
 * @author ivan
 */
public class MostrarGeneros extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String tipoProducto = request.getParameter("tipoProducto");
            Set<Categoria> categorias = new TreeSet<>();

            if (tipoProducto == null) {
                System.out.println(categorias);
                request.setAttribute("categorias", categorias);
                request.getRequestDispatcher("productos.jsp").forward(request, response);
            } else {

                if (tipoProducto.equals("libro")) {
                    categorias = new CategoriaDAO().getCategoriasDeLibros();
                    request.setAttribute("categorias", categorias);
                    request.getRequestDispatcher("productos.jsp?tipo=libros").forward(request, response);
                } else if (tipoProducto.equals("disco")) {
                    categorias = new CategoriaDAO().getCategoriasDeDiscos();
                    request.setAttribute("categorias", categorias);
                    request.getRequestDispatcher("productos.jsp?tipo=discos").forward(request, response);
                } else {
                    System.out.println(categorias);
                    request.setAttribute("categorias", categorias);
                    request.getRequestDispatcher("productos.jsp").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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

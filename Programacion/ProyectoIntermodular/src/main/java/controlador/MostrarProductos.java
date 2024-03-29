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
import modelo.dao.DiscoDAO;
import modelo.dao.LibroDAO;
import modelo.dao.ProductoDAO;
import modelo.dto.Categoria;
import modelo.dto.Libro;
import modelo.dto.Producto;

/**
 *
 * @author ivan
 */
public class MostrarProductos extends HttpServlet {

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
            Set<Categoria> categoriasSet = new TreeSet<>();
            String consulta = "cat.codigo = ";

            String[] categorias = request.getParameterValues("categoria");
            String tipo = request.getParameter("tipo");

            if (tipo.equals("libros") && categorias == null) {
                categoriasSet = new CategoriaDAO().getCategoriasDeLibros();

                request.setAttribute("categorias", categoriasSet);
                request.setAttribute("errorArrayVacio", true);
                request.getRequestDispatcher("productos.jsp").include(request, response);
            } else if (tipo.equals("discos") && categorias == null) {
                categoriasSet = new CategoriaDAO().getCategoriasDeDiscos();

                request.setAttribute("categorias", categoriasSet);
                request.setAttribute("errorArrayVacio", true);
                request.getRequestDispatcher("productos.jsp").include(request, response);
            } else {

                if (tipo.equals("libros")) {

                    for (int i = 0; i < categorias.length; i++) {
                        if (i == categorias.length - 1) {
                            consulta = consulta.concat(categorias[i]);
                        } else {
                            consulta = consulta.concat(categorias[i]).concat(" OR cat.codigo = ");
                        }
                    }

                    //System.out.println("Consulta SQL: " + consulta);
                    //System.out.println("He recibido las categorias");
                    //String buscador = request.getParameter("buscador");
                    ArrayList<Libro> productos = new LibroDAO().getByCategorias(consulta);
                    categoriasSet = new CategoriaDAO().getCategoriasDeLibros();

                    request.setAttribute("categorias", categoriasSet);
                    request.setAttribute("productos", productos);

                    request.getRequestDispatcher("productos.jsp").forward(request, response);

                } else {
                    for (int i = 0; i < categorias.length; i++) {
                        if (i == categorias.length - 1) {
                            consulta = consulta.concat(categorias[i]);
                        } else {
                            consulta = consulta.concat(categorias[i]).concat(" OR cat.codigo = ");
                        }
                    }

                    //System.out.println("Consulta SQL: " + consulta);
                    //System.out.println("He recibido las categorias");
                    //String buscador = request.getParameter("buscador");
                    ArrayList<? extends Producto> productos = new DiscoDAO().getByCategorias(consulta);
                    categoriasSet = new CategoriaDAO().getCategoriasDeDiscos();

                    request.setAttribute("categorias", categoriasSet);
                    request.setAttribute("productos", productos);

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

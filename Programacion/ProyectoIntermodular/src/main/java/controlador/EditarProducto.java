/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import modelo.dao.CategoriaDAO;
import modelo.dao.ProductoDAO;
import modelo.dto.Categoria;
import modelo.dto.Producto;
import modelo.dto.Usuario;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class EditarProducto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String DIRECTORI_GUARDAT = "imagenes";

    protected static Producto recogerProductoFormulario(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("utf-8");
            DiskFileItemFactory factory = new DiskFileItemFactory();

            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            

            List<FileItem> llistaParametres = upload.parseRequest(request);

            // Recogemos los campos del formulario
            int iterador = 0;
            String nombre = llistaParametres.get(iterador++).getString();
            String descripcion = llistaParametres.get(iterador++).getString();
            Double precio = llistaParametres.get(iterador).getString().equals("") ? null : Double.valueOf(llistaParametres.get(iterador).getString());
            iterador++;
            String unidad = llistaParametres.get(iterador++).getString();
            Integer iva = llistaParametres.get(iterador).getString().equals("") ? null : Integer.valueOf(llistaParametres.get(iterador).getString());
            iterador++;
            Integer stock = llistaParametres.get(iterador).getString().equals("") ? null : Integer.valueOf(llistaParametres.get(iterador).getString());
            iterador++;
            Integer stockmin = llistaParametres.get(iterador).getString().equals("") ? null : Integer.valueOf(llistaParametres.get(iterador).getString());
            iterador++;
            Integer codigo = llistaParametres.get(iterador).getString().equals("") ? null : Integer.valueOf(llistaParametres.get(iterador).getString());
            iterador++;
            // RECOGER CATEGORIAS
            LinkedHashSet<Categoria> categorias = new LinkedHashSet<>();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            for (; iterador < llistaParametres.size() - 2; iterador++) {
                categorias.add(categoriaDAO.getByCodigo(Integer.parseInt(llistaParametres.get(iterador).getString())));
            }

            // Ruta absoluta de l'aplicació
            String appPath = request.getServletContext().getRealPath("");
            // Ruta completa on es guarda l'arxiu
            String savePath = appPath + DIRECTORI_GUARDAT;
            // Crea el directori de guardat si no existeix
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }

            FileItem fichero = llistaParametres.get(llistaParametres.size() - 2);
            String fileName = null;
            // Si s'ha pujat fitxer el guardem.
            if (fichero.getSize() != 0) {
                fileName = new File(fichero.getName()).getName();
                String filePath = savePath + File.separator + fileName;
                File storeFile = new File(filePath);

                // Guarda en el disc
                fichero.write(storeFile);
            }
            /*for (Part part : request.getParts()) {
                fileName = extractFileName(part);
                fileName = new File(fileName).getName();
                part.write(savePath + File.separator + fileName);
            } */

            // ¡¡ Importante validar los datos también en el servidor!! Por si nos llega alguno de los campos obligatorios vacios !! Si alguno llega vacio retornamos NULL
            if (codigo != null && nombre != null && descripcion != null && precio != null && unidad != null && iva != null && stock != null && stockmin != null && !categorias.isEmpty()) {
                Usuario usuarioModifica = (Usuario) request.getSession().getAttribute("usuario");
                System.out.println(usuarioModifica);
                Producto antiguo = new ProductoDAO().getByCodigo(codigo);
                System.out.println(antiguo);
                String rutaImagen = (fichero.getSize() != 0) ? ("imagenes/" + fileName) : antiguo.getFoto();
                 Producto p = new Producto(antiguo.getCodigo(), utf8(nombre), utf8(descripcion), precio, utf8(unidad), iva, stock, stockmin, utf8(rutaImagen), antiguo.getUsuarioCrea(), antiguo.getFechaCreacion(), usuarioModifica, LocalDateTime.now(), categorias);
                 return p;
            }

        } catch (FileUploadException | SQLException ex) {
            System.err.println(ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    public static String utf8(String s) {
        String out;
        try {
            out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (NullPointerException | UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            Producto p = recogerProductoFormulario(request);
            Usuario usuarioSesion = (session != null && session.getAttribute("usuario") != null) ? (Usuario) session.getAttribute("usuario") : null;
            if (usuarioSesion == null || !usuarioSesion.isAdmin()) {
                out.println("<h2>No tienes permiso para acceder a esta sección</h2><p><a href=\"javascript: history.go(-1)\">Volver atrás</a></p>");
            } else if (p == null) {
                out.println("<h2>Se ha producido un error. Revisa los datos del formulario</h2><p><a href=\"javascript: history.go(-1)\">Volver atrás</a></p>");
            } else {
                try {
                    new ProductoDAO().actualizar(p);
                } catch (SQLException ex) {
                    out.println("<h2>Se ha producido un error al ejecutar la sentencia en la BD</h2><p><a href=\"javascript: history.go(-1)\">Volver atrás</a></p>");
                }
                response.sendRedirect("index.html");
            }
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

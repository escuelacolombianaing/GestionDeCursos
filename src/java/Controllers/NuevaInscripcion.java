/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import ConexionBD.Componentes;
import ConexionBD.Configuracion;
import Model.InfoCurso;
import Model.InfoInscripcion;
import Model.myResponse;
import Utils.Utilidades;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javier.tambo
 */
public class NuevaInscripcion extends HttpServlet {

      Configuracion Conexion = new Configuracion();
    Utilidades misUtilidades = new Utilidades();
    InfoInscripcion InformacionDeInscr = new InfoInscripcion();
    myResponse _myResponse = new myResponse();
    Componentes consumirComponente;
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NuevaInscripcion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NuevaInscripcion at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
     
        
        
        
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
        
          try {
               String Dato = null;
            Gson gson = new Gson();
              Dato = misUtilidades.httpServletRequestToString(request);
              InformacionDeInscr = gson.fromJson(Dato.toString(), InfoInscripcion.class);
            consumirComponente = new Componentes();
             _myResponse = consumirComponente.InsertNuevaInscripcion(InformacionDeInscr);

            if (_myResponse.isTransaccion()) {
                _myResponse.setMessage("Inscripción realizada con exíto");
            }
            
          } catch (Exception ex) {
              _myResponse.setError(ex.getMessage());
              _myResponse.setInformacion(false);
              _myResponse.setTransaccion(false);
          }finally {

            String json = null;
            json = new Gson().toJson(_myResponse);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }

            
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

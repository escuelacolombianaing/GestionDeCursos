/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ConexionBD.*;
import com.google.gson.Gson;
import Model.*;
import Utils.Utilidades;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author javier.tambo
 */
public class ReporteGeneral extends HttpServlet {

    Utilidades misUtilidades = new Utilidades();
    myResponse _myResponse= new myResponse();
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
            out.println("<title>Servlet ReporteGeneral</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReporteGeneral at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
      String json = null;
        try {
            
        FiltrosDinamicos NuevoFiltro= new FiltrosDinamicos();
        ConexionBD.Componentes Componente= new Componentes();
        Model.ReporteGeneral Reporte = new Model.ReporteGeneral();
         String Dato = null;
            Gson gson = new Gson();
              Dato = misUtilidades.httpServletRequestToString(request);
              NuevoFiltro = gson.fromJson(Dato.toString(), FiltrosDinamicos.class);
          Reporte=Componente.InformacionReporteGeneral(NuevoFiltro);           
              
              json = new Gson().toJson(Reporte);
            response.setContentType("application/json");
            response.getWriter().write(json);
              
              
        } catch (Exception ex) {
            _myResponse.setError(ex.getMessage());
              _myResponse.setInformacion(false);
              _myResponse.setTransaccion(false);
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

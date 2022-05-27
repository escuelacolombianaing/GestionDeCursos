/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import ConexionBD.Componentes;
import ConexionBD.Configuracion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;
import Utils.Utilidades;
import com.google.gson.Gson;
/**
 *
 * @author javier.tambo
 */
public class NotaDelInscrito extends HttpServlet {
 Configuracion Conexion = new Configuracion();
    Utilidades misUtilidades = new Utilidades();
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
            out.println("<title>Servlet NotaDelInscrito</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NotaDelInscrito at " + request.getContextPath() + "</h1>");
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
        myResponse _myResponse=new myResponse();
        NotaDeCurso InfoNotaCurso=new NotaDeCurso();
         try {
           String Dato = null;
            Gson gson = new Gson();

            Dato = misUtilidades.httpServletRequestToString(request);

            InfoNotaCurso = gson.fromJson(Dato.toString(), NotaDeCurso.class);
            consumirComponente = new Componentes();
            
            if (consumirComponente.ValidarExistenciaNota(InfoNotaCurso)){
             _myResponse = consumirComponente.UpdateNuevaNota(InfoNotaCurso);  
            }else{            
              _myResponse = consumirComponente.InsertarNuevaNota(InfoNotaCurso);  
            }
           
        } catch (Exception e) {
            _myResponse.setError(e.getMessage());
            _myResponse.setTransaccion(false);
        }finally{
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

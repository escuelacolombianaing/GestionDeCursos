/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import ConexionBD.Componentes;
import ConexionBD.Configuracion;
import Model.InfoCurso;
import Model.myResponse;
import Utils.Utilidades;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javier.tambo
 */
@WebServlet(name = "GestionDeCursos", urlPatterns = {"/GestionDeCursos"})
public class GestionDeCursos extends HttpServlet {

      Configuracion Conexion = new Configuracion();
    Utilidades misUtilidades = new Utilidades();
    InfoCurso InformacionDeCurso = new InfoCurso();
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
            out.println("<title>Servlet GestionDeCursos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GestionDeCursos at " + request.getContextPath() + "</h1>");
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
       
        
           try {

            String Dato = null;
            Gson gson = new Gson();

            Dato = misUtilidades.httpServletRequestToString(request);

            InformacionDeCurso = gson.fromJson(Dato.toString(), InfoCurso.class);
            consumirComponente = new Componentes();

            if (InformacionDeCurso.id==0){
            _myResponse = consumirComponente.InsertNuevoCurso(InformacionDeCurso);
            }else{
            _myResponse = consumirComponente.UpdateInfoCurso(InformacionDeCurso);            
            }
            

        } catch (Exception e) {
            _myResponse.setError(e.getMessage());
            _myResponse.setTransaccion(false);
        } finally {

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

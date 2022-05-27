/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ConexionBD.*;
import Model.FiltrosDinamicos;
import Utils.Utilidades;
import com.google.gson.Gson;
/**
 *
 * @author javier.tambo
 */
@WebServlet(name = "Listas", urlPatterns = {"/Utils/Listas"})
public class VerListas extends HttpServlet {

    
    Utilidades misUtilidades = new Utilidades();
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
        
        List<Model.Listas> listas=new ArrayList<Model.Listas>();
        ConexionBD.Listas MisListas=new ConexionBD.Listas();
                FiltrosDinamicos filtros=new FiltrosDinamicos();
        listas=MisListas.NuevaLista(filtros);
         String json = null;
         json = new Gson().toJson(listas);
                response.setContentType("application/json");
                response.getWriter().write(json);
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
            
      
            List<Model.Listas> listas=new ArrayList<Model.Listas>();
        ConexionBD.Listas MisListas=new ConexionBD.Listas();
          String Dato = null;
            Gson gson = new Gson();
         Dato = misUtilidades.httpServletRequestToString(request);        
                FiltrosDinamicos filtros=new FiltrosDinamicos();
      filtros=gson.fromJson(Dato.toString(), FiltrosDinamicos.class);
              
        listas=MisListas.NuevaLista(filtros);
         String json = null;
         json = new Gson().toJson(listas);
                response.setContentType("application/json");
                response.getWriter().write(json);
                  } catch (Exception e) {
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

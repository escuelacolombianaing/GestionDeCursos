/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import ConexionBD.Componentes;
import Model.InfoCurso;
import Model.InfoListInscritos;
import Model.myResponse;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javier.tambo
 */
public class ListInscritos extends HttpServlet {

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
            out.println("<title>Servlet ListInscritos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListInscritos at " + request.getContextPath() + "</h1>");
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
        InfoCurso idcurso = new InfoCurso();
        String json = null;
        myResponse _myResponse = new myResponse();
        List<InfoListInscritos> listaInscritos = new ArrayList<InfoListInscritos>();
        Componentes componentes = new Componentes();
        try {
            listaInscritos = componentes.InformacionAllInscritos(request.getParameter("Num_Doc").toString().trim());
            json = new Gson().toJson(listaInscritos);
            response.setContentType("application/json");
            response.getWriter().write(json);

        } catch (Exception e) {
            _myResponse.setError("Error: " + e.getMessage());
            _myResponse.setTransaccion(false);
            _myResponse.setInformacion(false);
            InfoListInscritos infoInscritos = new InfoListInscritos();
            infoInscritos.setMyresResponse(_myResponse);
            listaInscritos.add(infoInscritos);
            json = new Gson().toJson(listaInscritos);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
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

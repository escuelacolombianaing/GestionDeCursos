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
import Utils.*;
import ConexionBD.*;
import Model.InfoUsuario;
import javax.servlet.http.HttpSession;
/**
 *
 * @author javier.tambo
 */
public class Login extends HttpServlet {

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
        Componentes misComponentes=new Componentes();
        InfoUsuario infousuario=new InfoUsuario();
        Encripta encriptacion = new Encripta();
       int id_emp=Integer.parseInt(request.getParameter("id_emp"));
        String cadena=request.getParameter("cadena");
        try {
            if(misComponentes.ValidatToken(id_emp, encriptacion.Encriptar(cadena, id_emp))){
                misComponentes=new Componentes();
               infousuario= misComponentes.ObtenerInfUsu(id_emp);
               HttpSession misession= request.getSession(true);
               misession.setAttribute("nombre",infousuario.getNombre());
               misession.setAttribute("id_usu",infousuario.getId_usu());
               misession.setAttribute("rol",infousuario.getRol());
               if(infousuario.getRol()==1){
                   response.sendRedirect(request.getContextPath() + "/jsp/InicioAdm.jsp");
               }else if(infousuario.getRol()==2){
               response.sendRedirect(request.getContextPath() + "/jsp/InicioPro.jsp");
               }else if(infousuario.getRol()==3){
               response.sendRedirect(request.getContextPath() + "/jsp/InicioProAdm.jsp");
               }else if(infousuario.getRol()==4){
               response.sendRedirect(request.getContextPath() + "/jsp/Inicio.jsp");
               }else{
                   response.sendRedirect(request.getContextPath() + "/jsp/PageError.jsp");
               }
               
            }else{
             response.sendRedirect(request.getContextPath() + "/jsp/PageError.jsp");
            }
           
        }catch(Exception ex){
        } finally {     
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

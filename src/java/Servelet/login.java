/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servelet;

import Entity.Usuario;
import Schema.UsuariosService;
import Schema.UsuariosService;
import Utilities.ConnectionBD;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Roberto Santin
 */
@WebServlet(name = "login", urlPatterns =
    {
        "/login"
})
public class login extends HttpServlet
{

    HttpServletRequest request;
    HttpServletResponse response;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        this.request = request;
        this.response = response;
        
        if (request.getParameter("parametro").equals("login"))
        {
            validarLogin();
        }
    }

    private void validarLogin()
    {
        String email = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        UsuariosService us = ConnectionBD.getInstance().getUsuariosService();
        Usuario user = us.getUserCanLogin( email, senha );
        
        if ( user != null )
        {
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuarioLogado", user );
            sessao.setAttribute("error", null );
            redirect( "home.jsp" );
        }
        
        else
        {
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuarioLogado", null );
            sessao.setAttribute("error", "Usuário/Senha incorretos" );
            redirect( "index.jsp" );
        }
    }

    private void redirect( String pagina )
    {
        try
        {
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            rd.forward(request, response);
        }
        
        catch (Exception e)
        {
            System.out.println("erro ao encaminhar página");
        }
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}

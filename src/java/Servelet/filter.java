/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Roberto Santin
 */
@WebFilter("/*")
public class filter extends HttpServlet implements Filter {

    List<String> urls = new ArrayList<>();

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet filtro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet filtro at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        urls.add("/IoT");
        urls.add("/IoT/login");
        urls.add("/IoT/recover");
        urls.add("/IoT/recovery.jsp");
        urls.add("/IoT/logout");
        urls.add("/IoT/resultado");
        urls.add("/IoT/index.jsp");
        urls.add("/IoT/assets/img/logo.png");
        urls.add("/IoT/images/background.jpg");
        urls.add("/IoT/css/login.css");
        urls.add("/IoT/bootstrap/js/bootstrap.js");
        urls.add("/IoT/bootstrap/js/bootstrap.min.js");
        urls.add("/IoT/bootstrap/js/npm.min.js");
        urls.add("/IoT/bootstrap/js/jquery-1.11.1.js");
        urls.add("/IoT/bootstrap/css/bootstrap.css");
        urls.add("/IoT/bootstrap/css/bootstrap.min.css");
        urls.add("/IoT/bootstrap/css/bootstrap-theme.min.css");
        urls.add("/IoT/bootstrap/css/bootstrap-theme.css");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (urls.contains(req.getRequestURI()))
        {
            request.setAttribute("parametro", "login");
            chain.doFilter(request, response);
        }
        else
        {
            HttpSession sessao = ((HttpServletRequest) request).getSession();

            if (sessao.getAttribute("usuarioLogado") == null)
            {
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
            else
            {
                System.out.println("Destino: " + req.getRequestURI());
                chain.doFilter(request, response);
            }
        }
    }

}
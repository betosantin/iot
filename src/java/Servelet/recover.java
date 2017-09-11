package Servelet;

import Entity.Usuario;
import Utilities.ConnectionBD;
import Utilities.Email;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Roberto Santin
 */
public class recover extends HttpServlet {

    HttpServletRequest requisicao;
    HttpServletResponse resposta;

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
            out.println("<title>Servlet acao</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet acao at " + request.getContextPath() + "</h1>");
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
        requisicao = request;
        resposta = response;
        
        doPost(request, response);
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
        
        requisicao = request;
        resposta = response;

        if (request.getParameter("parametro") != null )
        {
            if (request.getParameter("parametro").equals("recuperarSenha"))
            {
                salvarLancamento();
            }
        }
    }

    private void salvarLancamento() throws IOException {
        
        String login = requisicao.getParameter( "login" );
        
        Usuario u = ConnectionBD.getInstance().getUsuariosService().getSource(login);
        
        if ( u == null )
        {
            requisicao.getSession().setAttribute("error", "Usuário não existe" );
            encaminharPagina("recovery.jsp");
        }
        else
        {
            String senha = new BigInteger(130, new SecureRandom()).toString(32);

            u.setSenhaUsuario( senha );

            try
            {
                Email.sendEmail(u.getEmailUsuario(), senha);

                ConnectionBD.getInstance().getUsuariosService().update(u);
                requisicao.getSession().setAttribute("error", "E-mail de recuperação de senha encaminhado.");
                encaminharPagina("index.jsp");

            }
            catch (Exception ex)
            {
                requisicao.getSession().setAttribute("error", ex);
                encaminharPagina("index.jsp");

            }
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

    private void encaminharPagina(String pagina) {
        try {
            RequestDispatcher rd = requisicao.getRequestDispatcher(pagina);
            rd.forward(requisicao, resposta);
        } catch (Exception e) {
            System.out.println("erro ao encaminhar página");
        }
    }

}

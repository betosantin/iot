/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelet;

import Entity.Dispositivo;
import Entity.Metodos;
import Entity.Parametros;
import TableName.MetodosName;
import TableName.ParametrosName;
import Utilities.ConnectionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roberto.santin
 */
public class controller extends HttpServlet {

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
            String paramet = request.getParameter("showoptions");
            
            if (!paramet.isEmpty()) {
                writeForm();
            }
        }
    }

    private void writeForm() throws IOException 
    {
        int id = Integer.parseInt(requisicao.getParameter("showoptions"));
        
        Dispositivo d = new ConnectionBD().getDispositivosService().getSource(id);
        
        if (d != null) {

            List<Metodos> ms = new ConnectionBD().getMetodosService().getAllFromDispositivo(d.getIdDispositivo());

            String divGroup = "<div class=\"form-group\">";
            
            for (Metodos m : ms) {
                String label = " <label>Função: " + m.getNomeMetodo() +"</label><br><br>";
                
                divGroup += label;
                        
                List<Parametros> ps = new ConnectionBD().getParametrosService().getAllFromMetodos(m.getIdMetodo());

                divGroup += "<form name=\"" + m.getNomeMetodo() + "\" action=\"" + d.getIp() + ":" + d.getPorta() + "/" + d.getServico() + "/"
                        + d.getServelet() + "?" + m.getNomeMetodo() + "&\" method=\"post\">";

                for (Parametros p : ps) {
                    divGroup += "<div class=\"form-group\">";

                    String labelParemtro = " <label>Parâmetro: " + p.getNomeParametro() + "</label>";
                    String input = " <input name=\"" + p.getNomeParametro() + "\" \n"
                            + "    class=\"form-control\""
                            + "    placeholder=\"" + p.getNomeParametro() + "\""
                            + "    type=\"" + p.typeInput() + "\">";

                    divGroup += labelParemtro + input + "</div><br>";
                }

                divGroup += "<button type=\"submit\" class=\"btn btn-primary\">Executar</button></a></div></form>";

                divGroup += "<br><br>";
            }
            
            try {

                resposta.setCharacterEncoding("utf-8");
                resposta.setContentType("text/html");
                resposta.getWriter().write(divGroup);

            } catch (IOException ex) {
                ex.printStackTrace();
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
        return "";
    }

    private void encaminharPagina(String pagina) {
        try {
            RequestDispatcher rd = requisicao.getRequestDispatcher(pagina);
            rd.forward(requisicao, resposta);
        } catch (Exception e) {
            System.out.println("erro ao encaminhar página");
        }
    }

}

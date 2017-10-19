/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelet;

import Entity.Resultado;
import Entity.Usuario;
import Schema.UsuariosService;
import Utilities.ConnectionBD;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Roberto Santin
 */
public class resultado extends HttpServlet {

    HttpServletRequest requisicao;
    HttpServletResponse resposta;

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
      
        String parameter = requisicao.getParameter("pass");

        if (parameter != null && !parameter.isEmpty()) {

            UsuariosService us = ConnectionBD.getInstance().getUsuariosService();

            boolean continuar = false;

            for (Usuario u : us.getAll()) {
                if (u.getSenhaUsuario().equalsIgnoreCase(parameter)) {
                    continuar = true;
                    break;
                }
            }

            if (continuar) {

                Resultado res = new Gson().fromJson(new String(requisicao.getParameter("resultado").getBytes("ISO8859_1"), "UTF8"), Resultado.class);

                if (res != null) {
                    res.setDispositivo(requisicao.getRemoteAddr());
                    ConnectionBD.getInstance().getResultadoService().insert(res);
                    resposta.sendError(HttpServletResponse.SC_OK);
                } else {
                    resposta.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                resposta.sendError(HttpServletResponse.SC_FORBIDDEN);
            }

        } else {
            resposta.setCharacterEncoding("utf-8");
            resposta.setContentType("text/html");
            resposta.sendError(HttpServletResponse.SC_BAD_REQUEST);
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

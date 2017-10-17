/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelet;

import Entity.Dispositivo;
import Entity.Metodos;
import Entity.Parametros;
import Entity.Usuario;
import Schema.DispositivosService;
import Schema.MetodosService;
import Schema.ParametrosService;
import TableName.DispositivosName;
import TableName.UsuariosName;
import Utilities.ConnectionBD;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.omg.CORBA.NameValuePair;

/**
 *
 * @author roberto.santin
 */
public class acao extends HttpServlet {

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
            String paramet = request.getParameter("parametro");
            
            if (paramet.equals("salvarUsuario")) {
                salvarUsuario();
            }

            if (paramet.equals("excluirUsuario")) {
                excluirUsuario();
            }

            if (paramet.equals("editarUsuario")) {
                editarUsuario();
            }

            if (paramet.equals("salvarDispositivo")) {
                salvarDispositivo();
            }
            
            if (paramet.equals("excluirDispositivo")) {
                excluirDispositivo();
            }

            if (paramet.equals("editarDispositivo")) {
                editarDispositivo();
            }
            
            if (paramet.equals("sincronizarDispositivo")) {
                sincronizarDispositivo();
            }
            
            if (paramet.equals("statusDispositivo")) {
                statusDispositivo();
            }

        }
    }

    private void editarUsuario() throws IOException {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Usuario user = new ConnectionBD().getUsuariosService().getSource(id);
        
        requisicao.setAttribute("editUsuario", user);
        
        encaminharPagina("editUsuario.jsp");
    }
    
    private void salvarUsuario() throws IOException {
        
        Usuario u = new Usuario();
        String error = "";
        
        int id = Integer.parseInt(requisicao.getParameter(UsuariosName.idUsuario));
        String nome = requisicao.getParameter(UsuariosName.nomeUsuario);
        String login = requisicao.getParameter(UsuariosName.loginUsuario);
        String senha = requisicao.getParameter(UsuariosName.senhaUsuario);
        String email = requisicao.getParameter(UsuariosName.emailUsuario);
        int tipo = requisicao.getParameter(UsuariosName.tipoUsuario).equalsIgnoreCase("administrador") ? Usuario.TIPO_ADMINISTRADOR : Usuario.TIPO_OPERADOR;
        int status = requisicao.getParameter(UsuariosName.statusUsuario) != null ? Usuario.STATUS_ATIVO : Usuario.STATUS_INATIVO;

        u.setIdUsuario(id);
        u.setNomeUsuario(nome);
        u.setLoginUsuario(login);
        u.setSenhaUsuario(senha);
        u.setEmailUsuario(email);
        u.setTipoUsuario(tipo);
        u.setStatusUsuario(status);

        boolean retorno = false;
        
        List<Usuario> users = new ConnectionBD().getUsuariosService().getAll();
        
        if (u.getIdUsuario() == 0) { // é uma inserção
            
            for (Usuario usuario : users)
            {
                if ( usuario.getLoginUsuario().equalsIgnoreCase( u.getLoginUsuario() ) )
                {
                    error += "Login já existe. Por favor, tente outro Login.";
                }
            }
            
            if ( error.isEmpty() )
            {
                error = "Usuário " + nome + " cadastrado com sucesso." ;
                retorno = new ConnectionBD().getUsuariosService().insert(u);
            }
        
        } else {
            
            for (Usuario usuario : users)
            {
                if ( usuario.getLoginUsuario().equalsIgnoreCase( u.getLoginUsuario() ) && usuario.getIdUsuario() != u.getIdUsuario() )
                {
                    error += "Login já existe. Por favor, tente outro Login.";
                }
            }
            
            if ( error.isEmpty() )
            {
                error = "Usuário " + nome + " atualizado com sucesso." ;
                retorno = new ConnectionBD().getUsuariosService().update(u);
            }
            
        }
        
        requisicao.setAttribute("paginaRetorno", "cadUsuario.jsp");

        if ( retorno ) 
        {
            
            if (error.isEmpty())
            {
                requisicao.getSession().setAttribute("retorno", "Usuário " + nome + " cadastrado com sucesso." );
            }
            else
            {
                requisicao.getSession().setAttribute("retorno", error );
            }
            
            encaminharPagina("cadUsuario.jsp");
        }
        
        else
        {
            if (error.isEmpty())
            {
                requisicao.getSession().setAttribute("retorno", "Erro ao inserir/editar registro.");
            }
            else
            {
                requisicao.getSession().setAttribute("retorno", error );
            }
            
            encaminharPagina("editUsuario.jsp");
        }
        
    }
    
    private void excluirUsuario() throws IOException {
        int id = Integer.parseInt(requisicao.getParameter("id"));

        Usuario u = (Usuario) requisicao.getSession().getAttribute("usuarioLogado");
        
        if ( u.getIdUsuario() == id )
        {
            requisicao.getSession().setAttribute("retorno", "Você não pode excluir o próprio usuário." );
        }
        
        else
        {
            boolean retorno = new ConnectionBD().getUsuariosService().delete(id);
            requisicao.setAttribute("paginaRetorno", "cadUsuario.jsp");

            if (retorno)
            {
                requisicao.getSession().setAttribute("retorno", "Usuário id: " + id + " deletado com sucesso.");
            }

            else
            {
                requisicao.getSession().setAttribute("retorno", "Erro ao Excluir Usuário.");
            }
        }
        encaminharPagina("cadUsuario.jsp");
    }
    
    
    private void salvarDispositivo() throws IOException {
        
        Dispositivo d = new Dispositivo();
        String error = "";
        
        int id = Integer.parseInt(requisicao.getParameter(DispositivosName.idDispositivo));
        String nome = requisicao.getParameter(DispositivosName.nomeDispositivo);
        String ip = requisicao.getParameter(DispositivosName.ip);
        int porta = Integer.parseInt(requisicao.getParameter(DispositivosName.porta));
        String servico = requisicao.getParameter(DispositivosName.servico);
        String servelet = requisicao.getParameter(DispositivosName.servelet);

        d.setIdDispositivo(id);
        d.setNomeDispositivo(nome);
        d.setIp(ip);
        d.setPorta(porta);
        d.setServico(servico);
        d.setServelet(servelet);

        boolean retorno = false;
        
        if (d.getIdDispositivo() == 0) { // é uma inserção
            
            if ( error.isEmpty() )
            {
                error = "Dispositivo " + nome + " cadastrado com sucesso." ;
                retorno = new ConnectionBD().getDispositivosService().insert(d);
            }
        
        } else {
            
            if ( error.isEmpty() )
            {
                error = "Dispositivo " + nome + " atualizado com sucesso." ;
                retorno = new ConnectionBD().getDispositivosService().update(d);
            }
            
        }
        
        requisicao.setAttribute("paginaRetorno", "cadDispositivos.jsp");

        if ( retorno ) 
        {
            
            if (error.isEmpty())
            {
                requisicao.getSession().setAttribute("retorno", "Usuário " + nome + " cadastrado com sucesso." );
            }
            else
            {
                requisicao.getSession().setAttribute("retorno", error );
            }
            
            encaminharPagina("cadDispositivos.jsp");
        }
        
        else
        {
            if (error.isEmpty())
            {
                requisicao.getSession().setAttribute("retorno", "Erro ao inserir/editar registro.");
            }
            else
            {
                requisicao.getSession().setAttribute("retorno", error );
            }
            
            encaminharPagina("editDispositivos.jsp");
        }
        
    }
    
    private void editarDispositivo() throws IOException {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Dispositivo d = new ConnectionBD().getDispositivosService().getSource(id);
        
        requisicao.setAttribute("editDispositivo", d);
        
        encaminharPagina("editDispositivos.jsp");
    }
    
    private void sincronizarDispositivo() throws IOException {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        DispositivosService ds = new ConnectionBD().getDispositivosService();
        ParametrosService ps = new ConnectionBD().getParametrosService();
        MetodosService ms = new ConnectionBD().getMetodosService();
        
        Dispositivo d = ds.getSource(id);
        
        if ( d != null )
        {
            String scheme = requisicao.getScheme();
            String addrs = requisicao.getRemoteAddr();
            int port = requisicao.getLocalPort();
            String path = requisicao.getContextPath().replaceAll("/", "" );
            Usuario user = (Usuario) requisicao.getSession().getAttribute("usuarioLogado");
            
            String urlString = d + "sincronizar&"
                    + "httptype=" + scheme
                    + "&ip=" + addrs
                    + "&porta=" + port
                    + "&servelet=" + path
                    + "&retorno=resultado&"
                    + "pass=" + user.getSenhaUsuario();

            BufferedReader rd = null;

            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setReadTimeout(10000);
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                TypeToken<List<Metodos>> token = new TypeToken<List<Metodos>>() {
                };
                List<Metodos> personList = new Gson().fromJson(rd.readLine(), token.getType());

                rd.close();

                List<Metodos> dn = ms.getAllFromDispositivo(id);
                
                if (dn.isEmpty()) {

                    for (Metodos metodo : personList) {
                        metodo.setIdDispositivo(d.getIdDispositivo());
                        metodo.setIdMetodo(ms.getNextId());

                        ms.insert(metodo);

                        if (metodo.getParametros() != null) {
                            for (Parametros p : metodo.getParametros()) {
                                p.setIdParametro(ps.getNextId());
                                p.setIdMetodo(metodo.getIdMetodo());

                                ps.insert(p);
                            }
                        }

                        System.out.println(metodo);
                    }
                    
                    requisicao.getSession().setAttribute("retorno", "Sincronizado com sucesso, dispositivo ONLINE.");

                }
                
                else
                {
                    requisicao.getSession().setAttribute("retorno", "Dispositivo ONLINE. Funções já sincronizadas.");
                }


            } catch (IOException ex) {
                System.out.println("TIME OUT......");
                requisicao.getSession().setAttribute("retorno", "Timeout, verifique o endereço IP, Porta, Serviço e Servelet da conexão");
                ex.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        encaminharPagina("cadDispositivos.jsp");
    }
    
    private void statusDispositivo() throws IOException {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        DispositivosService ds = new ConnectionBD().getDispositivosService();
        
        Dispositivo d = ds.getSource(id);
        
        if ( d != null )
        {
            String urlString = d + "status";

            BufferedReader rd = null;

            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setReadTimeout(10000);
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                rd.readLine();
                
                rd.close();
                
            } catch (IOException ex) {
                ex.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        encaminharPagina("cadDispositivos.jsp");
    }
    
    private void excluirDispositivo() throws IOException {
        int id = Integer.parseInt(requisicao.getParameter("id"));

        boolean retorno = new ConnectionBD().getDispositivosService().delete(id);
        
        requisicao.setAttribute("paginaRetorno", "cadDispositivos.jsp");

        if (retorno) {
            requisicao.getSession().setAttribute("retorno", "Dispositivo id: " + id + " deletado com sucesso.");
        } else {
            requisicao.getSession().setAttribute("retorno", "Erro ao Excluir Dispositivo.");
        }
        
        encaminharPagina("cadDispositivos.jsp");
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

    static {
    SslVerification();
}

private static void SslVerification() {
    try
    {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    } catch (KeyManagementException e) {
        e.printStackTrace();
    }
}
}

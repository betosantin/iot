package Utilities;



import Entity.Usuario;
import Schema.DispositivosService;
import Schema.MetodosService;
import Schema.ParametrosService;
import Schema.UsuariosService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author Roberto Santin
 */
public class ConnectionBD
{

    private Connection connection = null;
    private static ConnectionBD defaultInstance = null;

    public static ConnectionBD getInstance()
    {
        if ( defaultInstance == null )
        {
            defaultInstance = new ConnectionBD();
        }

        return defaultInstance;
    }

    public ConnectionBD()
    {
        try
        {
            // Carrega informações do arquivo de propriedades
            Properties prop = new Properties();
//            prop.load(new FileInputStream("db.properties"));
            prop.load(getClass().getResourceAsStream("db.properties"));
            String dbdriver = prop.getProperty("db.driver");
            String dburl = prop.getProperty("db.url");
            String dbuser = prop.getProperty("db.user");
            String dbsenha = prop.getProperty("db.senha");

            // Carrega Driver do Banco de Dados
            Class.forName(dbdriver);

            if (dbuser.length() != 0) // conexão COM usuário e senha
            {
                connection = DriverManager.getConnection(dburl, dbuser, dbsenha);
            }
            else // conexão SEM usuário e senha
            {
                connection = DriverManager.getConnection(dburl);
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }

    public Connection getConnection()
    {
        if (connection == null)
        {
            throw new RuntimeException("conexao==null");
        }
        return connection;
    }
    
    public UsuariosService getUsuariosService()
    {
        UsuariosService usuariosService = null;
        
        try
        {
            usuariosService = UsuariosService.getInstance();
        }
        
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        
        return usuariosService;
    }
    
    public DispositivosService getDispositivosService()
    {
        DispositivosService dispositivosService = null;
        
        try
        {
            dispositivosService = DispositivosService.getInstance();
        }
        
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        
        return dispositivosService;
    }
    
    public MetodosService getMetodosService()
    {
        MetodosService metodosService = null;
        
        try
        {
            metodosService = MetodosService.getInstance();
        }
        
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        
        return metodosService;
    }
    
    public ParametrosService getParametrosService()
    {
        ParametrosService parametrosService = null;
        
        try
        {
            parametrosService = ParametrosService.getInstance();
        }
        
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        
        return parametrosService;
    }
    
    public Usuario getActityUser()
    {
        return null;
    }
    
}

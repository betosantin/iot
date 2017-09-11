package Schema;

import Utilities.ConnectionBD;
import Entity.Usuario;
import Interface.DAO;
import TableName.UsuariosName;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Roberto Santin
 */
public class UsuariosService implements DAO
{
    private Connection connection = null;
    private static UsuariosService defaultInstance;

    public static UsuariosService getInstance() throws Exception
    {
        if (defaultInstance == null)
        {
            defaultInstance = new UsuariosService();
        }
        return defaultInstance;
    }

    public UsuariosService()
    {
        this.connection = ConnectionBD.getInstance().getConnection();
    }
    
    @Override
    public boolean insert( Object o )
    {
        Usuario u = (Usuario) o;
        
        if ( u == null )
        {
            return false;
        }
        
        try
        {
            connection.createStatement().executeUpdate("INSERT INTO " + UsuariosName.table_name + " "
                    + "(" + UsuariosName.sql + " ) "
                    + "VALUES (DEFAULT,'" + u.getNomeUsuario() + "','" + u.getLoginUsuario() + "'"
                    + ",'" + codificationPassWord( u.getSenhaUsuario() ) + "'," + u.getTipoUsuario() + ",'" + u.getEmailUsuario() + "'"
                    + ",'" + u.getStatusUsuario() + "' );");
            
            return true;            
        }
        
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean update(Object o)
    {
        Usuario u = (Usuario) o;
        
        if ( u == null )
        {
            return false;
        }
        
        try
        {
            connection.createStatement().executeUpdate( "UPDATE " + UsuariosName.table_name + " "
                    + "SET " + UsuariosName.nomeUsuario + " = '" + u.getNomeUsuario() + "' , "
                    + UsuariosName.loginUsuario + " = '" + u.getLoginUsuario() + "' , "
                    + UsuariosName.senhaUsuario + " = '" + codificationPassWord( u.getSenhaUsuario() ) + "' , "
                    + UsuariosName.tipoUsuario + " = " + u.getTipoUsuario() + " , "
                    + UsuariosName.emailUsuario + " = '" + u.getEmailUsuario() + "' , "
                    + UsuariosName.statusUsuario + " = " + + u.getStatusUsuario() + " "
                    + "WHERE " + UsuariosName.idUsuario + " = " + u.getIdUsuario() );
            
            return true;            
        }
        
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean delete(int id)
    {
        try
        {
            connection.createStatement().executeUpdate( "DELETE FROM " + UsuariosName.table_name + " "
                    + "WHERE " + UsuariosName.idUsuario + " = " + id );
            
            return true;            
        }
        
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    @Override
    public ArrayList<Usuario> getAll()
    {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try
        {
            ResultSet result = connection.createStatement()
                                      .executeQuery( "SELECT * FROM " + UsuariosName.table_name );
            
            while( result.next() )
            {
                Usuario u = new Usuario();
                
                u.setIdUsuario( result.getInt( UsuariosName.idUsuario ) );
                u.setNomeUsuario( result.getString( UsuariosName.nomeUsuario ) );
                u.setLoginUsuario( result.getString( UsuariosName.loginUsuario ) );
                u.setSenhaUsuario( result.getString( UsuariosName.senhaUsuario ) );
                u.setTipoUsuario( result.getInt( UsuariosName.tipoUsuario ) );
                u.setEmailUsuario( result.getString( UsuariosName.emailUsuario ) );
                u.setStatusUsuario( result.getInt( UsuariosName.statusUsuario ) );
                
                usuarios.add( u );
            }
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao consultar: getAll() - UsuariosServices.java" );
        }
        
        return usuarios;
    }

    @Override
    public Usuario getSource( Object o ) throws SQLException
    {
        Usuario u = (Usuario) o;
        
        if ( u == null )
        {
            new Exception( "This object is null in getSource of UsuariosService.java" );
        }
        
        return getSource( u.getIdUsuario() );
    }

    @Override
    public Usuario getSource( int id )
    {
        Usuario usuario = null;

        try
        {
            ResultSet result = connection.createStatement()
                                      .executeQuery( "SELECT * FROM " + UsuariosName.table_name + 
                                                     " WHERE " + UsuariosName.idUsuario + " = " + id );
            
            while( result.next() )
            {
                usuario = new Usuario();
                
                usuario.setIdUsuario( result.getInt( UsuariosName.idUsuario ) );
                usuario.setNomeUsuario( result.getString( UsuariosName.nomeUsuario ) );
                usuario.setLoginUsuario( result.getString( UsuariosName.loginUsuario ) );
                usuario.setSenhaUsuario( result.getString( UsuariosName.senhaUsuario ) );
                usuario.setTipoUsuario( result.getInt( UsuariosName.tipoUsuario ) );
                usuario.setEmailUsuario( result.getString( UsuariosName.emailUsuario ) );
                usuario.setStatusUsuario( result.getInt( UsuariosName.statusUsuario ) );
            }
        }

        catch ( Exception e )
        {
            System.out.println( "Error in the method: getSource(id) - UsuariosService.java" );
        }
        
        return usuario;
    }
    
    public Usuario getSource( String login )
    {
        Usuario usuario = null;

        try
        {
            ResultSet result = connection.createStatement()
                                      .executeQuery( "SELECT * FROM " + UsuariosName.table_name + 
                                                     " WHERE " + UsuariosName.loginUsuario + " = '" + login + "'");
            
            while( result.next() )
            {
                usuario = new Usuario();
                
                usuario.setIdUsuario( result.getInt( UsuariosName.idUsuario ) );
                usuario.setNomeUsuario( result.getString( UsuariosName.nomeUsuario ) );
                usuario.setLoginUsuario( result.getString( UsuariosName.loginUsuario ) );
                usuario.setSenhaUsuario( result.getString( UsuariosName.senhaUsuario ) );
                usuario.setTipoUsuario( result.getInt( UsuariosName.tipoUsuario ) );
                usuario.setEmailUsuario( result.getString( UsuariosName.emailUsuario ) );
                usuario.setStatusUsuario( result.getInt( UsuariosName.statusUsuario ) );
            }
        }

        catch ( Exception e )
        {
            System.out.println( "Error in the method: getSource(id) - UsuariosService.java" );
        }
        
        return usuario;
    }
    
    public Usuario getUserCanLogin( String login, String password )
    {
        Usuario usuario = null;

        try
        {
            String psC = codificationPassWord( password );
            
            ResultSet result = connection.createStatement()
                                      .executeQuery( "SELECT * FROM " + UsuariosName.table_name + 
                                                     " WHERE " + UsuariosName.loginUsuario + " = '" + login + "'" +
                                                     " AND " + UsuariosName.senhaUsuario + " = '" + psC + "'"  );
            
            while( result.next() )
            {
                usuario = new Usuario();
                
                usuario.setIdUsuario( result.getInt( UsuariosName.idUsuario ) );
                usuario.setNomeUsuario( result.getString( UsuariosName.nomeUsuario ) );
                usuario.setLoginUsuario( result.getString( UsuariosName.loginUsuario ) );
                usuario.setSenhaUsuario( result.getString( UsuariosName.senhaUsuario ) );
                usuario.setTipoUsuario( result.getInt( UsuariosName.tipoUsuario ) );
                usuario.setEmailUsuario( result.getString( UsuariosName.emailUsuario ) );
                usuario.setStatusUsuario( result.getInt( UsuariosName.statusUsuario ) );
            }
        }

        catch ( Exception e )
        {
            System.out.println( "Error in the method: getSource(id) - UsuariosService.java" );
        }
        
        return usuario;
    }
    
    public String codificationPassWord( String password )
    {
        try
        {
            return "<" + String.format( "%040x", new BigInteger( MessageDigest.getInstance( "SHA1" ).digest( password.getBytes() ) ).abs() ) + ">";
        }
        
        catch (Exception ex)
        {
            System.out.println( "Error in the method: codificationPassWord(password) - UsuariosService.java " + ex );
        }
        
        return "";
    }
}

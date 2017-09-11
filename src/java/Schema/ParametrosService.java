package Schema;

import Entity.Parametros;
import Utilities.ConnectionBD;
import Interface.DAO;
import TableName.ParametrosName;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Roberto Santin
 */
public class ParametrosService implements DAO
{
    private Connection connection = null;
    private static ParametrosService defaultInstance;

    public static ParametrosService getInstance() throws Exception
    {
        if (defaultInstance == null)
        {
            defaultInstance = new ParametrosService();
        }
        return defaultInstance;
    }

    public ParametrosService()
    {
        this.connection = ConnectionBD.getInstance().getConnection();
    }
    
    @Override
    public boolean insert( Object o )
    {
        Parametros u = (Parametros) o;
        
        if ( u == null )
        {
            return false;
        }
        
        try
        {
            connection.createStatement().executeUpdate("INSERT INTO " + ParametrosName.table_name + " "
                    + "(" + ParametrosName.sql + " ) "
                    + "VALUES (" + u.getIdParametro()+ ",'" + u.getNomeParametro() + "'," + u.getTipo()
                    + "," + u.getIdMetodo() + " );");
            
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
        Parametros u = (Parametros) o;
        
        if ( u == null )
        {
            return false;
        }
        
        try
        {
            connection.createStatement().executeUpdate( "UPDATE " + ParametrosName.table_name + " "
                    + "SET " + ParametrosName.nomeParametro + " = '" + u.getNomeParametro() + "' , "
                    + ParametrosName.tipoParametro + " = " + u.getTipo()+ " , "
                    + ParametrosName.idMetodo + " = " + u.getIdMetodo() + " "
                    + "WHERE " + ParametrosName.idParametro + " = " + u.getIdParametro());
            
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
            connection.createStatement().executeUpdate( "DELETE FROM " + ParametrosName.table_name + " "
                    + "WHERE " + ParametrosName.idParametro + " = " + id );
            
            return true;            
        }
        
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    @Override
    public ArrayList<Parametros> getAll()
    {
        ArrayList<Parametros> usuarios = new ArrayList<>();

        try
        {
            ResultSet result = connection.createStatement()
                                      .executeQuery( "SELECT * FROM " + ParametrosName.table_name );
            
            while( result.next() )
            {
                Parametros u = new Parametros();
                
                u.setIdParametro( result.getInt( ParametrosName.idParametro ) );
                u.setIdMetodo(result.getInt( ParametrosName.idMetodo ) );
                u.setNomeParametro( result.getString( ParametrosName.nomeParametro ) );
                u.setTipo( result.getInt( ParametrosName.tipoParametro ) );
                
                usuarios.add( u );
            }
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao consultar: getAll() - ParametrosService.java" );
        }
        
        return usuarios;
    }

    public ArrayList<Parametros> getAllFromMetodos(int idMetodo )
    {
        ArrayList<Parametros> usuarios = new ArrayList<>();

        try
        {
            ResultSet result = connection.createStatement()
                                      .executeQuery( "SELECT * FROM " + ParametrosName.table_name + " WHERE " + ParametrosName.idMetodo + " = " + idMetodo  );
            
            while( result.next() )
            {
                Parametros u = new Parametros();
                
                u.setIdParametro( result.getInt( ParametrosName.idParametro ) );
                u.setIdMetodo(result.getInt( ParametrosName.idMetodo ) );
                u.setNomeParametro( result.getString( ParametrosName.nomeParametro ) );
                u.setTipo( result.getInt( ParametrosName.tipoParametro ) );
                
                usuarios.add( u );
            }
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao consultar: getAll() - ParametrosService.java" );
        }
        
        return usuarios;
    }
    
    @Override
    public Parametros getSource( Object o ) throws SQLException
    {
        Parametros u = (Parametros) o;
        
        if ( u == null )
        {
            new Exception( "This object is null in getSource of ParametrosService.java" );
        }
        
        return getSource( u.getIdMetodo());
    }

    @Override
    public Parametros getSource( int id )
    {
        Parametros u = null;

        try
        {
            ResultSet result = connection.createStatement()
                                      .executeQuery( "SELECT * FROM " + ParametrosName.table_name + 
                                                     " WHERE " + ParametrosName.idParametro + " = " + id );
            
            while( result.next() )
            {
                u = new Parametros();
                
                u.setIdParametro( result.getInt( ParametrosName.idParametro ) );
                u.setIdMetodo(result.getInt( ParametrosName.idMetodo ) );
                u.setNomeParametro( result.getString( ParametrosName.nomeParametro ) );
                u.setTipo( result.getInt( ParametrosName.tipoParametro ) );
                
            }
        }

        catch ( Exception e )
        {
            System.out.println( "Error in the method: getSource(id) - ParametrosService.java" );
        }
        
        return u;
    }
    
    public int getNextId() 
    {
        int id = 0;

        try {
            ResultSet result = connection.createStatement()
                    .executeQuery("SELECT nextval(pg_get_serial_sequence('" + ParametrosName.table_name + "', '"
                            + ParametrosName.idParametro + "')) as " + ParametrosName.idParametro);

            while (result.next()) {
                id = result.getInt(ParametrosName.idParametro);
            }
        } catch (Exception e) {
            System.out.println("Error in the method: getNextId() - ParametrosService.java");
        }

        return id;
    }
    
}

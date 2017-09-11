package Schema;

import Entity.Metodos;
import Utilities.ConnectionBD;
import Interface.DAO;
import TableName.MetodosName;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Roberto Santin
 */
public class MetodosService implements DAO
{
    private Connection connection = null;
    private static MetodosService defaultInstance;

    public static MetodosService getInstance() throws Exception
    {
        if (defaultInstance == null)
        {
            defaultInstance = new MetodosService();
        }
        return defaultInstance;
    }

    public MetodosService()
    {
        this.connection = ConnectionBD.getInstance().getConnection();
    }
    
    @Override
    public boolean insert( Object o )
    {
        Metodos u = (Metodos) o;
        
        if ( u == null )
        {
            return false;
        }
        
        try
        {
            connection.createStatement().executeUpdate("INSERT INTO " + MetodosName.table_name + " "
                    + "(" + MetodosName.sql + " ) "
                    + "VALUES (" + u.getIdMetodo()+ ",'" + u.getNomeMetodo()+ "'," + u.getTipoRetorno()
                    + "," + u.getIdDispositivo() + " );");
            
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
        Metodos u = (Metodos) o;
        
        if ( u == null )
        {
            return false;
        }
        
        try
        {
            connection.createStatement().executeUpdate( "UPDATE " + MetodosName.table_name + " "
                    + "SET " + MetodosName.nomeMetodo + " = '" + u.getNomeMetodo()+ "' , "
                    + MetodosName.tipoRetorno + " = " + u.getTipoRetorno()+ " , "
                    + MetodosName.idDispositivo + " = " + u.getIdDispositivo() + " "
                    + "WHERE " + MetodosName.idMetodo + " = " + u.getIdMetodo() );
            
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
            connection.createStatement().executeUpdate( "DELETE FROM " + MetodosName.table_name + " "
                    + "WHERE " + MetodosName.idMetodo + " = " + id );
            
            return true;            
        }
        
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    @Override
    public ArrayList<Metodos> getAll()
    {
        ArrayList<Metodos> usuarios = new ArrayList<>();

        try
        {
            ResultSet result = connection.createStatement()
                                      .executeQuery( "SELECT * FROM " + MetodosName.table_name );
            
            while( result.next() )
            {
                Metodos u = new Metodos();
                
                u.setIdMetodo(result.getInt( MetodosName.idMetodo ) );
                u.setNomeMetodo( result.getString( MetodosName.nomeMetodo ) );
                u.setTipoRetorno( result.getInt( MetodosName.tipoRetorno ) );
                u.setIdDispositivo( result.getInt( MetodosName.idDispositivo ) );
                
                usuarios.add( u );
            }
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao consultar: getAll() - MetodosService.java" );
        }
        
        return usuarios;
    }

    public ArrayList<Metodos> getAllFromDispositivo( int idDispositivo )
    {
        ArrayList<Metodos> usuarios = new ArrayList<>();

        try
        {
            ResultSet result = connection.createStatement()
                                      .executeQuery( "SELECT * FROM " + MetodosName.table_name + " WHERE " + MetodosName.idDispositivo + " = " + idDispositivo );
            
            while( result.next() )
            {
                Metodos u = new Metodos();
                
                u.setIdMetodo(result.getInt( MetodosName.idMetodo ) );
                u.setNomeMetodo( result.getString( MetodosName.nomeMetodo ) );
                u.setTipoRetorno( result.getInt( MetodosName.tipoRetorno ) );
                u.setIdDispositivo( result.getInt( MetodosName.idDispositivo ) );
                
                usuarios.add( u );
            }
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao consultar: getAll() - MetodosService.java" );
        }
        
        return usuarios;
    }
    
    @Override
    public Metodos getSource( Object o ) throws SQLException
    {
        Metodos u = (Metodos) o;
        
        if ( u == null )
        {
            new Exception( "This object is null in getSource of MetodosService.java" );
        }
        
        return getSource( u.getIdMetodo());
    }

    @Override
    public Metodos getSource( int id )
    {
        Metodos u = null;

        try
        {
            ResultSet result = connection.createStatement()
                                      .executeQuery( "SELECT * FROM " + MetodosName.table_name + 
                                                     " WHERE " + MetodosName.idMetodo + " = " + id );
            
            while( result.next() )
            {
                u = new Metodos();
                
                u.setIdMetodo(result.getInt( MetodosName.idMetodo ) );
                u.setNomeMetodo( result.getString( MetodosName.nomeMetodo ) );
                u.setTipoRetorno( result.getInt( MetodosName.tipoRetorno ) );
                u.setIdDispositivo( result.getInt( MetodosName.idDispositivo ) );
            }
        }

        catch ( Exception e )
        {
            System.out.println( "Error in the method: getSource(id) - MetodosService.java" );
        }
        
        return u;
    }
    
    public int getNextId() 
    {
        int id = 0;

        try {
            ResultSet result = connection.createStatement()
                    .executeQuery("SELECT nextval(pg_get_serial_sequence('" + MetodosName.table_name + "', '"
                            + MetodosName.idMetodo + "')) as " + MetodosName.idMetodo);

            while (result.next()) {
                id = result.getInt(MetodosName.idMetodo);
            }
        } catch (Exception e) {
            System.out.println("Error in the method: getNextId() - MetodosService.java");
        }

        return id;
    }
    
}

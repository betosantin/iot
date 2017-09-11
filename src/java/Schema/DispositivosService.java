package Schema;

import Entity.Dispositivo;
import Utilities.ConnectionBD;
import Interface.DAO;
import TableName.DispositivosName;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Roberto Santin
 */
public class DispositivosService implements DAO
{
    private Connection connection = null;
    private static DispositivosService defaultInstance;

    public static DispositivosService getInstance() throws Exception
    {
        if (defaultInstance == null)
        {
            defaultInstance = new DispositivosService();
        }
        return defaultInstance;
    }

    public DispositivosService()
    {
        this.connection = ConnectionBD.getInstance().getConnection();
    }
    
    @Override
    public boolean insert( Object o )
    {
        Dispositivo u = (Dispositivo) o;
        
        if ( u == null )
        {
            return false;
        }
        
        try
        {
            connection.createStatement().executeUpdate("INSERT INTO " + DispositivosName.table_name + " "
                    + "(" + DispositivosName.sql + " ) "
                    + "VALUES (" + u.getIdDispositivo() + ",'" + u.getNomeDispositivo() + "','" + u.getIp() + "'," + u.getPorta() 
                    + ",'" + u.getServico()+ "','" + u.getServelet()+ "' );");
            
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
        Dispositivo u = (Dispositivo) o;
        
        if ( u == null )
        {
            return false;
        }
        
        try
        {
            connection.createStatement().executeUpdate( "UPDATE " + DispositivosName.table_name + " "
                    + "SET " + DispositivosName.nomeDispositivo + " = '" + u.getNomeDispositivo()+ "' , "
                    + DispositivosName.ip + " = '" + u.getIp() + "' , "
                    + DispositivosName.porta + " = " + u.getPorta()+ " , "
                    + DispositivosName.servico + " = '" + u.getServico()+ "' , "
                    + DispositivosName.servelet + " = '" + u.getServelet() + "' "
                    + "WHERE " + DispositivosName.idDispositivo + " = " + u.getIdDispositivo() );
            
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
            connection.createStatement().executeUpdate( "DELETE FROM " + DispositivosName.table_name + " "
                    + "WHERE " + DispositivosName.idDispositivo + " = " + id );
            
            return true;            
        }
        
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    @Override
    public ArrayList<Dispositivo> getAll()
    {
        ArrayList<Dispositivo> usuarios = new ArrayList<>();

        try
        {
            ResultSet result = connection.createStatement()
                                      .executeQuery( "SELECT * FROM " + DispositivosName.table_name );
            
            while( result.next() )
            {
                Dispositivo u = new Dispositivo();
                
                u.setIdDispositivo( result.getInt( DispositivosName.idDispositivo ) );
                u.setNomeDispositivo( result.getString( DispositivosName.nomeDispositivo ) );
                u.setIp( result.getString( DispositivosName.ip ) );
                u.setPorta( result.getInt( DispositivosName.porta ) );
                u.setServico( result.getString( DispositivosName.servico ) );
                u.setServelet( result.getString( DispositivosName.servelet ) );
                
                usuarios.add( u );
            }
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao consultar: getAll() - DispositivosService.java" );
        }
        
        return usuarios;
    }

    @Override
    public Dispositivo getSource( Object o ) throws SQLException
    {
        Dispositivo u = (Dispositivo) o;
        
        if ( u == null )
        {
            new Exception( "This object is null in getSource of DispositivosService.java" );
        }
        
        return getSource( u.getIdDispositivo());
    }

    @Override
    public Dispositivo getSource( int id )
    {
        Dispositivo u = null;

        try
        {
            ResultSet result = connection.createStatement()
                                      .executeQuery( "SELECT * FROM " + DispositivosName.table_name + 
                                                     " WHERE " + DispositivosName.idDispositivo + " = " + id );
            
            while( result.next() )
            {
                u = new Dispositivo();
                
                u.setIdDispositivo( result.getInt( DispositivosName.idDispositivo ) );
                u.setNomeDispositivo( result.getString( DispositivosName.nomeDispositivo ) );
                u.setIp( result.getString( DispositivosName.ip ) );
                u.setPorta( result.getInt( DispositivosName.porta ) );
                u.setServico( result.getString( DispositivosName.servico ) );
                u.setServelet( result.getString( DispositivosName.servelet ) );
            }
        }

        catch ( Exception e )
        {
            System.out.println( "Error in the method: getSource(id) - DispositivosService.java" );
        }
        
        return u;
    }
    
    public int getNextId() 
    {
        int id = 0;

        try {
            ResultSet result = connection.createStatement()
                    .executeQuery("SELECT nextval(pg_get_serial_sequence('" + DispositivosName.table_name + "', '"
                            + DispositivosName.idDispositivo + "')) as " + DispositivosName.idDispositivo);

            while (result.next()) {
                id = result.getInt(DispositivosName.idDispositivo);
            }
        } catch (Exception e) {
            System.out.println("Error in the method: getNextId() - DispositivosService.java");
        }

        return id;
    }
    
}

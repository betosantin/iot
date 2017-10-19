package Schema;

import Entity.Resultado;
import Utilities.ConnectionBD;
import Interface.DAO;
import TableName.HistoricosName;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Roberto Santin
 */
public class ResultadoService implements DAO
{
    private Connection connection = null;
    private static ResultadoService defaultInstance;

    public static ResultadoService getInstance() throws Exception
    {
        if (defaultInstance == null)
        {
            defaultInstance = new ResultadoService();
        }
        return defaultInstance;
    }

    public ResultadoService()
    {
        this.connection = ConnectionBD.getInstance().getConnection();
    }
    
    @Override
    public boolean insert( Object o )
    {
        Resultado u = (Resultado) o;
        
        if ( u == null )
        {
            return false;
        }
        
        try
        {
            connection.createStatement().executeUpdate("INSERT INTO " + HistoricosName.table_name + " "
                    + "(" + HistoricosName.sql + " ) "
                    + "VALUES (" + getNextId() + ",'" + u.getDispositivo() + "','" + u.getUsuario() + "','" + u.getNomeEvento()
                    + "','" + u.getDataToString() + "'," + u.getDirecao() + ",'" + u.getValue() + "' );");
            
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
        Resultado u = (Resultado) o;
        
        if ( u == null )
        {
            return false;
        }
        
        try
        {
            connection.createStatement().executeUpdate( "UPDATE " + HistoricosName.table_name + " "
                    + "SET " + HistoricosName.dispositivo + " = '" + u.getDispositivo() + "' , "
                    + HistoricosName.usuario + " = '" + u.getUsuario()+ "' , "
                    + HistoricosName.evento + " = '" + u.getNomeEvento() + "' , "
                    + HistoricosName.data + " = '" + u.getData() + "' , "
                    + HistoricosName.direcao + " = " + u.getDirecao() + " "
                    + HistoricosName.valor + " = '" + u.getValue() + "' "
                    + "WHERE " + HistoricosName.idhistorico + " = " + u.getIdhistorico() );
            
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
            connection.createStatement().executeUpdate( "DELETE FROM " + HistoricosName.table_name + " "
                    + "WHERE " + HistoricosName.idhistorico + " = " + id );
            
            return true;            
        }
        
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    @Override
    public ArrayList<Resultado> getAll()
    {
        ArrayList<Resultado> resultados = new ArrayList<>();

        try
        {
            ResultSet result = connection.createStatement()
                                      .executeQuery( "SELECT * FROM " + HistoricosName.table_name );
            
            while( result.next() )
            {
                Resultado u = new Resultado();
                
                u.setIdhistorico(result.getInt(HistoricosName.idhistorico));
                u.setDispositivo(result.getString(HistoricosName.dispositivo));
                u.setUsuario(result.getString(HistoricosName.usuario));
                u.setNomeEvento(result.getString(HistoricosName.evento));
                u.setData(result.getTimestamp(HistoricosName.data).getTime());
                u.setDirecao(result.getInt(HistoricosName.direcao));
                u.setValue(result.getString(HistoricosName.valor));

                resultados.add( u );
            }
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao consultar: getAll() - ResultadoService.java" );
        }
        
        return resultados;
    }

    @Override
    public Resultado getSource( Object o ) throws SQLException
    {
        Resultado u = (Resultado) o;
        
        if ( u == null )
        {
            new Exception( "This object is null in getSource of ResultadoService.java" );
        }
        
        return getSource( u.getIdhistorico());
    }

    @Override
    public Resultado getSource( int id )
    {
        Resultado u = null;

        try
        {
            ResultSet result = connection.createStatement()
                                      .executeQuery( "SELECT * FROM " + HistoricosName.table_name + 
                                                     " WHERE " + HistoricosName.idhistorico + " = " + id );
            
            while( result.next() )
            {
                u = new Resultado();
                
                u.setIdhistorico( result.getInt( HistoricosName.idhistorico ) );
                u.setDispositivo( result.getString( HistoricosName.dispositivo ) );
                u.setUsuario( result.getString( HistoricosName.usuario ) );
                u.setNomeEvento( result.getString( HistoricosName.evento ) );
                u.setData( result.getTimestamp(HistoricosName.data).getTime() );
                u.setDirecao( result.getInt( HistoricosName.direcao ) );
                u.setValue( result.getString( HistoricosName.valor ) );
            }
        }

        catch ( Exception e )
        {
            System.out.println( "Error in the method: getSource(id) - ResultadoService.java" );
        }
        
        return u;
    }
    
    public int getNextId() 
    {
        int id = 0;

        try {
            ResultSet result = connection.createStatement()
                    .executeQuery("SELECT nextval(pg_get_serial_sequence('" + HistoricosName.table_name + "', '"
                            + HistoricosName.idhistorico + "')) as " + HistoricosName.idhistorico);

            while (result.next()) {
                id = result.getInt(HistoricosName.idhistorico);
            }
        } catch (Exception e) {
            System.out.println("Error in the method: getNextId() - ResultadoService.java");
        }

        return id;
    }
    
}

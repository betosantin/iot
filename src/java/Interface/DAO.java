package Interface;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Roberto Santin
 */
public interface DAO {

    ArrayList<? extends Object> objs = new ArrayList<>();
    Object obj = new Object();

    public boolean insert(Object o);

    public boolean update(Object o);

    public boolean delete(int id);

    public ArrayList<? extends Object> getAll();

    public <T extends Object> T getSource( Object o ) throws SQLException;

    public <T extends Object> T getSource(int id);
    
//    public ArrayList<Object> consultar( String criterio );
   
}

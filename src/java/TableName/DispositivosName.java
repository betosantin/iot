package TableName;

/**
 *
 * @author Roberto Santin
 */
public class DispositivosName
{
    public static String table_name = "Dispositivos";
    
    public static String idDispositivo = "iddispositivo";
    public static String nomeDispositivo = "nomedispositivo";
    public static String ip = "ip";
    public static String porta = "porta";
    public static String servico = "servico";
    public static String servelet = "servelet";

    public static String sql = idDispositivo + ", " + nomeDispositivo + ", " + ip + ", " + porta + ", "+ 
                               servico + ", " + servelet;
}

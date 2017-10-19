/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import Entity.Resultado;
import Schema.ResultadoService;
import static TableName.HistoricosName.data;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Roberto Santin
 */
public class Teste {
   
    public static void main(String[] args) throws Exception {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println( sdf.format(cal.getTime()) );
        
        Date d = new Date(cal.getTimeInMillis());
        
        Resultado a = new Resultado();
        
        a.setDispositivo("dispostivo");
        a.setUsuario("usuario");
        a.setNomeEvento("evento");
        a.setData(d.getTime());
        a.setDirecao(Resultado.DIRECTION_SEND);
        a.setValue("valor");
        
        ResultadoService.getInstance().insert(a);
        
        System.out.println(ResultadoService.getInstance().getAll());
        
    }
    
    
}

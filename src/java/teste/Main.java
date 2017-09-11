/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import Entity.Metodos;
import Entity.Parametros;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author Roberto Santin
 */
public class Main {
    
    public static void main(String[] args) {
        
        //public void ligarLampada( RGB rgb, boolean blink, int time )
        
        Parametros p1 = new Parametros( "red", Parametros.INT );
        Parametros p2 = new Parametros( "green", Parametros.INT );
        Parametros p3 = new Parametros( "blue", Parametros.INT );
        Parametros p4 = new Parametros( "blink", Parametros.BOOLEAN );
        Parametros p5 = new Parametros( "time", Parametros.INT );

        List<Metodos> metodos = new ArrayList<Metodos>();
        Metodos m = new Metodos("ligarLampada", Metodos.VOID , Arrays.asList(p1, p2, p3, p4, p5) );
        Metodos m1 = new Metodos("obterTemperatura", Parametros.FLOAT , null );

        metodos.add(m);
        metodos.add(m1);
        
//        System.out.println(m);
        
//        XStream xstream = new XStream(new DomDriver());
//        String xml = xstream.toXML(m);
        
//        System.out.println(xml);
        
        Gson g = new Gson();
//        System.out.println(g.toJson(metodos));
//        g.toJson(m);
        
//        System.out.println(g.toJson(m));
        
        List<Metodos> m43 = g.fromJson(g.toJson(metodos), List.class );
//        Metodos m44 = g.fromJson(g.toJson(m1), Metodos.class);
        System.out.println(m43);
//        System.out.println(m44);
        
        
//        int i = 255 & 0xFF;
//        System.out.println("i2 = " + i);
        
    }
    
    
}

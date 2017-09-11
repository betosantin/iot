/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import Entity.Metodos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Roberto Santin
 */
public class Sincronize {
   
    public static void main(String[] args) {
        String urlString = "http://192.168.2.2:8081/Agente/acao?sincronizar";
     
       

        BufferedReader rd = null;
        
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(10000);
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            TypeToken<List<Metodos>> token = new TypeToken<List<Metodos>>() {
            };
            List<Metodos> personList = new Gson().fromJson(rd.readLine(), token.getType());

            rd.close();
            
            
            for (Metodos metodos : personList) {

                System.out.println(metodos);

            }

        } catch (IOException ex) {
            System.out.println("TIME OUT......");
            ex.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

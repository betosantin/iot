/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Roberto Santin
 */
public class Dispositivo {

    private int idDispositivo = 0;
    private String nomeDispositivo = "";
    private String ip = "";
    private int porta = 80;
    private String servico = "";
    private String servelet = "";

    public Dispositivo() {
    }
    
    public Dispositivo( int idDispositivo, String nomeDispositivo, String ip, String servico, String servelet ) {
        this.idDispositivo = idDispositivo;
        this.nomeDispositivo = nomeDispositivo;
        this.ip = ip;
        this.servico = servico;
        this.servelet = servelet;
    }

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getNomeDispositivo() {
        return nomeDispositivo;
    }

    public void setNomeDispositivo(String nomeDispositivo) {
        this.nomeDispositivo = nomeDispositivo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getServelet() {
        return servelet;
    }

    public void setServelet(String servelet) {
        this.servelet = servelet;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
    

    @Override
    public String toString() {
        return "http://" + ip + ":" + porta + "/" + servico + "/" + servelet + "?";
    }
}

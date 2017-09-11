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
public class Usuario
{
    public int idUsuario;
    public String nomeUsuario;
    public String loginUsuario;
    public String senhaUsuario;
    public int tipoUsuario;
    public String emailUsuario;
    public int statusUsuario;
    
    public static int TIPO_ADMINISTRADOR = 0;
    public static int TIPO_OPERADOR = 1;
    
    public static int STATUS_INATIVO = 0;
    public static int STATUS_ATIVO = 1;

    public Usuario()
    {
        idUsuario = 0;
        nomeUsuario = "";
        loginUsuario = "";
        senhaUsuario = "";
        tipoUsuario = 0;
        emailUsuario = "";
        statusUsuario = 1;
    }
    
    public int getIdUsuario()
    {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario)
    {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario()
    {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario)
    {
        this.nomeUsuario = nomeUsuario;
    }
    
    public String getLoginUsuario()
    {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario)
    {
        this.loginUsuario = loginUsuario;
    }

    public String getSenhaUsuario()
    {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario)
    {
        this.senhaUsuario = senhaUsuario;
    }

    public int getTipoUsuario()
    {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario)
    {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmailUsuario()
    {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario)
    {
        this.emailUsuario = emailUsuario;
    }

    public int getStatusUsuario()
    {
        return statusUsuario;
    }

    public void setStatusUsuario(int statusUsuario)
    {
        this.statusUsuario = statusUsuario;
    }
    
    public String getStatusString()
    {
        String ret = "N/D";
        
        if ( statusUsuario == STATUS_ATIVO )
        {
            ret = "Ativo";
        }
        else if ( statusUsuario == STATUS_INATIVO )
        {
            ret = "Inativo";
        }        
        
        return ret;
    }
    
    public String getTipoString()
    {
        String ret = "N/D";
        
        if ( tipoUsuario == TIPO_ADMINISTRADOR )
        {
            ret = "Administrador";
        }
        else if ( statusUsuario == TIPO_OPERADOR )
        {
            ret = "Operador";
        }        
        
        return ret;
    }
    
    @Override
    public String toString()
    {
        return nomeUsuario;
    }
    
}

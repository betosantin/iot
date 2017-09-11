/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teste;

import Entity.Usuario;
import Utilities.ConnectionBD;

/**
 *
 * @author Roberto Santin
 */
public class testeUser
{
    
    public static void main(String[] args)
    {
        Usuario u = new Usuario();
        u.setEmailUsuario("betosantin@gmail.com");
        u.setSenhaUsuario( ConnectionBD.getInstance().getUsuariosService().codificationPassWord( "123" ) );
        
        u.setIdUsuario(1);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.DataAccessObject;

import com.deLeon.ecommerceleon_v1.Model.Usuario;
import java.util.List;
import org.sql2o.Connection;
import util.Sql2oConnection;

/**
 * @author Dario
 * @author Luciano
 * @author Gaston
 */
public class UsuarioDAO {
    
    public List<Usuario> verificarPersona( Usuario u ) {
        
        List<Usuario> usuarios = null;
        String sql = "SELECT * FROM usuario WHERE nombre = :nombre and  contrasenia = :contrasenia";
     
        try (Connection con = Sql2oConnection.getSql2o().open()) {

            usuarios = con
                .createQuery(sql)
                .bind(u)
                .executeAndFetch(Usuario.class);
        }
        return usuarios;
    }
    
}

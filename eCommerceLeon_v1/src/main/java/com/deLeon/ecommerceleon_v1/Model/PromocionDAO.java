/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Model;

import java.util.List;
import lombok.Data;
import org.sql2o.Connection;
import util.Sql2oConnection;

/**
 *
 * @author Dario
 */
@Data
public class PromocionDAO {
     public List<Promocion> getAllOfertas(){
        String queryStatement = "SELECT * FROM PROMOCION;";
        List<Promocion> res = null;
        
        try (Connection con = Sql2oConnection.getSql2o().open()) {
            res = con.createQuery(queryStatement).executeAndFetch(Promocion.class);
        } catch(Exception e){
            System.out.println("Error en PromocionDAO-getAllOfertas");
        }
        return res;
    }
}

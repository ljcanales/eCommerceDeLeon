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
    public List<Promocion> getAllPromociones(){
        String queryStatement = "SELECT * FROM PROMOCION;";
        List<Promocion> res = null;
        
        try (Connection con = Sql2oConnection.getSql2o().open()) {
            res = con.createQuery(queryStatement).executeAndFetch(Promocion.class);
        } catch(Exception e){
            System.out.println("Error en PromocionDAO-getAllPromociones");
        }
        return res;
    }
    public void addPromocion(Promocion p){
         
         String queryStatement = "INSERT INTO PROMOCION SET ID_PROMO=:id_promo, NOMBRE=:nombre, DESCUENTO=:descuento, FECHADESDE=:fechadesde, FECHAHASTA=:fechahasta";
         
         try (Connection con = Sql2oConnection.getSql2o().open()) {       
            con.createQuery(queryStatement).bind(p).executeUpdate();
         } catch(Exception e){
            System.out.println("Error en PromocionDAO addPromocion()"+e.toString());
            }
    }  
}

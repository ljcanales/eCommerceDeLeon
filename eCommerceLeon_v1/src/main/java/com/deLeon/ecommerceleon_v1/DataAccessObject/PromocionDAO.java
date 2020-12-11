/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.DataAccessObject;

import com.deLeon.ecommerceleon_v1.Model.Promocion;
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
    public int addPromocion(Promocion p){
         
        String queryStatement = "INSERT INTO PROMOCION SET ID_PROMO=:id_promo, NOMBRE=:nombre, DESCUENTO=:descuento, FECHADESDE=:fechadesde, FECHAHASTA=:fechahasta";
        int key= -1;
        try (Connection con = Sql2oConnection.getSql2o().open()) {       
            key = con
                    .createQuery(queryStatement)
                    .bind(p)
                    .executeUpdate()
                    .getKey(int.class);
            return key;
        } catch(Exception e){
           System.out.println("Error en PromocionDAO addPromocion()"+e.toString());
        }
        return key;
    }  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.DataAccessObject;

import com.deLeon.ecommerceleon_v1.Model.PromocionesXproducto;
import java.util.List;
import lombok.Data;
import org.sql2o.Connection;
import util.Sql2oConnection;

/**
 *
 * @author Dario
 */
@Data
public class PromocionesXproductoDAO {

    public void addPromocionesXproductos(List<PromocionesXproducto> prodpromo) {
        
        for(int i=0; i<prodpromo.size();i++){
         String queryStatement = "INSERT INTO PROMOCIONESXPRODUCTO SET ID_PROMO=:id_promo, ID_PRODUCTO=:id_producto, CANTIDAD=:cantidad";
         
         try (Connection con = Sql2oConnection.getSql2o().open()) {       
            con.createQuery(queryStatement).bind(prodpromo.get(i)).executeUpdate();
         } catch(Exception e){
            System.out.println("Error en PromocionesXproductoDAO addPromocionesXproductos()"+e.toString());
            }
        }
    }  
}


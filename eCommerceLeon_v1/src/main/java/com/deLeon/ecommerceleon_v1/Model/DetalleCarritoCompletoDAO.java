/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Model;

import java.util.List;
import org.sql2o.Connection;
import util.Sql2oConnection;

/**
 *
 * @author Gaston
 */
public class DetalleCarritoCompletoDAO {
    public List<DetalleCarritoCompleto> getDetallesCarrito(int idCarrito){
        String queryStatement = "SELECT * FROM DETALLECARRITO WHERE ID_CARRITO = :idCarrito;";
        List<DetalleCarritoCompleto> res = null;
        
         try (Connection con = Sql2oConnection.getSql2o().open()) {
            res = con.createQuery(queryStatement)
                    .addParameter("idCarrito", idCarrito)
                    .executeAndFetch(DetalleCarritoCompleto.class);
        } catch(Exception e){
            System.out.println("Error en DetalleCarritoDAO-getDetallesCarrito()");
        }
    return res;    
   }
}

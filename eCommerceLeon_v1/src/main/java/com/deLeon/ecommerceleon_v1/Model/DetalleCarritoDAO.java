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
 * @author Luciano
 */
public class DetalleCarritoDAO {
    
    public boolean addDetalleCarrito(DetalleCarrito dc) {
        boolean r = false;
        String queryStatement = "INSERT INTO DETALLECARRITO SET ID_CARRITO=:id_carrito, ID_PRODUCTO=:id_producto, CANT=:cant ON DUPLICATE KEY UPDATE CANT = CANT + :cant";
        try (Connection con = Sql2oConnection.getSql2o().open()) {       
            con.createQuery(queryStatement).bind(dc).executeUpdate();
            r = true;
        } catch(Exception e){
                System.out.println("Error en DetalleCarritoDAO-addDetalleCarrito (1)"+e.toString());
        }
        
        return r;
    }
    
   public List<DetalleCarrito> getDetallesCarrito(int idCarrito){
        String queryStatement = "SELECT * FROM DETALLECARRITO WHERE ID_CARRITO = :id_carrito;";
        List<DetalleCarrito> res=null;
        
         try (Connection con = Sql2oConnection.getSql2o().open()) {
            res = con.createQuery(queryStatement)
                    .addParameter("id_carrito", idCarrito)
                    .executeAndFetch(DetalleCarrito.class);
        } catch(Exception e){
            System.out.println("Error en DetalleCarritoDAO-getDetallesCarrito()");
        }
    return res;    
   }
}

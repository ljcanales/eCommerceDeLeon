/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.DataAccessObject;

import com.deLeon.ecommerceleon_v1.Model.DetalleCarrito;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.Connection;
import util.Sql2oConnection;

/**
 * @author Dario
 * @author Luciano
 * @author Gaston
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
        String queryStatement = "SELECT producto.id_producto, nombre, cant, precio FROM DetalleCarrito JOIN Producto ON DetalleCarrito.id_producto = Producto.id_producto WHERE ID_CARRITO = :id_carrito;";
        List<DetalleCarrito> res= new ArrayList<DetalleCarrito>();
        
         try (Connection con = Sql2oConnection.getSql2o().open()) {
            res = con.createQuery(queryStatement)
                    .addParameter("id_carrito", idCarrito)
                    .executeAndFetch(DetalleCarrito.class);
        } catch(Exception e){
            System.out.println("Error en DetalleCarritoDAO-getDetallesCarrito()"+e.toString());
        }

    return res;    
   }
   
   public void delProduct(int idCarrito, int idProducto){
        String queryStatement = "DELETE FROM DetalleCarrito WHERE ID_CARRITO = :id_carrito and ID_PRODUCTO = :id_producto ;";
        
         try (Connection con = Sql2oConnection.getSql2o().open()) {
            con.createQuery(queryStatement)
               .addParameter("id_carrito", idCarrito)
               .addParameter("id_producto", idProducto)
               .executeUpdate();
        } catch(Exception e){
            System.out.println("Error en DetalleCarritoDAO-delProduct()"+e.toString());
        }   
   }
   
   public List<DetalleCarrito> getDetalleProduct(int idCarrito, int idProducto){
        List<DetalleCarrito> res= new ArrayList<DetalleCarrito>();
        String queryStatement = "SELECT * FROM DetalleCarrito WHERE ID_CARRITO = :id_carrito and ID_PRODUCTO = :id_producto ;";
        
         try (Connection con = Sql2oConnection.getSql2o().open()) {
            res = con.createQuery(queryStatement)
                     .addParameter("id_carrito", idCarrito)
                     .addParameter("id_producto", idProducto)
                     .executeAndFetch(DetalleCarrito.class);
        } catch(Exception e){
            System.out.println("Error en DetalleCarritoDAO-getDetalleProduct()"+e.toString());
        }  
        return res;
   }
  
   public void updateProduct(DetalleCarrito dc){
        String queryStatement = "UPDATE  DetalleCarrito SET id_carrito = :id_carrito, id_producto = :id_producto, cant = :cant WHERE id_carrito = :id_carrito AND id_producto = :id_producto";
        
         try (Connection con = Sql2oConnection.getSql2o().open()) {
            con.createQuery(queryStatement)
               .bind(dc)
               .executeUpdate();
        } catch(Exception e){
            System.out.println("Error en DetalleCarritoDAO-updateProduct()"+e.toString());
        }   
   }
}

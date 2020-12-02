/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Model;
import com.deLeon.ecommerceleon_v1.Model.*;
import java.util.HashMap;
import org.sql2o.Connection;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;
import util.Sql2oConnection;
/**
 *
 * @author Luciano
 */
public class DetalleCarritoDAO {
    
    public boolean addDetalleCarrito(DetalleCarrito dc) {
        boolean r = false;
        String queryStatement = "INSERT INTO CARRITO (ID_CARRITO, ID_PRODUCTO, CANT) VALUES (:id_carrito, :id_producto, :cant)";
        try (Connection con = Sql2oConnection.getSql2o().open()) {
            con.createQuery(queryStatement).bind(dc).executeUpdate();
            r = true;
        } catch(Exception e){
                System.out.println("Error en DetalleCarritoDAO-addDetalleCarrito (1)");
        }
        
        return r;
    }
    
}

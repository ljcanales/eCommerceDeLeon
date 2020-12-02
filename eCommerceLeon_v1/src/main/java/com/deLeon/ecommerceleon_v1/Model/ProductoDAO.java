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
public class ProductoDAO {
    public List<Producto> getAllProductos(){
        String queryStatement = "SELECT * FROM PRODUCTO;";
        List<Producto> res = null;
        
        try (Connection con = Sql2oConnection.getSql2o().open()) {
            res = con.createQuery(queryStatement).executeAndFetch(Producto.class);
        } catch(Exception e){
            System.out.println("Error en ProductoDAO-getAllProductos");
        }
        return res;
    }
    
    public String existsId(int id){
        String queryStatement = "SELECT * FROM PRODUCTO WHERE id = :id;";
        String res = "0";
        
        try (Connection con = Sql2oConnection.getSql2o().open()) {
            List<Producto> c = con
                    .createQuery(queryStatement)
                    .addParameter("id",id)
                    .executeAndFetch(Producto.class);
            if(c.size() > 0)
                res = "1";
        } catch(Exception e){
            System.out.println("Error en ProductoDAO-getAllProductos");
        }
        return res;
    }
}

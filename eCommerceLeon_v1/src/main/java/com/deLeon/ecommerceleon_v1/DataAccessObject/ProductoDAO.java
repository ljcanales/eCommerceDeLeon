/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.DataAccessObject;

import com.deLeon.ecommerceleon_v1.Model.Producto;
import java.util.List;
import org.sql2o.Connection;
import util.Sql2oConnection;

/**
 * @author Dario
 * @author Luciano
 * @author Gaston
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
        String queryStatement = "SELECT NOMBRE FROM PRODUCTO WHERE ID_PRODUCTO = :id;";
        String res = null;
        try (Connection con = Sql2oConnection.getSql2o().open()) {
            res = con
                    .createQuery(queryStatement)
                    .addParameter("id",id).executeAndFetchFirst(String.class);
        } catch(Exception e){
            System.out.println("Error en ProductoDAO-existsId"+e.toString());
        }
        if(res == null) 
            res = "";
        return res;
    }
}

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
public class CarritoDAO {
    public int getCarritoID(int id_cliente){
        String queryStatement = "SELECT * FROM CARRITO WHERE ID_CLIENTE = :id_cliente;";
        List<Carrito> res = null;
        int id_carrito = 0;
        
        //obtenemos los carritos asociados al id_cliente (deberia ser solo 1)
        try (Connection con = Sql2oConnection.getSql2o().open()) {
            res = con
                    .createQuery(queryStatement)
                    .addParameter("id_cliente", id_cliente)
                    .executeAndFetch(Carrito.class);
        } catch(Exception e){
            System.out.println("Error en CarritoDAO-getCarritoID (1)");
        }
        
        //si el cliente no tiene un carito asociado se le crea uno
        //y se conserva el id del carrito creado
        if(res.size() == 0){
            Carrito c = new Carrito();
            c.setId_cliente(id_cliente);
            queryStatement = "INSERT INTO CARRITO (ID_CLIENTE) VALUES (:id_cliente)";
            try (Connection con = Sql2oConnection.getSql2o().open()) {
            id_carrito = (int) con
                        .createQuery(queryStatement)
                        .bind(c)
                        .executeUpdate().getKey();
            } catch(Exception e){
                System.out.println("Error en CarritoDAO-getCarritoID (2)");
            }
        }
        //si el cliente tenia un carrito se obtiene el id del carrito
        else{
        id_carrito = res.get(0).getId_carrito();
        }
        return id_carrito;
    }
}

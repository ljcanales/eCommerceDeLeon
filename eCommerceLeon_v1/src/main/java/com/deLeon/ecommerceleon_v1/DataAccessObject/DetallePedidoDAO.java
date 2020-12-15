/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.DataAccessObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.sql2o.Connection;
import util.Sql2oConnection;

/**
 * @author Dario
 * @author Luciano
 * @author Gaston
 */
public class DetallePedidoDAO {
    
    public List<Map<String,Object>> getDetallesPedido(int idPedido) {
        String queryStatement = "SELECT producto.id_producto, nombre, cantidad, DetallePedido.precio FROM DetallePedido JOIN Producto ON DetallePedido.id_producto = Producto.id_producto WHERE ID_PEDIDO = :id_pedido;";
        List<Map<String,Object>> res = new ArrayList<Map<String,Object>>();
        
        try (Connection con = Sql2oConnection.getSql2o().open()) {
            res = con.createQuery(queryStatement)
                    .addParameter("id_pedido", idPedido)
                    .executeAndFetchTable()
                    .asList();
        } catch(Exception e) {
            System.out.println("Error en DetallePedidoDAO-getDetallesPedido()" + e.toString());
        }
        return res;
    }
}

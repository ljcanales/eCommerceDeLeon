/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.DataAccessObject;
import com.deLeon.ecommerceleon_v1.Model.Pedido;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.Connection;
import util.Sql2oConnection;

/**
 * @author Dario
 * @author Luciano
 * @author Gaston
 */
public class PedidoDAO {
    
    public List<Pedido> getPedidos(int idCliente) {
        String queryStatement = "SELECT * FROM PEDIDO WHERE ID_cliente = :id_cliente;";
        List<Pedido> res= new ArrayList<Pedido>();
        
        try (Connection con = Sql2oConnection.getSql2o().open()) {
            res = con.createQuery(queryStatement)
                    .addParameter("id_cliente", idCliente)
                    .executeAndFetch(Pedido.class);
        } catch(Exception e) {
            System.out.println("Error en PedidoDAO-getPeidos()" + e.toString());
        }
        return res;
    }
    
    public int addPedido(Pedido p){
        String sql = "INSERT INTO PEDIDO (id_pedido, id_cliente, fecha_pedido, fecha_envio, total, estado) VALUES (:id_pedido, :id_cliente, CURRENT_DATE, :fecha_envio, :total, :estado)";
        int id_pedido = 0;
        try (Connection con = Sql2oConnection.getSql2o().open()) {
            id_pedido = (int) con.createQuery(sql)
                                 .bind(p)
                                 .executeUpdate()
                                 .getKey(int.class);
        } catch(Exception e) {
            System.out.println("Error en PedidoDAO-addPedido()" + e.toString());
        }
        return id_pedido;
    }
}

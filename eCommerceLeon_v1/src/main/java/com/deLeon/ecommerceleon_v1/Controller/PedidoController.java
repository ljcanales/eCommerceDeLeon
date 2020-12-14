/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Controller;
import com.deLeon.ecommerceleon_v1.DataAccessObject.*;
import com.deLeon.ecommerceleon_v1.Model.*;
import java.util.HashMap;
import spark.Request;
import spark.Response;
import spark.Route;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

/**
 * @author Dario
 * @author Luciano
 * @author Gaston
 */
public class PedidoController {
    public static Route 
        getPedidos = (Request request, Response res) -> {
            PedidoDAO pDAO = new PedidoDAO();
            List <Pedido> pedidos = pDAO.getPedidos(request.session().attribute("user_id"));
            
            for(Pedido p: pedidos)
                System.out.println("Pedido: " +p.getTotal());
            
            HashMap model = new HashMap(); 
            model.put("pedidos", pedidos);
            model.put("userid", request.session().attribute("user_id"));
            model.put("Template", "templates/listaPedidos.vsl");
            model.put("username", request.session().attribute("username"));
            
            return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/userLayout.vsl")); 
        };
    
    public static Route 
        getDetallesPedido = (Request request, Response res) -> {
            DetallePedidoDAO dpDAO = new DetallePedidoDAO();
            int idPedido = Integer.parseInt(request.queryParams("id"));
            List<Map<String,Object>> dp = dpDAO.getDetallesPedido(idPedido, request.session().attribute("user_id"));
            
            HashMap model = new HashMap(); 
            model.put("detalles", dp);
            model.put("userid", request.session().attribute("user_id"));
            model.put("Template", "templates/listaDetallesPedidos.vsl");
            model.put("username", request.session().attribute("username"));
            
            return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/userLayout.vsl"));
        };
}

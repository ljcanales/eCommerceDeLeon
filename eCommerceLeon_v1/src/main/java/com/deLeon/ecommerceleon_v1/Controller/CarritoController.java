/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Controller;

import com.deLeon.ecommerceleon_v1.Model.*;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

/**
 *
 * @author Luciano
 */
public class CarritoController {
    public static Route addProducto = (Request request, Response response) -> {
        return null;
    };
   
    public static Route getCarritoID = (Request request, Response response) -> {
        CarritoDAO cDAO = new CarritoDAO();
        int id_carrito = cDAO.getCarritoID(1);
        
        HashMap model = new HashMap();
        model.put("id_carrito", id_carrito);
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/borrarTemplate.vsl")); 
    };
    

}

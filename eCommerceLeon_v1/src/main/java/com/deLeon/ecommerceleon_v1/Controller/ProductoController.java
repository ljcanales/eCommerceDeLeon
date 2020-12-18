/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Controller;

import com.deLeon.ecommerceleon_v1.Model.Producto;
import com.deLeon.ecommerceleon_v1.DataAccessObject.ProductoDAO;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

/**
 * @author Dario
 * @author Luciano
 * @author Gaston
 */
public class ProductoController {
        public static Route getProductos = (Request request, Response response) -> {
        ProductoDAO pDAO = new ProductoDAO();
        List<Producto> res = pDAO.getAllProductos();
        
        HashMap model = new HashMap(); 
        model.put("productos", res);
        model.put("Template", "templates/listaProductos.vsl");
        model.put("username", request.session().attribute("username"));
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/userLayout.vsl")); 
    };
    
    public static Route checkId = (Request request, Response response) -> {
        Integer id = Integer.valueOf(request.queryParams("id"));
        ProductoDAO pDAO = new ProductoDAO();
        return pDAO.existsId(id);
    };
    
    public static Route appgetProductos = (Request request, Response response) -> {
        ProductoDAO pDAO = new ProductoDAO();
        List<Producto> res = pDAO.getAllProductos();
        
        HashMap model = new HashMap(); 
        model.put("productos", res);
        //model.put("Template", "templates/listaProductos.vsl");
        //model.put("username", request.session().attribute("username"));
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/app/appListaProductos.vsl")); 
    };
}

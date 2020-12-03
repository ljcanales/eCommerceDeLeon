/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Controller;

import com.deLeon.ecommerceleon_v1.Model.Producto;
import com.deLeon.ecommerceleon_v1.Model.ProductoDAO;
import com.deLeon.ecommerceleon_v1.Model.Promocion;
import com.deLeon.ecommerceleon_v1.Model.PromocionDAO;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

/**
 *
 * @author Dario
 */
public class PromocionController {
    public static Route getOfertas = (Request request, Response response) -> {
        PromocionDAO pDAO = new PromocionDAO();
        List<Promocion> res = pDAO.getAllOfertas();
        
        HashMap model = new HashMap();
        model.put("promociones", res);
        model.put("TemplateOfertas", "templates/listaOfertas.vsl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/userLayout.vsl")); 
    };
}

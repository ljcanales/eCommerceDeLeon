/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Controller;

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
public class IndexController {
    public static Route getIndex = (Request request, Response response) -> {
        //REDIRECCIONO A LA PAGINA INDEX.HTML - De prueba
        response.redirect("index.html");
        return null;
    };
    public static Route admin = (Request request, Response response) -> {

        HashMap model = new HashMap();
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/adminLayout.vsl")); 
    };
}

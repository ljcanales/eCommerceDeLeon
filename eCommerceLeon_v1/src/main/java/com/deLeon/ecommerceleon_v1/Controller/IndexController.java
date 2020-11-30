/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Controller;

import spark.Request;
import spark.Response;
import spark.Route;

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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Controller;

import com.deLeon.ecommerceleon_v1.Model.Producto;
import com.deLeon.ecommerceleon_v1.Model.ProductoDAO;
import java.util.List;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author Luciano
 */
public class ProductoController {
    public static Route getProductos = (Request request, Response response) -> {
        ProductoDAO pDAO = new ProductoDAO();
        List<Producto> res = pDAO.getAllProductos();
        
        return res;
    };
    
    public static Route checkId = (Request request, Response response) -> {
        Integer id = Integer.valueOf(request.queryParams("id"));
        ProductoDAO pDAO = new ProductoDAO();
        return pDAO.existsId(id);
    };
}

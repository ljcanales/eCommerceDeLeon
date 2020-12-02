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
        String id_producto = request.queryParams("id_producto");
        String cant = request.queryParams("cant");
        
        //poner if verificar si ninguno de los parametros en null
        
        CarritoDAO cDAO = new CarritoDAO();
        DetalleCarritoDAO dcDAO = new DetalleCarritoDAO();
        DetalleCarrito dc = new DetalleCarrito();
        
        int cantidad = Integer.parseInt(cant);
        int idcarrito = cDAO.getCarritoID(1); //cambiar 1 por el id del usuario
        int idproducto = Integer.parseInt(id_producto);
        
        System.out.println("cant: " +cantidad+ " idCarrito: " +idcarrito+ " idProducto: " +idproducto);
        
        dc.setCant(cantidad);
        dc.setId_carrito(idcarrito);
        dc.setId_producto(idproducto);
        
        dcDAO.addDetalleCarrito(dc);
        
        ////////////////////////////////////////////////////////////////////////
        
        List<DetalleCarrito> carrito = dcDAO.getDetallesCarrito(idcarrito);
        
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

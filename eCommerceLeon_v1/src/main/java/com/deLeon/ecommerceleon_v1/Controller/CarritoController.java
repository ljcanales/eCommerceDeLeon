/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Controller;

import com.deLeon.ecommerceleon_v1.DataAccessObject.DetalleCarritoDAO;
import com.deLeon.ecommerceleon_v1.DataAccessObject.CarritoDAO;
import com.deLeon.ecommerceleon_v1.Model.*;
import java.util.HashMap;
import java.util.List;
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
    
        dc.setCant(cantidad);
        dc.setId_carrito(idcarrito);
        dc.setId_producto(idproducto);
        
        dcDAO.addDetalleCarrito(dc);
        
        ////////////////////////////////////////////////////////////////////////
        
        List<DetalleCarrito> carrito = dcDAO.getDetallesCarrito(idcarrito);
        double total = 0;
        for(DetalleCarrito d : carrito){
            total+= d.getCant() * d.getPrecio();
        }
        
        HashMap model = new HashMap();
        model.put("detalles_carrito", carrito);
        model.put("total", total);
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/listaCarrito.vsl")); 
    };
    
    public static Route updateCarrito = (Request request, Response response) -> {
        
        CarritoDAO cDAO = new CarritoDAO();
        DetalleCarritoDAO dcDAO = new DetalleCarritoDAO();

        int idcarrito = cDAO.getCarritoID(1); //cambiar 1 por el id del usuario
        
        List<DetalleCarrito> carrito = dcDAO.getDetallesCarrito(idcarrito);
        
        double total = 0;
        for(DetalleCarrito d : carrito){
            total+= d.getCant() * d.getPrecio();
        }
        
        HashMap model = new HashMap();
        model.put("detalles_carrito", carrito);
        model.put("total", total);
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/listaCarrito.vsl")); 
    };
   
    public static Route getCarritoID = (Request request, Response response) -> {
        CarritoDAO cDAO = new CarritoDAO();
        int id_carrito = cDAO.getCarritoID(1);
        
        HashMap model = new HashMap();
        model.put("id_carrito", id_carrito);
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/borrarTemplate.vsl")); 
    };
}

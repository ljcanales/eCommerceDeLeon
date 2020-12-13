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
 * @author Dario
 * @author Luciano
 * @author Gaston
 */
public class CarritoController {

    public static Route addProducto = (Request request, Response response) -> {
        DetalleCarritoDAO dcDAO = new DetalleCarritoDAO();
        DetalleCarrito dc = new DetalleCarrito();
        
        int idcarrito = request.session().attribute("cart_id"); //obtengo id_carrito de cookie
        int cantidad = Integer.parseInt(request.queryParams("cant"));
        int idproducto = Integer.parseInt(request.queryParams("id_producto"));
    
        dc.setCant(cantidad);
        dc.setId_carrito(idcarrito);
        dc.setId_producto(idproducto);
        
        dcDAO.addDetalleCarrito(dc);
        
        //devuelvo el carrito, esto deberiamos sacarloo y solo usar el updateCarrtio en js
        
        List<DetalleCarrito> carrito = dcDAO.getDetallesCarrito(idcarrito);
        double total = 0;
        for(DetalleCarrito d : carrito){
            total+= d.getCant() * d.getPrecio();
        }
        
        HashMap model = new HashMap();
        model.put("id_carrito", idcarrito);
        model.put("detalles_carrito", carrito);
        model.put("total", total);
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/listaCarrito.vsl")); 
    };//FIN addProducto()   
    
    public static Route updateCarrito = (Request request, Response response) -> {
        CarritoDAO cDAO = new CarritoDAO();
        DetalleCarritoDAO dcDAO = new DetalleCarritoDAO();
        
        int idcarrito = request.session().attribute("cart_id");

        List<DetalleCarrito> carrito = dcDAO.getDetallesCarrito(idcarrito);
        
        double total = 0;
        for(DetalleCarrito d : carrito){
            total+= d.getCant() * d.getPrecio();
        }
        
        HashMap model = new HashMap();
        model.put("id_carrito", idcarrito);
        model.put("detalles_carrito", carrito);
        model.put("total", total);
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/listaCarrito.vsl")); 
    };//FIN updateCarrito()
   
    public static Route getCarritoID = (Request request, Response response) -> {
        CarritoDAO cDAO = new CarritoDAO();
        int id_carrito = request.session().attribute("cart_id");
        
        HashMap model = new HashMap();
        model.put("id_carrito", id_carrito);
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/borrarTemplate.vsl")); 
    };//FIN getCarritoID()
    
    public static Route delProducto = (Request request, Response response) -> {
        CarritoDAO cDAO = new CarritoDAO();
        DetalleCarritoDAO dcDAO = new DetalleCarritoDAO();
        
        int id_producto = Integer.parseInt(request.queryParams("id_producto"));
        int id_carrito = request.session().attribute("cart_id");
        
        System.out.println("IDcarrito: "+id_carrito+ " IDproducto: "+id_producto);
        
        dcDAO.delProduct(id_carrito, id_producto);

        return null; 
    };//FIN delProducto()
    
    public static Route updateCant = (Request request, Response response) -> {
        CarritoDAO cDAO = new CarritoDAO();
        
        DetalleCarritoDAO dcDAO = new DetalleCarritoDAO();
        
        String op = request.queryParams("op");
        int id_producto = Integer.parseInt(request.queryParams("id_producto"));
        int id_carrito = request.session().attribute("cart_id");
        
        List<DetalleCarrito> dc = dcDAO.getDetalleProduct(id_carrito, id_producto);
        DetalleCarrito p = dc.get(0);
        
        int cant = p.getCant();
        if(op.equals("increase")){
            cant++;
            p.setCant(cant);
            dcDAO.updateProduct(p);
        }
       
        if(op.equals("decrease")){
            cant--;
            if(cant>0){
                p.setCant(cant);
                dcDAO.updateProduct(p);
            }
            else{
                dcDAO.delProduct(id_carrito, id_producto);
            }
        }
        return null; 
    }; //FIN updateCant()
}


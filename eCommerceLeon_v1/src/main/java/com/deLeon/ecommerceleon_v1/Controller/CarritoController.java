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
        String id_producto = request.queryParams("id_producto");
        String cant = request.queryParams("cant");
        
        //poner if verificar si ninguno de los parametros en null
        
        CarritoDAO cDAO = new CarritoDAO();
        DetalleCarritoDAO dcDAO = new DetalleCarritoDAO();
        DetalleCarrito dc = new DetalleCarrito();
        
        int cantidad = Integer.parseInt(cant);
        int idcarrito = cDAO.getCarritoID(request.session().attribute("id")); 
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
        model.put("id_carrito", idcarrito);
        model.put("detalles_carrito", carrito);
        model.put("total", total);
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/listaCarrito.vsl")); 
    };
    
    public static Route updateCarrito = (Request request, Response response) -> {
        System.out.println("Start Update");
        CarritoDAO cDAO = new CarritoDAO();
        DetalleCarritoDAO dcDAO = new DetalleCarritoDAO();
        
        int idcarrito = cDAO.getCarritoID(request.session().attribute("id")); 

        List<DetalleCarrito> carrito = dcDAO.getDetallesCarrito(idcarrito);
        
        double total = 0;
        for(DetalleCarrito d : carrito){
            total+= d.getCant() * d.getPrecio();
            System.out.println(d.getNombre()+" - "+d.getCant());
        }
        
        HashMap model = new HashMap();
        model.put("id_carrito", idcarrito);
        model.put("detalles_carrito", carrito);
        model.put("total", total);
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/listaCarrito.vsl")); 
    };
   
    public static Route getCarritoID = (Request request, Response response) -> {
        CarritoDAO cDAO = new CarritoDAO();
        int id_carrito = cDAO.getCarritoID(request.session().attribute("id")); 
        
        HashMap model = new HashMap();
        model.put("id_carrito", id_carrito);
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/borrarTemplate.vsl")); 
    };
    
    public static Route delProducto = (Request request, Response response) -> {
        CarritoDAO cDAO = new CarritoDAO();
        DetalleCarritoDAO dcDAO = new DetalleCarritoDAO();
        
        int id_producto = Integer.parseInt(request.queryParams("id_producto"));
        int id_carrito = Integer.parseInt(request.queryParams("id_carrito"));
        
        System.out.println("IDcarrito: "+id_carrito+ " IDproducto: "+id_producto);
        
        dcDAO.delProduct(id_carrito, id_producto);

        return null; 
    };
    
    public static Route updateCant = (Request request, Response response) -> {
        CarritoDAO cDAO = new CarritoDAO();
        
        DetalleCarritoDAO dcDAO = new DetalleCarritoDAO();
        
        String op = request.queryParams("op");
        int id_producto = Integer.parseInt(request.queryParams("id_producto"));
        int id_carrito = cDAO.getCarritoID(request.session().attribute("id")); 
        
        List<DetalleCarrito> dc = dcDAO.getDetalleProduct(id_carrito, id_producto);
        DetalleCarrito p = dc.get(0);
        
        System.out.println("OP: "+op);
        
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
    };
}


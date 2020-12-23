/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Controller;

import com.deLeon.ecommerceleon_v1.DataAccessObject.DetalleCarritoDAO;
import com.deLeon.ecommerceleon_v1.DataAccessObject.CarritoDAO;
import com.deLeon.ecommerceleon_v1.DataAccessObject.DetallePedidoDAO;
import com.deLeon.ecommerceleon_v1.DataAccessObject.PedidoDAO;
import com.deLeon.ecommerceleon_v1.Model.*;
import java.util.ArrayList;
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

    public static Route 
        addProducto = (Request request, Response response) -> {
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
    
    public static Route 
        updateCarrito = (Request request, Response response) -> {
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
   
    public static Route 
        getCarritoID = (Request request, Response response) -> {
            CarritoDAO cDAO = new CarritoDAO();
            int id_carrito = request.session().attribute("cart_id");

            HashMap model = new HashMap();
            model.put("id_carrito", id_carrito);
            return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/borrarTemplate.vsl")); 
        };//FIN getCarritoID()
    
    public static Route 
        delProducto = (Request request, Response response) -> {
            CarritoDAO cDAO = new CarritoDAO();
            DetalleCarritoDAO dcDAO = new DetalleCarritoDAO();

            int id_producto = Integer.parseInt(request.queryParams("id_producto"));
            int id_carrito = request.session().attribute("cart_id");

            System.out.println("IDcarrito: "+id_carrito+ " IDproducto: "+id_producto);

            dcDAO.delProduct(id_carrito, id_producto);

            return null; 
        };//FIN delProducto()
    
    public static Route 
        updateCant = (Request request, Response response) -> {

            DetalleCarritoDAO dcDAO = new DetalleCarritoDAO();

            String op = request.queryParams("op");
            int id_producto = Integer.parseInt(request.queryParams("id_producto"));
            int id_carrito = request.session().attribute("cart_id");

            DetalleCarrito dc = dcDAO.getDetalleProduct(id_carrito, id_producto);

            int cant = dc.getCant();
            if(op.equals("increase")){
                cant++;
                dc.setCant(cant);
                dcDAO.updateProduct(dc);
            }

            if(op.equals("decrease")){
                cant--;
                if(cant>0){
                    dc.setCant(cant);
                    dcDAO.updateProduct(dc);
                }
                else{
                    dcDAO.delProduct(id_carrito, id_producto);
                }
            }
            return null; 
        }; //FIN updateCant()
    
    public static Route 
        comprarCarrito = (Request request, Response response) -> {

            int id_carrito = request.session().attribute("cart_id");

            DetalleCarritoDAO dcDAO = new DetalleCarritoDAO();
            List<DetalleCarrito> dc = dcDAO.getDetallesCarrito(id_carrito);
            
            List<DetallePedido> dp = new ArrayList<DetallePedido>();
            
            double total = 0;
            for(DetalleCarrito k : dc) {
                DetallePedido d = new DetallePedido();
                d.setId_producto(k.getId_producto());
                d.setCantidad(k.getCant());
                d.setPrecio(k.getPrecio());
                
                dp.add(d);
                total += k.getCant() * k.getPrecio();
            }
            
            Pedido pedido = new Pedido();
            pedido.setId_cliente(request.session().attribute("user_id"));
            pedido.setTotal(total);
            pedido.setEstado("guardado");

            int id_pedido = new PedidoDAO().addPedido(pedido);
            
            DetallePedidoDAO dpDAO = new DetallePedidoDAO();
            
            dp.stream().forEach(i -> {
                i.setId_pedido(id_pedido);
                dpDAO.addDetallePedido(i);
            });
            
            dcDAO.cleanCart(id_carrito);
            
            return ""; 
        }; //FIN comprarCarrito()
    
    // APP
    public static Route 
        appverCarrito = (Request request, Response response) -> {
            CarritoDAO cDAO = new CarritoDAO();
            DetalleCarritoDAO dcDAO = new DetalleCarritoDAO();

            int idcarrito = Integer.parseInt(request.queryParams("cart_id"));

            List<DetalleCarrito> carrito = dcDAO.getDetallesCarrito(idcarrito);

            double total = 0;
            for(DetalleCarrito d : carrito){
                total+= d.getCant() * d.getPrecio();
            }

            HashMap model = new HashMap();
            model.put("id_carrito", idcarrito);
            model.put("detalles_carrito", carrito);
            model.put("total", total);
            return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/app/appListaCarrito.vsl")); 
        };
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1;

import com.deLeon.ecommerceleon_v1.Controller.*;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

/**
 * @author Dario
 * @author Luciano
 * @author Gaston
 */
public class Main {
    public static void main(String[] args) { 

        staticFiles.location("/public");
        
        get("/",IndexController.getIndex);
        
        //LOGIN
        get("/login", UsuarioController.getLogin); 
        post("/login", UsuarioController.getLogin);
        get("/logout", UsuarioController.Logout);
                
        //CU AGREGAR A CARRITO
            //PAGINA PARA INICIAR CU
            get("/getProductos",ProductoController.getProductos);   
            //PAGINAS DEL CU
            get("/getCarritoID",CarritoController.getCarritoID); 
            get("/addProducto",CarritoController.addProducto); 
            get("/updateCarrito",CarritoController.updateCarrito);
            //CU MODIFICAR CANTIDAD
            get("/delProducto",CarritoController.delProducto);
            get("/updateCant",CarritoController.updateCant); 
        
        //CU AGREGAR PROMOCION
            //PAGINA PARA INICIAR CU
            get("/admin",IndexController.admin);       
             //PAGINAS DEL CU
            post("/addPromocion",PromocionController.addPromocion);
            get("/adminAddPromo",IndexController.adminAddPromo);  
            get("/checkId",ProductoController.checkId);
        
        //CU VER PEDIDOS
            get("/getPedidos",PedidoController.getPedidos);  
            get("/getDetallesPedido",PedidoController.getDetallesPedido);
        
    }
}

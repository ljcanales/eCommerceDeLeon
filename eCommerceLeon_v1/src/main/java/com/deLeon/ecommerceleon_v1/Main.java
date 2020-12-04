/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1;

import com.deLeon.ecommerceleon_v1.Controller.*;
import static spark.Spark.staticFiles;
import static spark.Spark.get;

/**
 *
 * @author Luciano
 */
public class Main {
    public static void main(String[] args) { 

        staticFiles.location("/public");
        
        get("/",IndexController.getIndex);
                
        get("/getProductos",ProductoController.getProductos); 
        get("/getCarritoID",CarritoController.getCarritoID); 
        get("/addProducto",CarritoController.addProducto); 
        get("/updateCarrito",CarritoController.updateCarrito);
        get("/addPromocion",PromocionController.addPromocion);
        
        get("/admin",IndexController.admin); 
        get("/adminAddPromo",IndexController.adminAddPromo); 
        
        get("/checkId",ProductoController.checkId);
    }
}

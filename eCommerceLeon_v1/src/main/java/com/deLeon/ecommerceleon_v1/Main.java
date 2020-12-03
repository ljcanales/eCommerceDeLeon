/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1;

import com.deLeon.ecommerceleon_v1.Controller.*;
import static spark.Spark.staticFiles;
import static spark.Spark.get;
import util.Path;

/**
 *
 * @author Luciano
 */
public class Main {
    public static void main(String[] args) { 
        /*
        staticFiles.location(Path.Web.PUBLIC_FOLDER);
        get(Path.Web.INDEX,IndexController.getIndex);
        */
        staticFiles.location("/public");
                
        get("/getProductos",ProductoController.getProductos); 
        get("/getCarritoID",CarritoController.getCarritoID); 
        get("/addProducto",CarritoController.addProducto); 
        get("/updateCarrito",CarritoController.updateCarrito); 
        
        get("/admin",IndexController.admin); 
        get("/getForm",IndexController.getForm);
    }
}

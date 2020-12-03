/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Controller;

import com.deLeon.ecommerceleon_v1.Model.Promocion;
import com.deLeon.ecommerceleon_v1.Model.PromocionDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

/**
 *
 * @author Dario
 */
public class PromocionController {
    public static Route getOfertas = (Request request, Response response) -> {
        PromocionDAO pDAO = new PromocionDAO();
        List<Promocion> res = pDAO.getAllOfertas();
        
        HashMap model = new HashMap();
        model.put("promociones", res);
        model.put("TemplateOfertas", "templates/listaOfertas.vsl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/userLayout.vsl")); 
    };
    public static Route addOferta = (Request request, Response response) -> {
        Promocion p = new Promocion();
            p.setNombre(request.queryParams("nombre"));
            p.setDescuento(Double.parseDouble(request.queryParams("descuento")));
            
            SimpleDateFormat simple= new SimpleDateFormat("dd-MMM-yyyy");
            Date fechadesde = simple.parse(request.queryParams("fechadesde"));
            p.setFechadesde(fechadesde);
           
            Date fechahasta = simple.parse(request.queryParams("fechahasta"));
            p.setFechahasta(fechahasta);
        
        PromocionDAO pDAO = new PromocionDAO();
            pDAO.update(p);
        
        //ProductoDAO prodDAO = new ProductoDAO();
       
        HashMap model = new HashMap();
        model.put("promociones", pDAO);
        model.put("TemplateOfertas", "templates/listaOfertas.vsl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/userLayout.vsl")); 
    };
}

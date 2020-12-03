/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Controller;

import com.deLeon.ecommerceleon_v1.Model.Promocion;
import com.deLeon.ecommerceleon_v1.Model.PromocionDAO;
import com.deLeon.ecommerceleon_v1.Model.PromocionesXproducto;
import com.deLeon.ecommerceleon_v1.Model.PromocionesXproductoDAO;
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
    public static Route getPromocion = (Request request, Response response) -> {
        PromocionDAO pDAO = new PromocionDAO();
        List<Promocion> res = pDAO.getAllPromociones();
        
        HashMap model = new HashMap();
        model.put("promociones", res);
        model.put("TemplateOfertas", "templates/listaOfertas.vsl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/userLayout.vsl")); 
    };
    public static Route addPromocion = (Request request, Response response) -> {
        PromocionDAO pDAO = new PromocionDAO();
        
        // CREAR LA PROMOCION 
        Promocion p = new Promocion();
            p.setNombre(request.queryParams("nombre"));
            p.setDescuento(Double.parseDouble(request.queryParams("descuento")));
            
            SimpleDateFormat simple= new SimpleDateFormat("dd-MMM-yyyy");
            Date fechadesde = simple.parse(request.queryParams("fechadesde"));
            p.setFechadesde(fechadesde);
           
            Date fechahasta = simple.parse(request.queryParams("fechahasta"));
            p.setFechahasta(fechahasta);
        pDAO.addPromocion(p);
        // AGREGAR LOS PRODUCTOS A LA PROMOCION
        PromocionesXproductoDAO pXp = new PromocionesXproductoDAO();
        
            int cant =Integer.parseInt(request.queryParams("cantidad"));
            List<PromocionesXproducto> prodpromo = null;
            for(int i=0;i<cant;i++){
                PromocionesXproducto pp= new PromocionesXproducto();
                // asignar atributos a promocionesXproductos
                pp.setId_promo(Integer.parseInt(request.queryParams("id_promo")));
                pp.setId_producto(Integer.parseInt(request.queryParams("id_producto")));
                pp.setId_producto(Integer.parseInt(request.queryParams("cantidad")));
       
                prodpromo.add(pp);
            }
            pXp.addPromocionesXproductos(prodpromo);
       
        HashMap model = new HashMap();
        model.put("promociones", p);
        model.put("TemplateOfertas", "templates/listaOfertas.vsl");
        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/userLayout.vsl")); 
    };
}

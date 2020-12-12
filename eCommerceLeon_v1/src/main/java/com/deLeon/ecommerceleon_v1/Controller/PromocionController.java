/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Controller;

import com.deLeon.ecommerceleon_v1.Model.Promocion;
import com.deLeon.ecommerceleon_v1.DataAccessObject.PromocionDAO;
import com.deLeon.ecommerceleon_v1.Model.PromocionesXproducto;
import com.deLeon.ecommerceleon_v1.DataAccessObject.PromocionesXproductoDAO;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;
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
    public static Route addPromocion = (Request request, Response response) -> {
        
        Map<String,String> promoData = new HashMap<>();
        List<Map<String,String>> productos = new ArrayList<>();
        
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            promoData = mapper.readValue(request.queryParams("promodata"), HashMap.class);
            productos = Arrays.asList(mapper.readValue(request.queryParams("productos"), HashMap[].class));
        } catch(Exception e){
            System.out.println(e.toString());
        }

        // CREAR LA PROMOCION 
        Promocion p = new Promocion();
        p.setNombre(promoData.get("nombre"));
        p.setDescuento(Double.parseDouble(promoData.get("descuento")));

        SimpleDateFormat simple= new SimpleDateFormat("yyyy-MM-dd");
        Date fechadesde = simple.parse(promoData.get("fechadesde"));
        p.setFechadesde(fechadesde);

        Date fechahasta = simple.parse(promoData.get("fechahasta"));
        p.setFechahasta(fechahasta);
        
        // ACTUALIZACION PROMOCION
        PromocionDAO pDAO = new PromocionDAO();
        int id_promo = pDAO.addPromocion(p);
        
        // AGREGAR LOS PRODUCTOS A LA PROMOCION
        ArrayList<PromocionesXproducto> pxp = new ArrayList<PromocionesXproducto>();
        
        productos.stream().forEach(k -> {
            PromocionesXproducto pp = new PromocionesXproducto();
            pp.setId_promo(id_promo);
            pp.setId_producto(Integer.valueOf(k.get("id")));
            pp.setCantidad(Integer.valueOf(k.get("cantidad")));
            pxp.add(pp);
        });

        // ACTUALIZACION PROMOCIONESXPRODUCTO
        PromocionesXproductoDAO pxpDAO = new PromocionesXproductoDAO();
        pxpDAO.addPromocionesXproductos(pxp);

        return "ok";
    };
}

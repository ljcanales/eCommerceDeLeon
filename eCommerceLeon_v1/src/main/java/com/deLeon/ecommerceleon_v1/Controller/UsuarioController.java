/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Controller;

import spark.Request;
import spark.Response;
import spark.Route;
import java.util.List;

import java.util.ArrayList;
import java.util.*;

import java.util.HashMap; 
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import com.deLeon.ecommerceleon_v1.Model.Usuario;
import com.deLeon.ecommerceleon_v1.DataAccessObject.UsuarioDAO;

/**
 * @author Dario
 * @author Luciano
 * @author Gaston
 */
public class UsuarioController {
    public static Route 
        getLogin = (Request req, Response res) -> {
     
            HashMap model = new HashMap();
            
            if(req.queryParams("pass")!=null && req.queryParams("username")!=null)
            {
                UsuarioDAO uDAO = new UsuarioDAO();
                
                Usuario u = new Usuario();
                u.setNombre(req.queryParams("username"));
                u.setContrasenia(req.queryParams("pass"));
                
                List<Usuario> user = uDAO.verificarPersona(u);
                
                if(user.size() > 0){
                    //CREAR SEASION/COOKIE
                    Usuario usuarioLogeado = user.get(0);
                    
                    req.session(true);                      // Crear y retornar la sesion
                    req.session().attribute("id", usuarioLogeado.getId_usuario() );       
                    req.session().attribute("usertype", usuarioLogeado.getTipo() );                    
                    req.session().attribute("username", usuarioLogeado.getNombre() ); 
                    //req.session().attribute("atributo")   //para recuperar atributo
                    
                    res.redirect("/getProductos");
                }else{
                    model.put("request",req);
                    model.put("error", "La contraseña o el email es incorrecto.");
                }
                
            }else{
                model.put("email","");
            }
            
            return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/login.vsl")); 
        }; 
    
    public static Route 
            Logout = (Request req, Response res) -> {
     
            req.session().removeAttribute("id");
            req.session().removeAttribute("usertype");
            req.session().removeAttribute("username");
            res.redirect("/");
            return null;
        };
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Model;
import lombok.Data;
/**
 *
 * @author Luciano
 */
@Data
public class DetalleCarrito {
    private int id_carrito;
    private int id_producto;
    private int cant;
    //private double subtotal; //en caso de no lograr mandar un arreglo 
}

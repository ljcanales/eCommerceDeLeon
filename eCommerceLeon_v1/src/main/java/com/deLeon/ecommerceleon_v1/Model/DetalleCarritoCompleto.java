/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Model;
import lombok.Data;
/**
 *
 * @author Gaston
 */
@Data
public class DetalleCarritoCompleto {
    private int id_carrito;
    private int id_producto;
    private String nombre;
    private int cant;
    private double precio;
}

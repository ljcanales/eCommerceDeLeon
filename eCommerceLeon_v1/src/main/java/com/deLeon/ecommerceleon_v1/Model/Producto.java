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
public class Producto {
    private int id_producto;
    private String nombre;
    private double precio;
    private double stock;
}
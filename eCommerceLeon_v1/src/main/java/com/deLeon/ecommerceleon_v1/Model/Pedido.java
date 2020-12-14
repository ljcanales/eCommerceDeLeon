/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deLeon.ecommerceleon_v1.Model;
import lombok.Data;
import java.util.Date;

/**
 * @author Dario
 * @author Luciano
 * @author Gaston
 */
@Data
public class Pedido {
    private int id_pedido;
    private int id_cliente;
    private Date fecha_pedido;
    private Date fecha_envio;
    private Double total;
    private String estado;  
}


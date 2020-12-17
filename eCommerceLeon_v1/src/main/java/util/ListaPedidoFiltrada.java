/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.deLeon.ecommerceleon_v1.Model.Pedido;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Luciano
 */
public class ListaPedidoFiltrada implements Iterable<Pedido> {
    private final List<Pedido> list;
    private final String filter;
    public ListaPedidoFiltrada(final List<Pedido> list, final String filter) {
        this.list = list;
        this.filter = filter;
    }

    @Override
    public Iterator<Pedido> iterator() {
        return new Iterator<Pedido>() {
            private int pos;
            @Override
            public boolean hasNext() {
                while(pos < list.size()){
                    if (list.get(pos).getEstado() != null && list.get(pos).getEstado().equals(filter))
                        return true;
                    pos++;
                }
                return false;
            }

            @Override
            public Pedido next() {
                if (hasNext())
                    return list.get(pos++);
                return null;
            }
        };
    }
}

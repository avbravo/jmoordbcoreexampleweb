/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.controller.table;

import fish.payara.model.Producto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author avbravo
 */
public class TableDataProductoConverter {
    /**
     * Convierte una lista de objetos Producto al formato Map<ID, List<Valores>>
     * requerido por TableEditable, para su edición.
     * * El orden de los valores añadidos (producto, precio, cantidad) debe
     * coincidir con el orden de los encabezados ('Producto', 'Precio', 'Cantidad').
     */
    public static Map<String, List<String>> convert(List<Producto> productos) {
        Map<String, List<String>> dataMap = new HashMap<>();
        
        for (Producto p : productos) {
            List<String> values = new ArrayList<>();
            
            // Los valores se añaden en el orden deseado para la tabla:
            values.add(p.getProducto());
            values.add(String.valueOf(p.getPrecio()));
            values.add(String.valueOf(p.getCantidad()));
            
            // La clave del Map es el ID del producto (usado como primera columna de la fila)
            dataMap.put(p.getIdproducto(), values);
        }
        return dataMap;
    }
}

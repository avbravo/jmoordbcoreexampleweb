/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.controller.table;

import fish.payara.model.Cliente;
import fish.payara.model.Cliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author avbravo
 */
public class TableDataClienteConverter {
    /**
     * Convierte una lista de objetos Cliente al formato Map<ID, List<Valores>>
     * requerido por TableEditable, para su edición.
     * * El orden de los valores añadidos (producto, precio, cantidad) debe
     * coincidir con el orden de los encabezados ('Cliente', 'Precio', 'Cantidad').
     */
    public static Map<String, List<String>> convert(List<Cliente> clientes) {
        Map<String, List<String>> dataMap = new HashMap<>();
        
        for (Cliente p : clientes) {
            List<String> values = new ArrayList<>();
            
            // Los valores se añaden en el orden deseado para la tabla:
            
            values.add(p.getNombre());
            values.add(p.getLatitude().toString());
            values.add(p.getLongitude().toString());
            
            // La clave del Map es el ID del producto (usado como primera columna de la fila)
            dataMap.put(p.getId().toString(), values);
        }
        return dataMap;
    }
}
